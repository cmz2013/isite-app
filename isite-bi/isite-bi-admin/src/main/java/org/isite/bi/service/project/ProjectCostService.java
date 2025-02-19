package org.isite.bi.service.project;

import org.isite.bi.data.vo.project.ProjectCost;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class ProjectCostService {
    /**
     * 批量（100条）查询项目费用参数，项目ID除以shardTotal取余，如果余数为shardIndex，则返回该条记录
     */
    public List<ProjectCost> findList(int shardIndex, int shardTotal, long minId) {
        return null;
    }
}
