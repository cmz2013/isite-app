package org.isite.bi.controller.operation;

import org.isite.bi.cache.operation.InviteRankCache;
import org.isite.bi.data.vo.operation.InviteRank;
import org.isite.commons.cloud.data.Result;
import org.isite.commons.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.isite.bi.data.constants.BiConstants.URL_BI;
import static org.isite.commons.cloud.constants.UrlConstants.URL_API;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@RestController
public class InviteRankController extends BaseController {

    private InviteRankCache inviteRankCache;

    /**
     * 查询活动邀请排行榜单 TOP 100，不需要登录就可以访问。从定时任务初始化的缓存读取数据
     */
    @GetMapping(URL_API + URL_BI + "/invite/{activityId}/rank")
    public Result<List<InviteRank>> getInviteRank(@PathVariable("activityId") Integer activityId) {
        return toResult(this.inviteRankCache.getInviteRank(activityId));
    }

    @Autowired
    public void setInviteRankCache(InviteRankCache inviteRankCache) {
        this.inviteRankCache = inviteRankCache;
    }
}