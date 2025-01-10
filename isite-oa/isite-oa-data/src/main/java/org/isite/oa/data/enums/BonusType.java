package org.isite.oa.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.isite.commons.lang.enums.Enumerable;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

/**
 * @Description 奖金类型
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@JsonFormat(shape = OBJECT)
public enum BonusType implements Enumerable<Integer> {
    /**
     * 根据员工和团队的表现发放的奖金
     */
    PERFORMANCE(1, "绩效奖金"),
    /**
     * 在年末或财政年度结束时发放的奖金，通常用于奖励员工一年的工作表现。
     */
    YEAR_END(2, "年终奖金"),
    /**
     * 在特定节日前后（如春节、圣诞节）发放的奖金，旨在表达公司对员工的感谢和祝福
     */
    HOLIDAY(3, "节日奖金")
    ;

    private final Integer code;
    private final String label;

    BonusType(Integer code, String label) {
        this.code = code;
        this.label = label;
    }
}
