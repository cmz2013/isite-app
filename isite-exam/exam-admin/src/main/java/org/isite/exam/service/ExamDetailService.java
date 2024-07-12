package org.isite.exam.service;

import org.isite.exam.mapper.ExamDetailMapper;
import org.isite.exam.po.ExamDetailPo;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.UserAnswer;
import org.isite.mybatis.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.Collection;
import java.util.List;

import static org.isite.commons.lang.json.Jackson.toJsonString;
import static tk.mybatis.mapper.weekend.Weekend.of;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamDetailService extends ModelService<ExamDetailPo, Long> {

    @Autowired
    public ExamDetailService(ExamDetailMapper examDetailMapper) {
        super(examDetailMapper);
    }

    /**
     * 保存考试详情
     */
    public ExamDetailPo saveExamDetail(Long examRecordId, List<ExamModule> examModules) {
        ExamDetailPo examDetailPo = new ExamDetailPo();
        examDetailPo.setExamRecordId(examRecordId);
        examDetailPo.setExamModules(toJsonString(examModules));
        insert(examDetailPo);
        return examDetailPo;
    }

    /**
     * 更新用户答案
     */
    public void updateUserAnswers(Long examRecordId, Collection<UserAnswer> userAnswers) {
        ExamDetailPo examDetailPo = new ExamDetailPo();
        examDetailPo.setUserAnswers(toJsonString(userAnswers));
        Weekend<ExamDetailPo> weekend = of(ExamDetailPo.class);
        weekend.weekendCriteria().andEqualTo(ExamDetailPo::getExamRecordId, examRecordId);
        getMapper().updateByExampleSelective(examDetailPo, weekend);
    }
}
