package org.isite.exam.converter;

import org.isite.exam.po.ExamDetailPo;
import org.isite.exam.data.vo.ExamDetail;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.UserAnswer;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.isite.commons.lang.json.Jackson.parseArray;

/**
 * @author <font color='blue'>zhangcm</font>
 */
public class ExamDetailConverter {

    private ExamDetailConverter() {
    }

    public static ExamDetail toExamDetail(ExamDetailPo examDetailPo) {
        ExamDetail examDetail = new ExamDetail();
        examDetail.setId(examDetailPo.getId());
        examDetail.setCreateTime(examDetailPo.getCreateTime());
        examDetail.setUpdateTime(examDetailPo.getUpdateTime());
        examDetail.setExamModules(parseArray(examDetailPo.getExamModules(), ExamModule.class));
        if (isNotBlank(examDetailPo.getUserAnswers())) {
            examDetail.setUserAnswers(parseArray(examDetailPo.getUserAnswers(), UserAnswer.class));
        }
        return examDetail;
    }
}
