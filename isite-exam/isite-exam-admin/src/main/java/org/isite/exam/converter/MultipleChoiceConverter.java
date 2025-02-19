package org.isite.exam.converter;

import org.isite.commons.lang.json.Jackson;
import org.isite.exam.data.dto.MultipleChoiceDto;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.MultipleChoice;
import org.isite.exam.po.QuestionPo;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class MultipleChoiceConverter extends
        ObjectiveQuestionConverter<MultipleChoice, MultipleChoiceDto, Set<Integer>> {

    @Override
    protected Set<Integer> toRightAnswer(QuestionPo questionPo) {
        return new HashSet<>(Jackson.parseArray(questionPo.getRightAnswer(), Integer.class));
    }

    /**
     * 多选题正确答案转PO
     */
    @Override
    protected String toRightAnswer(MultipleChoiceDto questionDto) {
        return Jackson.toJsonString(questionDto.getRightAnswer());
    }

    @Override
    public QuestionType[] getIdentities() {
        return new QuestionType[] {QuestionType.MULTIPLE_CHOICE};
    }
}
