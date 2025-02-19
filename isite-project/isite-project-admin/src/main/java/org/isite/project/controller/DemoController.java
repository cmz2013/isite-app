package org.isite.project.controller;

import org.isite.commons.cloud.data.constants.UrlConstants;
import org.isite.commons.web.sign.Signed;
import org.isite.project.data.constants.ProjectUrls;
import org.isite.project.data.dto.DemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Description 本地服务示例
 * @Author <font color='blue'>zhangcm</font>
 */
@RestController
public class DemoController {
    private DemoHandler demoHandler;

    @Signed
    @PostMapping(UrlConstants.URL_API + ProjectUrls.URL_PROJECT + "/demo")
    public String demo(@RequestBody DemoDto demoDto) {
        return demoHandler.handle(demoDto);
    }

    @Autowired
    public void setDemoHandler(DemoHandler demoHandler) {
        this.demoHandler = demoHandler;
    }
}
