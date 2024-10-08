package org.isite.exam.controller;

import com.github.pagehelper.Page;
import org.isite.commons.cloud.data.dto.PageRequest;
import org.isite.commons.cloud.data.op.Add;
import org.isite.commons.cloud.data.op.Update;
import org.isite.commons.cloud.data.vo.PageResult;
import org.isite.commons.cloud.data.vo.Result;
import org.isite.commons.web.controller.BaseController;
import org.isite.exam.converter.QuestionConverterFactory;
import org.isite.exam.data.dto.MultipleChoiceDto;
import org.isite.exam.data.dto.QuestionGetDto;
import org.isite.exam.data.dto.SingleChoiceDto;
import org.isite.exam.data.vo.Question;
import org.isite.exam.po.QuestionPo;
import org.isite.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;
import static org.isite.commons.cloud.converter.PageQueryConverter.toPageQuery;
import static org.isite.exam.data.constants.UrlConstants.URL_EXAM;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@RestController
public class QuestionController extends BaseController {

    private QuestionService questionService;
    private QuestionConverterFactory questionConverterFactory;

    /**
     * 新增多选题目
     */
    @PostMapping(URL_EXAM + "/question/choice/multiple")
    public Result<Integer> addMultipleChoice(
            @Validated(Add.class) @RequestBody MultipleChoiceDto questionDto) {
        return toResult(questionService.insert(questionConverterFactory.get(questionDto.getQuestionType())
                .toQuestionPo(questionDto)));
    }

    /**
     * 更新多选题目
     */
    @PutMapping(URL_EXAM + "/question/choice/multiple")
    public Result<Integer> updateMultipleChoice(
            @Validated(Update.class) @RequestBody MultipleChoiceDto questionDto) {
        return toResult(questionService.updateById(questionConverterFactory.get(questionDto.getQuestionType())
                .toQuestionPo(questionDto)));
    }

    /**
     * 新增单选题和判断题
     */
    @PostMapping(URL_EXAM + "/question/choice/single")
    public Result<Integer> addSingleChoice(@Validated(Add.class) @RequestBody SingleChoiceDto questionDto) {
        return toResult(questionService.insert(questionConverterFactory.get(
                questionDto.getQuestionType()).toQuestionPo(questionDto)));
    }

    /**
     * 更新单选题和判断题
     */
    @PutMapping(URL_EXAM + "/question/choice/single")
    public Result<Integer> updateSingleChoice(
            @Validated(Update.class) @RequestBody SingleChoiceDto questionDto) {
        return toResult(questionService.updateById(questionConverterFactory.get(
                questionDto.getQuestionType()).toQuestionPo(questionDto)));
    }

    /**
     * 查询题目列表
     */
    @GetMapping(URL_EXAM + "/question/list")
    public PageResult<Question> findQuestions(PageRequest<QuestionGetDto> request) {
        try (Page<QuestionPo> page = questionService.findPage(toPageQuery(request, QuestionPo::new))) {
            return toPageResult(request, page.getResult().stream().map(po -> questionConverterFactory.get(
                    po.getQuestionType()).toQuestion(po)).collect(toList()), page.getTotal());
        }
    }

    /**
     * 根据ID查询题目
     */
    @GetMapping(URL_EXAM + "/question/{id}")
    public Result<Question> getQuestion(@PathVariable("id") Long id) {
        QuestionPo questionPo = questionService.get(id);
        return toResult(questionConverterFactory.get(questionPo.getQuestionType()).toQuestion(questionPo));
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setQuestionConverterFactory(QuestionConverterFactory questionConverterFactory) {
        this.questionConverterFactory = questionConverterFactory;
    }
}
