package org.isite.oa.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;

/**
 * @Description 员工银行账户
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "bank_account")
public class BankAccountPo extends Po<Integer> {
    /**
     * 员工ID
     */
    private Long employeeId;
    /**
     * 账户持有人姓名（录入时负责人检查，要求必须和员工真实姓名一致）
     */
    private String holderName;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 账户号码
     */
    private String accountNumber;
    /**
     * 备注
     */
    private String remark;
}
