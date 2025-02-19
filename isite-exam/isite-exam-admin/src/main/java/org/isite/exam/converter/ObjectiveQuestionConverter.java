package org.isite.exam.converter;

import org.isite.commons.cloud.converter.DataConverter;
import org.isite.commons.lang.Reflection;
import org.isite.commons.lang.json.Jackson;
import org.isite.commons.lang.utils.TypeUtils;
import org.isite.exam.data.dto.ObjectiveQuestionDto;
import org.isite.exam.data.vo.ObjectiveQuestion;
import org.isite.exam.data.vo.Option;
import org.isite.exam.po.QuestionPo;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
public abstract class ObjectiveQuestionConverter<V extends ObjectiveQuestion<T>,
        D extends ObjectiveQuestionDto, T> extends QuestionConverter<V, D> {
    /**
     * 客观题扩展的属性转VO
     */
    @Override
    protected void toQuestionVo(QuestionPo questionPo, V question) {
        question.setOptions(Jackson.parseArray(questionPo.getOptions(), Option.class));
        question.setRightAnswer(toRightAnswer(questionPo));
    }

    @Override
    protected Class<V> getQuestionVoClass() {
        return TypeUtils.cast(Reflection.getGenericParameter(this.getClass(), ObjectiveQuestionConverter.class));
    }

    /**
     * 客观题正确答案转VO
     */
    protected abstract T toRightAnswer(QuestionPo questionPo);

    /**
     * 客观题扩展的属性转PO
     */
    @Override
    protected void toQuestionPo(D questionDto, QuestionPo questionPo) {
        questionPo.setOptions(Jackson.toJsonString(DataConverter.convert(questionDto.getOptions(), Option::new)));
        questionPo.setRightAnswer(toRightAnswer(questionDto));
    }

    /**
     * @Description 客观题正确答案转PO
     */
    protected abstract String toRightAnswer(D questionDto);
}
