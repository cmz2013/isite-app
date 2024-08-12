package org.isite.exam.service;

import org.isite.exam.mapper.ExamRecordMapper;
import org.isite.exam.po.ExamPaperPo;
import org.isite.exam.po.ExamRecordPo;
import org.isite.exam.po.ExamScenePo;
import org.isite.mybatis.service.PoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static java.lang.System.currentTimeMillis;
import static org.isite.commons.lang.data.Constants.THOUSAND;
import static org.isite.commons.lang.data.Constants.ZERO;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamRecordService extends PoService<ExamRecordPo, Long> {

    @Autowired
    public ExamRecordService(ExamRecordMapper examRecordMapper) {
        super(examRecordMapper);
    }

    /**
     * @Description 查询最近一次的考试记录
     */
    public ExamRecordPo findLastExamRecord(
            @Nullable Integer tenantId, long userId, int sceneId, int paperId) {
        return ((ExamRecordMapper) getMapper()).selectLastExamRecord(tenantId, userId, sceneId, paperId);
    }

    /**
     * true：考试没有结束，false：考试已结束
     */
    public boolean notFinished(ExamRecordPo examRecordPo) {
        return !isFinished(examRecordPo);
    }

    /**
     * 考试是否已结束
     */
    public boolean isFinished(ExamRecordPo examRecordPo) {
        if (null != examRecordPo.getSubmitTime()) {
            return true;
        }
        //不限制考试时间
        if (null == examRecordPo.getExamSecond() || ZERO == examRecordPo.getExamSecond()) {
            return false;
        }
        long time = currentTimeMillis() - examRecordPo.getCreateTime().getTime();
        return (time / THOUSAND) >= examRecordPo.getExamSecond();
    }

    /**
     * 保存考试记录，租户ID可以为空
     * 注解@Nullable用于标记一个方法、字段、参数或返回值可以为null。这个注解主要用于提高代码的可读性和可维护性
     */
    @Transactional(rollbackFor = Exception.class)
    public ExamRecordPo saveExamRecord(
            ExamScenePo scenePo, ExamPaperPo paperPo, @Nullable Integer tenantId, long userId) {
        ExamRecordPo examRecordPo = new ExamRecordPo();
        examRecordPo.setPaperId(paperPo.getId());
        examRecordPo.setTitle(scenePo.getTitle());
        examRecordPo.setExamSecond(paperPo.getExamSecond());
        examRecordPo.setSceneId(scenePo.getId());
        examRecordPo.setTenantId(tenantId);
        examRecordPo.setTotalScore(paperPo.getTotalScore());
        examRecordPo.setUserId(userId);
        this.insert(examRecordPo);
        return examRecordPo;
    }

    /**
     * 更新用户分数
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateUserScore(Long examRecordId, Integer userScore) {
        ExamRecordPo examRecordPo = new ExamRecordPo();
        examRecordPo.setId(examRecordId);
        examRecordPo.setUserScore(userScore);
        examRecordPo.setSubmitTime(new Date(currentTimeMillis()));
        this.updateSelectiveById(examRecordPo);
    }
}
