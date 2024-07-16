package org.isite.exam.mapper;

import org.apache.ibatis.annotations.Param;
import org.isite.exam.po.ExamRecordPo;
import org.isite.mybatis.mapper.ModelMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Repository
public interface ExamRecordMapper extends ModelMapper<ExamRecordPo, Long> {

    /**
     * 查询最近一次的考试记录
     * @param tenantId 可以为空
     */
    ExamRecordPo selectLastExamRecord(
            @Param("sceneId") int sceneId, @Param("paperId") int paperId,
            @Param("userId") long userId, @Param("tenantId") Integer tenantId);
}
