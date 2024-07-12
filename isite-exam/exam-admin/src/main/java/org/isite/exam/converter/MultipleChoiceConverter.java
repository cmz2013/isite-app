package org.isite.exam.converter;

import org.isite.commons.lang.Key;
import org.isite.exam.po.QuestionPo;
import org.isite.exam.data.dto.MultipleChoiceDto;
import org.isite.exam.data.vo.MultipleChoice;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

import static org.isite.commons.lang.json.Jackson.parseArray;
import static org.isite.commons.lang.json.Jackson.toJsonString;
import static org.isite.exam.data.constants.ExamConstants.QUESTION_MULTIPLE_CHOICE;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = QUESTION_MULTIPLE_CHOICE)
public class MultipleChoiceConverter extends
        ObjectiveQuestionConverter<MultipleChoice, MultipleChoiceDto, Set<Integer>> {

    @Override
    protected Set<Integer> toRightAnswer(QuestionPo questionPo) {
        return new HashSet<>(parseArray(questionPo.getRightAnswer(), Integer.class));
    }

    /**
     * 多选题正确答案转PO
     */
    @Override
    protected String toRightAnswer(MultipleChoiceDto questionDto) {
        return toJsonString(questionDto.getRightAnswer());
    }
}
