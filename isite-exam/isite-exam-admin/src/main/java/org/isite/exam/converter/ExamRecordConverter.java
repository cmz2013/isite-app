package org.isite.exam.converter;

import org.isite.commons.cloud.converter.DataConverter;
import org.isite.exam.data.vo.ExamRecord;
import org.isite.exam.po.ExamDetailPo;
import org.isite.exam.po.ExamRecordPo;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class ExamRecordConverter {

    private ExamRecordConverter() {
    }

    public static ExamRecord toExamRecord(ExamRecordPo examRecordPo, ExamDetailPo examDetailPo) {
        ExamRecord examRecord = DataConverter.convert(examRecordPo, ExamRecord::new);
        examRecord.setExamDetail(ExamDetailConverter.toExamDetail(examDetailPo));
        return examRecord;
    }
}
