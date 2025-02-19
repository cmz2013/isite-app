package org.isite.bi.job.operation;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.collections4.CollectionUtils;
import org.isite.bi.cache.operation.InviteRankCache;
import org.isite.bi.service.operation.ActivityService;
import org.isite.commons.lang.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @Description 计算项目费用JOB
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class InviteRankJob {
    private ActivityService activityService;
    private InviteRankCache inviteRankCache;

    /**
     * @Description 分片任务：统计活动邀请排行榜单（缓存3天，每天0点更新一次）
     * 10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍
     */
    @XxlJob("inviteRankJob")
    public ReturnT<String> execute(String params) {
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        XxlJobHelper.log("统计活动邀请排行榜单计算任务：" +
                "当前分片序号(分片序号从0开始) = {}, 总分片数 = {}", shardIndex, shardTotal);
        long index = Constants.ZERO;
        List<Integer> activityIds = activityService.findIds(shardIndex, shardTotal, Constants.ZERO);
        while (CollectionUtils.isNotEmpty(activityIds)) {
            activityIds.forEach(activityId -> inviteRankCache.updateInviteRank(activityId));
            activityIds = activityIds.size() == Constants.HUNDRED ?
                    activityService.findIds(shardIndex, shardTotal, ++index * Constants.HUNDRED) : null;
        }
        return ReturnT.SUCCESS;
    }

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Autowired
    public void setInviteRankCache(InviteRankCache inviteRankCache) {
        this.inviteRankCache = inviteRankCache;
    }
}
