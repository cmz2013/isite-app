package org.isite.exam.converter;

import org.isite.exam.data.vo.ExamRecord;
import org.isite.exam.po.ExamDetailPo;
import org.isite.exam.po.ExamRecordPo;

import static org.isite.commons.cloud.data.Converter.convert;
import static org.isite.exam.converter.ExamDetailConverter.toExamDetail;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class ExamRecordConverter {

    private ExamRecordConverter() {
    }

    public static ExamRecord toExamRecord(ExamRecordPo examRecordPo, ExamDetailPo examDetailPo) {
        ExamRecord examRecord = convert(examRecordPo, ExamRecord::new);
        examRecord.setExamDetail(toExamDetail(examDetailPo));
        return examRecord;
    }
}
