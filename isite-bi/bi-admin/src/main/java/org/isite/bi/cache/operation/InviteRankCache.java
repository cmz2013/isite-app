package org.isite.bi.cache.operation;

import org.isite.bi.data.vo.operation.InviteRank;
import org.isite.bi.service.operation.InviteRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.time.Duration.ofDays;
import static org.isite.bi.data.constants.CacheKey.OPERATION_INVITE_RANK_PREFIX;
import static org.isite.commons.lang.Constants.THREE;
import static org.isite.commons.lang.Constants.ZERO;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class InviteRankCache {

    /**
     * 操作redis缓存
     */
    private RedisTemplate<String, InviteRank> redisTemplate;
    private InviteRankService inviteRankService;

    /**
     * 邀请榜单TOP 100
     */
    public List<InviteRank> getInviteRank(int activityId) {
        //opsForList()用于操作Redis的List数据结构，range()方法用于获取列表中指定范围内的元素。
        return redisTemplate.opsForList().range(OPERATION_INVITE_RANK_PREFIX + activityId, ZERO, -1);
    }

    /**
     * 缓存3天，每天0点更新一次
     */
    public void updateInviteRank(int activityId) {
        String key = OPERATION_INVITE_RANK_PREFIX + activityId;
        //删除现有列表
        redisTemplate.delete(key);
        // 批量插入新数据。rightPushAll是用于将多个值一次性推入列表的右侧（末尾）
        redisTemplate.opsForList().rightPushAll(key, inviteRankService.getInviteRank(activityId));
        // 设置缓存过期时间为3天
        redisTemplate.expire(key, ofDays(THREE));
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, InviteRank> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setInviteRankService(InviteRankService inviteRankService) {
        this.inviteRankService = inviteRankService;
    }
}
