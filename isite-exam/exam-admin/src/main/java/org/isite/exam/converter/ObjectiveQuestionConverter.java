package org.isite.exam.converter;

import org.isite.exam.data.dto.ObjectiveQuestionDto;
import org.isite.exam.data.vo.ObjectiveQuestion;
import org.isite.exam.data.vo.Option;
import org.isite.exam.po.QuestionPo;

import static org.isite.commons.cloud.converter.DataConverter.convert;
import static org.isite.commons.lang.Reflection.getGenericParameter;
import static org.isite.commons.lang.json.Jackson.parseArray;
import static org.isite.commons.lang.json.Jackson.toJsonString;
import static org.isite.commons.lang.utils.TypeUtils.cast;

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
        question.setOptions(parseArray(questionPo.getOptions(), Option.class));
        question.setRightAnswer(toRightAnswer(questionPo));
    }

    @Override
    protected Class<V> getQuestionVoClass() {
        return cast(getGenericParameter(this.getClass(), ObjectiveQuestionConverter.class));
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
        questionPo.setOptions(toJsonString(convert(questionDto.getOptions(), Option::new)));
        questionPo.setRightAnswer(toRightAnswer(questionDto));
    }

    /**
     * @Description 客观题正确答案转PO
     */
    protected abstract String toRightAnswer(D questionDto);
}
