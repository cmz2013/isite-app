package org.isite.exam.controller;

import org.isite.commons.cloud.converter.DataConverter;
import org.isite.commons.cloud.data.op.Add;
import org.isite.commons.cloud.data.op.Update;
import org.isite.commons.cloud.data.vo.Result;
import org.isite.commons.web.controller.BaseController;
import org.isite.exam.data.constants.ExamUrls;
import org.isite.exam.data.dto.ExamSceneDto;
import org.isite.exam.po.ExamScenePo;
import org.isite.exam.service.ExamSceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Description 考试场景 Controller
 * @Author <font color='blue'>zhangcm</font>
 */
@RestController
public class ExamSceneController extends BaseController {
    private ExamSceneService examSceneService;

    /**
     * 新增考试场景
     */
    @PostMapping(ExamUrls.URL_EXAM + "/scene")
    public Result<Integer> addExamScene(@Validated(Add.class) @RequestBody ExamSceneDto sceneDto) {
        return toResult(examSceneService.insert(DataConverter.convert(sceneDto, ExamScenePo::new)));
    }

    /**
     * 更新考试场景
     */
    @PutMapping(ExamUrls.URL_EXAM + "/scene")
    public Result<Integer> updateExamScene(@Validated(Update.class) @RequestBody ExamSceneDto sceneDto) {
        return toResult(examSceneService.updateById(DataConverter.convert(sceneDto, ExamScenePo::new)));
    }

    @Autowired
    public void setExamSceneService(ExamSceneService examSceneService) {
        this.examSceneService = examSceneService;
    }
}
