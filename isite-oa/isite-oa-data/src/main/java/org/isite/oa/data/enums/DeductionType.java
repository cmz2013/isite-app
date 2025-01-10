package org.isite.oa.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.isite.commons.lang.enums.Enumerable;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

/**
 * @Description 积分类型
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@JsonFormat(shape = OBJECT)
public enum DeductionType implements Enumerable<Integer> {

    INCOME_TAX(1, "所得税"),
    MEDICARE_TAX(2, "医疗保险"),
    /**
     * 用于支付员工未来的退休金
     */
    SOCIAL_SECURITY(3, "社会保障"),
    ABSENTEEISM(4, "旷工"),
    /**
     * 迟到扣除
     */
    LATE_ARRIVAL(5, "迟到"),
    /**
     * 早退扣除
     */
    EARLY_DEPARTURE(6, "早退")
    ;

    private final Integer code;
    private final String label;

    DeductionType(Integer code, String label) {
        this.code = code;
        this.label = label;
    }
}
