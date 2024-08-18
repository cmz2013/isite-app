package org.isite.exam.converter;

import lombok.SneakyThrows;
import org.isite.commons.cloud.factory.Strategy;
import org.isite.exam.data.dto.QuestionDto;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.QuestionStem;
import org.isite.exam.po.QuestionPo;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.isite.commons.cloud.data.Converter.convert;
import static org.isite.commons.lang.Reflection.getGenericParameter;
import static org.isite.commons.lang.json.Jackson.parseObject;
import static org.isite.commons.lang.json.Jackson.toJsonString;
import static org.isite.commons.lang.utils.TypeUtils.cast;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
public abstract class QuestionConverter<V extends Question, D extends QuestionDto> implements Strategy<QuestionType> {
    /**
     * 根据题目类型转VO
     */
    public List<V> toQuestions(List<QuestionPo> questionPos) {
        if (isEmpty(questionPos)) {
            return emptyList();
        }
        List<V> questionVos = new ArrayList<>(questionPos.size());
        questionPos.forEach(questionPo -> questionVos.add(toQuestion(questionPo)));
        return questionVos;
    }

    /**
     * 根据题目类型转VO
     */
    @SneakyThrows
    public V toQuestion(QuestionPo questionPo) {
        V question = getQuestionVoClass().getConstructor().newInstance();
        question.setAnswerAnalysis(questionPo.getAnswerAnalysis());
        question.setCreateTime(questionPo.getCreateTime());
        question.setId(questionPo.getId());
        question.setDifficultyLevel(questionPo.getDifficultyLevel());
        question.setMajorId(questionPo.getMajorId());
        question.setQuestionType(questionPo.getQuestionType());
        question.setPoolId(questionPo.getPoolId());
        question.setRemark(questionPo.getRemark());
        question.setTags(questionPo.getTags());
        question.setUpdateTime(questionPo.getUpdateTime());
        question.setQuestionStem(parseObject(questionPo.getQuestionStem(), QuestionStem.class));
        toQuestionVo(questionPo, question);
        return question;
    }

    protected Class<V> getQuestionVoClass() {
        return cast(getGenericParameter(this.getClass(), QuestionConverter.class));
    }

    /**
     * @Description 题目子类扩展的属性转VO
     */
    protected abstract void toQuestionVo(QuestionPo questionPo, V question);

    /**
     * 根据题目类型转PO
     */
    public QuestionPo toQuestionPo(D questionDto) {
        QuestionPo questionPo = new QuestionPo();
        questionPo.setAnswerAnalysis(questionDto.getAnswerAnalysis());
        questionPo.setDifficultyLevel(questionDto.getDifficultyLevel());
        questionPo.setId(questionDto.getId());
        questionPo.setMajorId(questionDto.getMajorId());
        questionPo.setQuestionType(questionDto.getQuestionType());
        questionPo.setPoolId(questionDto.getPoolId());
        questionPo.setRemark(questionDto.getRemark());
        questionPo.setTags(questionDto.getTags());
        questionPo.setQuestionStem(toJsonString(convert(questionDto.getQuestionStems(), QuestionStem::new)));
        toQuestionPo(questionDto, questionPo);
        return questionPo;
    }

    /**
     * @Description 题目子类扩展的属性PO
     */
    protected abstract void toQuestionPo(D questionDto, QuestionPo questionPo);
}
