package org.isite.exam.controller;

import org.isite.commons.lang.data.Result;
import org.isite.commons.web.controller.BaseController;
import org.isite.commons.web.data.op.Update;
import org.isite.commons.web.exception.OverstepAccessError;
import org.isite.exam.data.dto.ExamRecordDto;
import org.isite.exam.data.vo.ExamRecord;
import org.isite.exam.data.vo.UserAnswer;
import org.isite.exam.service.ExamRecordService;
import org.isite.exam.service.ExamSceneService;
import org.isite.exam.service.OnlineExamService;
import org.isite.misc.data.enums.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.isite.commons.lang.Assert.isTrue;
import static org.isite.commons.web.config.WebMvcAdapter.URL_MY;
import static org.isite.commons.web.data.Converter.convert;
import static org.isite.exam.converter.ExamSceneConverter.toExamScenePo;
import static org.isite.exam.data.constants.UrlConstants.URL_EXAM;
import static org.isite.security.support.utils.SecurityUtils.getOauthUser;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@RestController
public class OnlineExamController extends BaseController {

    private ExamSceneService examSceneService;
    private OnlineExamService onlineExamService;
    private ExamRecordService examRecordService;

    /**
     * @Description 查询未结束的考试记录，不存在时创建考试记录，用于开始考试。
     * 注意：在考试场景中，objectType和objectValue不需要组合唯一
     */
    @PostMapping(URL_MY + URL_EXAM + "/scene/{sceneId}")
    public Result<ExamRecord> applyExam(@PathVariable("sceneId") Integer sceneId) {
        return toResult(onlineExamService.applyExam(examSceneService.get(sceneId), getOauthUser()));
    }

    /**
     * @Description 查询未结束的考试记录，不存在时创建考试记录，用于开始考试。
     * 注意：在考试场景中，objectType和objectValue必须组合唯一
     */
    @PostMapping(URL_MY + URL_EXAM + "/object/{objectType}/{objectValue}")
    public Result<ExamRecord> applyExam(
            @PathVariable("objectType") ObjectType objectType, @PathVariable("objectValue") String objectValue) {
        return toResult(onlineExamService.applyExam(
                examSceneService.findOne(toExamScenePo(objectType, objectValue)), getOauthUser()));
    }

    /**
     * 提交考卷
     */
    @PutMapping(URL_MY + URL_EXAM + "/submit")
    public Result<Integer> submitExam(@RequestBody @Validated(Update.class) ExamRecordDto examRecordDto) {
        isTrue(examRecordService.get(examRecordDto.getId()).getUserId().equals(getOauthUser().getUserId()),
                new OverstepAccessError());
        return toResult(onlineExamService.submitExam(examRecordDto.getId(),
                convert(examRecordDto.getUserAnswers(), UserAnswer::new)));
    }

    @Autowired
    public void setExamSceneService(ExamSceneService examSceneService) {
        this.examSceneService = examSceneService;
    }

    @Autowired
    public void setOnlineExamService(OnlineExamService onlineExamService) {
        this.onlineExamService = onlineExamService;
    }

    @Autowired
    public void setExamRecordService(ExamRecordService examRecordService) {
        this.examRecordService = examRecordService;
    }
}
