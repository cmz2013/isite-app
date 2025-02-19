package org.isite.bi.service.operation;

import org.isite.bi.mapper.operation.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class ActivityService {

    private final ActivityMapper activityMapper;

    @Autowired
    public ActivityService(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    /**
     * 批量（100条）查询进行中的活动ID，活动ID除以shardTotal取余，如果余数为shardIndex，则返回该活动ID
     */
    public List<Integer> findIds(int shardIndex, int shardTotal, long minId) {
        return activityMapper.selectOngoingActivityIds(shardIndex, shardTotal, minId);
    }
}
