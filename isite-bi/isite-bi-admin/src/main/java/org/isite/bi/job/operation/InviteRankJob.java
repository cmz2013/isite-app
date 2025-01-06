package org.isite.bi.job.operation;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.isite.bi.cache.operation.InviteRankCache;
import org.isite.bi.service.operation.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;
import static com.xxl.job.core.context.XxlJobHelper.getShardIndex;
import static com.xxl.job.core.context.XxlJobHelper.getShardTotal;
import static com.xxl.job.core.context.XxlJobHelper.log;
import static org.isite.commons.lang.Constants.ZERO;

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
    @XxlJob("inviteRankJobHandler")
    public ReturnT<String> execute(String params) {
        int shardIndex = getShardIndex();
        int shardTotal = getShardTotal();
        log("统计活动邀请排行榜单计算任务：当前分片序号(分片序号从0开始) = {}, 总分片数 = {}", shardIndex, shardTotal);

        Integer activityId = getOngoingActivityId(shardIndex, shardTotal, ZERO);
        while (null != activityId) {
            inviteRankCache.updateInviteRank(activityId);
            activityId = getOngoingActivityId(shardIndex, shardTotal, activityId);
        }
        return SUCCESS;
    }

    /**
     * 查询一个进行中的活动ID，活动ID除以shardTotal取余，如果余数为shardIndex，则返回该活动ID
     */
    private Integer getOngoingActivityId(int shardIndex, int shardTotal, int minId) {
        return activityService.getOngoingActivityId(shardIndex, shardTotal, minId);
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
