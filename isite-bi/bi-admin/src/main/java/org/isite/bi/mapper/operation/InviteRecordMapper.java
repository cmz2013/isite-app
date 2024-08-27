package org.isite.bi.mapper.operation;

import org.apache.ibatis.annotations.Param;
import org.isite.bi.data.vo.operation.InviteRank;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Repository
@RegisterMapper
public interface InviteRecordMapper {

    List<InviteRank> selectInviteRank(@Param("activityId") int activityId);
}
