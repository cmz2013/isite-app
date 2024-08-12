package org.isite.exam.service;

import org.isite.exam.core.ExamAccessorFactory;
import org.isite.exam.core.ScoreCalculator;
import org.isite.exam.core.ScoreCalculatorFactory;
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

import static java.lang.Boolean.TRUE;
import static org.isite.commons.lang.Assert.notEmpty;
import static org.isite.commons.lang.Assert.notNull;
import static org.isite.commons.lang.data.Constants.ZERO;
import static org.isite.commons.lang.json.Jackson.parseArray;
import static org.isite.commons.web.data.Converter.toMap;
import static org.isite.exam.converter.ExamRecordConverter.toExamRecord;

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
    public ExamRecord applyExam(ExamScenePo scenePo, @Nullable Integer tenantId, Long userId) {
        ExamRecordPo examRecordPo;
        ExamDetailPo examDetailPo;
        if (TRUE.equals(scenePo.getContinues())) {
            examRecordPo = examRecordService.findLastExamRecord(
                    tenantId,  userId, scenePo.getId(), scenePo.getPaperId());
            if (null != examRecordPo && examRecordService.notFinished(examRecordPo)) {
                examDetailPo = examDetailService.findOne(ExamDetailPo::getExamRecordId, examRecordPo.getId());
                return toExamRecord(examRecordPo, examDetailPo);
            }
        }
        ExamPaperPo paperPo = examPaperService.get(scenePo.getPaperId());
        notNull(paperPo, "examPaper not found: " + scenePo.getPaperId());
        List<ExamModule> examModules = examAccessorFactory.get(paperPo.getQuestionMode())
                .getExamModules(scenePo.getPaperId());
        notEmpty(examModules, "examModules is empty: " +  + scenePo.getPaperId());
        examRecordPo = examRecordService.saveExamRecord(scenePo, paperPo, tenantId, userId);
        examDetailPo = examDetailService.saveExamDetail(examRecordPo.getId(), examModules);
        return toExamRecord(examRecordPo, examDetailPo);
    }

    /**
     * @Description 用户提交答卷
     * @param userAnswers 用户答题记录
     */
    @Transactional(rollbackFor = Exception.class)
    public int submitExam(long examRecordId, List<UserAnswer> userAnswers) {
        ExamDetailPo examDetailPo = examDetailService.findOne(ExamDetailPo::getExamRecordId, examRecordId);
        List<ExamModule> examModules = parseArray(examDetailPo.getExamModules(), ExamModule.class);
        int userScore = ZERO;
        Map<Long, UserAnswer> answerMap = toMap(userAnswers, UserAnswer::getQuestionId);
        for (ExamModule examModule : examModules) {
            ScoreCalculator calculator = scoreCalculatorFactory.get(examModule.getScoreAlgorithm());
            if (null != calculator) {
                userScore += calculator.getUserScore(examModule, answerMap);
            }
        }
        examRecordService.updateUserScore(examRecordId, userScore);
        examDetailService.updateUserAnswers(examRecordId, userAnswers);
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
