package org.isite.exam.converter;

import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.isite.commons.cloud.converter.DataConverter;
import org.isite.commons.cloud.factory.Strategy;
import org.isite.commons.lang.Constants;
import org.isite.commons.lang.Reflection;
import org.isite.commons.lang.json.Jackson;
import org.isite.commons.lang.utils.TypeUtils;
import org.isite.exam.data.dto.QuestionDto;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.QuestionStem;
import org.isite.exam.po.QuestionPo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
public abstract class QuestionConverter<V extends Question, D extends QuestionDto> implements Strategy<QuestionType> {
    /**
     * 根据题目类型转VO
     */
    public List<V> toQuestions(List<QuestionPo> questionPos) {
        if (CollectionUtils.isEmpty(questionPos)) {
            return Collections.emptyList();
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
        question.setQuestionStem(Jackson.parseObject(questionPo.getQuestionStem(), QuestionStem.class));
        toQuestionVo(questionPo, question);
        return question;
    }

    protected Class<V> getQuestionVoClass() {
        return TypeUtils.cast(Reflection.getGenericParameter(this.getClass(), QuestionConverter.class));
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
        questionPo.setId(questionDto.getId());
        questionPo.setMajorId(questionDto.getMajorId());
        questionPo.setQuestionType(questionDto.getQuestionType());
        questionPo.setQuestionStem(Jackson.toJsonString(DataConverter.convert(questionDto.getQuestionStems(), QuestionStem::new)));
        questionPo.setPoolId(questionDto.getPoolId());
        questionPo.setDifficultyLevel(questionDto.getDifficultyLevel());
        questionPo.setTags(questionDto.getTags());
        questionPo.setAnswerAnalysis(questionDto.getAnswerAnalysis());
        questionPo.setRemark(questionDto.getRemark());
        if (null == questionDto.getTags()) {
            questionPo.setTags(Constants.BLANK_STR);
        }
        if (null == questionDto.getAnswerAnalysis()) {
            questionPo.setAnswerAnalysis(Constants.BLANK_STR);
        }
        if (null == questionDto.getRemark()) {
            questionPo.setRemark(Constants.BLANK_STR);
        }
        toQuestionPo(questionDto, questionPo);
        return questionPo;
    }

    /**
     * @Description 题目子类扩展的属性PO
     */
    protected abstract void toQuestionPo(D questionDto, QuestionPo questionPo);
}
