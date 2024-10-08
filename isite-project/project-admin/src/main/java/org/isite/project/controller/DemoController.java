package org.isite.project.controller;

import org.isite.commons.web.sign.Signed;
import org.isite.project.data.dto.DemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.isite.commons.cloud.data.constants.UrlConstants.URL_API;
import static org.isite.project.data.constants.UrlConstants.URL_PROJECT;

/**
 * @Description 本地服务示例
 * @Author <font color='blue'>zhangcm</font>
 */
@RestController
public class DemoController {

    private DemoHandler demoHandler;

    @Signed
    @PostMapping(URL_API + URL_PROJECT + "/demo")
    public String demo(@RequestBody DemoDto demoDto) {
        return demoHandler.handle(demoDto);
    }

    @Autowired
    public void setDemoHandler(DemoHandler demoHandler) {
        this.demoHandler = demoHandler;
    }
}
