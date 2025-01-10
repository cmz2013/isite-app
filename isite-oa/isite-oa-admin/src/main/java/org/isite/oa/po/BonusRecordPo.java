package org.isite.oa.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;
import org.isite.mybatis.type.EnumTypeHandler;
import org.isite.oa.data.enums.BonusType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description 奖金记录
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "bonus_record")
public class BonusRecordPo extends Po<Long> {
    /**
     * 员工ID
     */
    private Integer employeeId;
    /**
     * 薪资周期
     */
    private Date payPeriod;
    /**
     * 奖金类型
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private BonusType bonusType;
    /**
     * 奖励金额(分)
     */
    private Integer amount;
    /**
     * 备注
     */
    private String remark;
}
