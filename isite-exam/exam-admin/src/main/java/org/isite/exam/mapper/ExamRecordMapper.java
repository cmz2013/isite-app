package org.isite.exam.mapper;

import org.apache.ibatis.annotations.Param;
import org.isite.exam.po.ExamRecordPo;
import org.isite.mybatis.mapper.PoMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Repository
public interface ExamRecordMapper extends PoMapper<ExamRecordPo, Long> {

    /**
     * 查询最近一次的考试记录
     * @param tenantId 可以为空
     */
    ExamRecordPo selectLastExamRecord(
            @Nullable @Param("tenantId") Integer tenantId, @Param("userId") long userId,
            @Param("sceneId") int sceneId, @Param("paperId") int paperId );
}
