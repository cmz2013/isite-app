package org.isite.exam.service;

import org.isite.exam.mapper.ExamRecordMapper;
import org.isite.exam.po.ExamPaperPo;
import org.isite.exam.po.ExamRecordPo;
import org.isite.exam.po.ExamScenePo;
import org.isite.mybatis.service.ModelService;
import org.isite.security.support.oauth.OauthEmployee;
import org.isite.security.support.oauth.OauthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.lang.System.currentTimeMillis;
import static org.isite.commons.lang.data.Constants.THOUSAND;
import static org.isite.commons.lang.data.Constants.ZERO;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamRecordService extends ModelService<ExamRecordPo, Long> {

    @Autowired
    public ExamRecordService(ExamRecordMapper examRecordMapper) {
        super(examRecordMapper);
    }

    /**
     * @Description 查询最近一次的考试记录
     */
    public ExamRecordPo findLastExamRecord(int sceneId, int paperId, OauthUser user) {
        Integer tenantId = null;
        if (user instanceof OauthEmployee) {
            tenantId = ((OauthEmployee) user).getTenantId();
        }
        return ((ExamRecordMapper) getMapper()).selectLastExamRecord(sceneId, paperId, user.getUserId(), tenantId);
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
     * 保存考试记录
     */
    public ExamRecordPo saveExamRecord(ExamScenePo scenePo, ExamPaperPo paperPo, OauthUser user) {
        ExamRecordPo examRecordPo = new ExamRecordPo();
        examRecordPo.setPaperId(paperPo.getId());
        examRecordPo.setTitle(scenePo.getTitle());
        examRecordPo.setExamSecond(paperPo.getExamSecond());
        examRecordPo.setSceneId(scenePo.getId());
        if (user instanceof OauthEmployee) {
            examRecordPo.setTenantId(((OauthEmployee) user).getTenantId());
        }
        examRecordPo.setTotalScore(paperPo.getTotalScore());
        examRecordPo.setUserId(user.getUserId());
        insert(examRecordPo);
        return examRecordPo;
    }

    /**
     * 更新用户分数
     */
    public void updateUserScore(Long examRecordId, Integer userScore) {
        ExamRecordPo examRecordPo = new ExamRecordPo();
        examRecordPo.setId(examRecordId);
        examRecordPo.setUserScore(userScore);
        examRecordPo.setSubmitTime(new Date(currentTimeMillis()));
        this.updateSelectiveById(examRecordPo);
    }
}
