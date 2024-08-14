package org.isite.exam.converter;

import org.isite.exam.po.QuestionPo;
import org.isite.exam.data.dto.SubjectiveQuestionDto;
import org.isite.exam.data.vo.SubjectiveQuestion;

import static org.isite.commons.lang.Reflection.getGenericParameter;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
public abstract class SubjectiveQuestionConverter<V extends SubjectiveQuestion,
        D extends SubjectiveQuestionDto> extends QuestionConverter<V, D> {
    /**
     * 主观题扩展的属性转VO
     */
    @Override
    protected void toQuestionVo(QuestionPo questionPo, V question) {
    }

    @Override
    protected Class<V> getQuestionVoClass() {
        return (Class<V>) getGenericParameter(this.getClass(), ObjectiveQuestionConverter.class);
    }

    /**
     * 主观题扩展的属性转PO
     */
    @Override
    protected void toQuestionPo(D questionDto, QuestionPo questionPo) {
    }
}
