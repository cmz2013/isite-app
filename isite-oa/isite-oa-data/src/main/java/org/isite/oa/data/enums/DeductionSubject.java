package org.isite.oa.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.isite.commons.lang.Constants;
import org.isite.commons.lang.enums.Enumerable;
/**
 * @Description 扣费科目
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DeductionSubject implements Enumerable<Integer> {
    INCOME_TAX(Constants.ONE, "所得税"),
    MEDICAL_INSURANCE(Constants.TWO, "医疗保险"),
    PENSION_INSURANCE(Constants.THREE, "养老保险"),
    UNEMPLOYMENT_INSURANCE(Constants.FOUR, "失业保险"),
    WORK_INJURY_INSURANCE(Constants.FIVE, "工伤保险"),
    MATERNITY_INSURANCE(Constants.SIX, "生育保险"),
    PROVIDENT_FUND(Constants.SEVEN, "公积金"),
    /**
     * 旷工一天会扣发当天的全部工资，一个月内或一年内累计旷工达到一定天数，扣发更多工资、给予警告，甚至辞退
     */
    ABSENTEEISM(Constants.EIGHT, "旷工")
    ;

    private final Integer code;
    private final String label;

    DeductionSubject(Integer code, String label) {
        this.code = code;
        this.label = label;
    }
}
