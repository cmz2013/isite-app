package org.isite.exam.service;

import org.isite.commons.cloud.converter.MapConverter;
import org.isite.commons.lang.Assert;
import org.isite.commons.lang.Constants;
import org.isite.commons.lang.json.Jackson;
import org.isite.commons.web.sync.Lock;
import org.isite.commons.web.sync.Synchronized;
import org.isite.exam.converter.ExamRecordConverter;
import org.isite.exam.core.ExamAccessorFactory;
import org.isite.exam.core.ScoreCalculator;
import org.isite.exam.core.ScoreCalculatorFactory;
import org.isite.exam.data.constants.CacheKeys;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.ExamRecord;
import org.isite.exam.data.vo.UserAnswer;
import org.isite.exam.po.ExamDetailPo;
import org.isite.exam.po.ExamPaperPo;
import org.isite.exam.po.ExamRecordPo;
import org.isite.exam.po.ExamScenePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
 * @Description 线上考试 Service
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class OnlineExamService {
    private ScoreCalculatorFactory scoreCalculatorFactory;
    private ExamPaperService examPaperService;
    private ExamDetailService examDetailService;
    private ExamRecordService examRecordService;
    private ExamAccessorFactory examAccessorFactory;

    /**
     * @Description 查询未结束的考试记录，不存在时创建考试记录，用于开始考试
     */
    @Transactional(rollbackFor = Exception.class)
    public ExamRecord applyExam(ExamScenePo examScenePo, @Nullable Integer tenantId, Long userId) {
        ExamRecordPo examRecordPo;
        ExamDetailPo examDetailPo;
        if (Boolean.TRUE.equals(examScenePo.getContinues())) {
            examRecordPo = examRecordService.findLastExamRecord(
                    tenantId,  userId, examScenePo.getId(), examScenePo.getExamPaperId());
            if (null != examRecordPo && examRecordService.notFinished(examRecordPo)) {
                examDetailPo = examDetailService.findOne(ExamDetailPo::getExamRecordId, examRecordPo.getId());
                return ExamRecordConverter.toExamRecord(examRecordPo, examDetailPo);
            }
        }
        ExamPaperPo examPaperPo = examPaperService.get(examScenePo.getExamPaperId());
        Assert.notNull(examPaperPo, "examPaper not found: " + examScenePo.getExamPaperId());
        List<ExamModule> examModules = examAccessorFactory.get(examPaperPo.getQuestionMode())
                .getExamModules(examScenePo.getExamPaperId());
        Assert.notEmpty(examModules, "examModules is empty: " +  + examScenePo.getExamPaperId());
        examRecordPo = examRecordService.saveExamRecord(examScenePo, examPaperPo, tenantId, userId);
        examDetailPo = examDetailService.saveExamDetail(examRecordPo.getId(), examModules);
        return ExamRecordConverter.toExamRecord(examRecordPo, examDetailPo);
    }

    /**
     * @Description 用户提交答卷
     * @param userAnswers 用户答题记录
     */
    @Transactional(rollbackFor = Exception.class)
    @Synchronized(locks = @Lock(name = CacheKeys.LOCK_EXAM_SUBMIT, keys = "#examRecordId"))
    public int submitExam(long examRecordId, List<UserAnswer> userAnswers) {
        Assert.isNull(examRecordService.get(examRecordId).getSubmitTime(), "it's already been submitted");
        ExamDetailPo examDetailPo = examDetailService.findOne(ExamDetailPo::getExamRecordId, examRecordId);
        List<ExamModule> examModules = Jackson.parseArray(examDetailPo.getExamModules(), ExamModule.class);
        int userScore = Constants.ZERO;
        Map<Long, UserAnswer> answerMap = MapConverter.toMap(UserAnswer::getQuestionId, userAnswers);
        for (ExamModule examModule : examModules) {
            ScoreCalculator calculator = scoreCalculatorFactory.get(examModule.getScoreAlgorithm());
            if (null != calculator) {
                userScore += calculator.getUserScore(examModule, answerMap);
            }
        }
        examRecordService.saveUserScore(examRecordId, userScore);
        examDetailService.saveUserAnswers(examRecordId, userAnswers);
        return userScore;
    }

    @Autowired
    public void setExamPaperService(ExamPaperService examPaperService) {
        this.examPaperService = examPaperService;
    }

    @Autowired
    public void setExamDetailService(ExamDetailService examDetailService) {
        this.examDetailService = examDetailService;
    }

    @Autowired
    public void setExamRecordService(ExamRecordService examRecordService) {
        this.examRecordService = examRecordService;
    }

    @Autowired
    public void setExamAccessorFactory(ExamAccessorFactory examAccessorFactory) {
        this.examAccessorFactory = examAccessorFactory;
    }

    @Autowired
    public void setExamScoreCalculatorFactory(ScoreCalculatorFactory scoreCalculatorFactory) {
        this.scoreCalculatorFactory = scoreCalculatorFactory;
    }
}
