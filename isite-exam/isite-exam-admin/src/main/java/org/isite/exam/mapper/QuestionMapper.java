package org.isite.exam.mapper;

import org.isite.exam.po.QuestionPo;
import org.isite.mybatis.mapper.PoMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Repository
public interface QuestionMapper extends PoMapper<QuestionPo, Long> {
}
