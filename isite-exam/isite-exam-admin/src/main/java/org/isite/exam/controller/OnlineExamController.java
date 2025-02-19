package org.isite.exam.controller;

import org.isite.commons.cloud.converter.DataConverter;
import org.isite.commons.cloud.data.constants.UrlConstants;
import org.isite.commons.cloud.data.vo.Result;
import org.isite.commons.lang.Assert;
import org.isite.commons.web.controller.BaseController;
import org.isite.commons.web.exception.OverstepAccessError;
import org.isite.commons.web.interceptor.TransmittableHeaders;
import org.isite.exam.converter.ExamSceneConverter;
import org.isite.exam.data.constants.ExamUrls;
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
    @PostMapping(UrlConstants.URL_MY + ExamUrls.URL_EXAM + "/scene/{examSceneId}")
    public Result<ExamRecord> applyExam(@PathVariable("examSceneId") Integer examSceneId) {
        return toResult(onlineExamService.applyExam(examSceneService.get(examSceneId),
                TransmittableHeaders.getTenantId(), TransmittableHeaders.getUserId()));
    }

    /**
     * @Description 查询未结束的考试记录，不存在时创建考试记录，用于开始考试。
     * 注意：在考试场景中，objectType和objectValue必须组合唯一
     */
    @PostMapping(UrlConstants.URL_MY + ExamUrls.URL_EXAM + "/object/{objectType}/{objectValue}")
    public Result<ExamRecord> applyExam(
            @PathVariable("objectType") ObjectType objectType, @PathVariable("objectValue") String objectValue) {
        return toResult(onlineExamService.applyExam(examSceneService.findOne(ExamSceneConverter.toExamScenePo(
                objectType, objectValue)), TransmittableHeaders.getTenantId(), TransmittableHeaders.getUserId()));
    }

    /**
     * 提交考卷
     */
    @PutMapping(UrlConstants.URL_MY + ExamUrls.URL_EXAM + "/submit")
    public Result<Integer> submitExam(@RequestBody @Validated ExamRecordDto examRecordDto) {
        Assert.isTrue(examRecordService.get(examRecordDto.getId()).getUserId().equals(
                TransmittableHeaders.getUserId()), new OverstepAccessError());
        return toResult(onlineExamService.submitExam(examRecordDto.getId(),
                DataConverter.convert(examRecordDto.getUserAnswers(), UserAnswer::new)));
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
