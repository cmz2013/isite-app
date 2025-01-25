package org.isite.oa.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.isite.commons.lang.enums.Enumerable;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

/**
 * @Description 扣款类型
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
    /**
     * 旷工一天会扣发当天的全部工资，一个月内或一年内累计旷工达到一定天数，扣发更多工资、给予警告，甚至辞退
     */
    ABSENTEEISM(4, "旷工"),
    /**
     * 迟到或早退30分钟以内者，每次扣发一定金额的薪金；
     * 超过30分钟但不足1小时者，扣款金额相应增加；
     * 超过1小时以上者，需提前办理请假手续，否则按旷工处理。
     * 月迟到、早退累计达到一定次数者，会面临更严厉的扣款或纪律处分。
     */
    ARRIVE_LATE(5, "迟到"),
    LEAVE_EARLY(6, "早退")
    ;

    private final Integer code;
    private final String label;

    DeductionType(Integer code, String label) {
        this.code = code;
        this.label = label;
    }
}
