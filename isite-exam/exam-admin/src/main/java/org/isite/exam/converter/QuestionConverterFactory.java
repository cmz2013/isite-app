package org.isite.exam.converter;

import org.isite.commons.cloud.factory.AbstractFactory;
import org.isite.exam.data.dto.QuestionDto;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.Question;
import org.springframework.stereotype.Component;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class QuestionConverterFactory extends
        AbstractFactory<QuestionConverter<Question, QuestionDto>, QuestionType, Integer> {
}
