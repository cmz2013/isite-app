package org.isite.project.api;

import org.isite.commons.web.sign.Signature;
import org.isite.project.data.dto.DemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本地服务示例
 * @author <font color='blue'>zhangcm</font>
 */
@RestController
public class DemoController {

    private DemoHandler demoHandler;

    @Signature
    @PostMapping("/api/demo")
    public String demo(@RequestBody DemoDto demoDto) {
        return demoHandler.handle(demoDto);
    }

    @Autowired
    public void setDemoHandler(DemoHandler demoHandler) {
        this.demoHandler = demoHandler;
    }
}
