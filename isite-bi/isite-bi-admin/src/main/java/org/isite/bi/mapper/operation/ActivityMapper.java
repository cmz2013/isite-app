package org.isite.bi.mapper.operation;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * @Description 在 Mapper 接口上添加 @RegisterMapper 注解，MyBatis 会在启动时自动扫描并注册这些接口
 * @Author <font color='blue'>zhangcm</font>
 */
@Repository
@RegisterMapper
public interface ActivityMapper {

    /**
     * 查询一个进行中的活动ID，活动ID除以shardTotal取余，如果余数为shardIndex，则返回该活动ID
     */
    Integer selectOngoingActivityId(@Param("shardIndex") int shardIndex,
                                    @Param("shardTotal") int shardTotal,
                                    @Param("minId") int minId);
}
