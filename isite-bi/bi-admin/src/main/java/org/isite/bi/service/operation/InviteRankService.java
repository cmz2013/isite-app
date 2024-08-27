package org.isite.bi.service.operation;

import org.isite.bi.data.vo.operation.InviteRank;
import org.isite.bi.mapper.operation.InviteRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class InviteRankService {

    private final InviteRecordMapper inviteRecordMapper;

    @Autowired
    public InviteRankService(InviteRecordMapper inviteRecordMapper) {
        this.inviteRecordMapper = inviteRecordMapper;
    }

    public List<InviteRank> getInviteRank(int activityId) {
        return inviteRecordMapper.selectInviteRank(activityId);
    }
}
