package org.isite.bi.data.vo.operation;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class InviteRank implements Serializable {

    private long userId;
    private String userName;
    private int inviteCount;
}
