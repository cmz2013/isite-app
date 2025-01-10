package org.isite.oa.data.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.isite.commons.lang.enums.Enumerable;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

/**
 * @Description 缺勤原因
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@JsonFormat(shape = OBJECT)
public enum AbsenceReason implements Enumerable<Integer> {
    ANNUAL_LEAVE(1, "年假"),
    PERSONAL_LEAVE(2, "事假"),
    ABSENTEEISM(3, "旷工")
    ;

    private final Integer code;
    private final String label;

    AbsenceReason(Integer code, String label) {
        this.code = code;
        this.label = label;
    }
}
