package org.isite.exam.converter;

import org.apache.commons.lang3.StringUtils;
import org.isite.commons.lang.json.Jackson;
import org.isite.exam.data.vo.ExamDetail;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.UserAnswer;
import org.isite.exam.po.ExamDetailPo;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class ExamDetailConverter {

    private ExamDetailConverter() {
    }

    public static ExamDetail toExamDetail(ExamDetailPo examDetailPo) {
        ExamDetail examDetail = new ExamDetail();
        examDetail.setId(examDetailPo.getId());
        examDetail.setCreateTime(examDetailPo.getCreateTime());
        examDetail.setUpdateTime(examDetailPo.getUpdateTime());
        examDetail.setExamModules(Jackson.parseArray(examDetailPo.getExamModules(), ExamModule.class));
        if (StringUtils.isNotBlank(examDetailPo.getUserAnswers())) {
            examDetail.setUserAnswers(Jackson.parseArray(examDetailPo.getUserAnswers(), UserAnswer.class));
        }
        return examDetail;
    }
}
