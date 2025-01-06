package org.isite.bi.service.operation;

import org.isite.bi.mapper.operation.ActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Integer getOngoingActivityId(int shardIndex, int shardTotal, int minId) {
        return activityMapper.selectOngoingActivityId(shardIndex, shardTotal, minId);
    }
}
