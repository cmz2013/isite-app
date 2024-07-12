package org.isite.exam.converter;

import org.isite.exam.po.ExamDetailPo;
import org.isite.exam.po.ExamRecordPo;
import org.isite.exam.data.vo.ExamRecord;

import static org.isite.commons.cloud.data.Converter.convert;

/**
 * @author <font color='blue'>zhangcm</font>
 */
public class ExamRecordConverter {

    private ExamRecordConverter() {
    }

    public static ExamRecord toExamRecord(ExamRecordPo examRecordPo, ExamDetailPo examDetailPo) {
        ExamRecord examRecord = convert(examRecordPo, ExamRecord::new);
        examRecord.setExamDetail(ExamDetailConverter.toExamDetail(examDetailPo));
        return examRecord;
    }
}
