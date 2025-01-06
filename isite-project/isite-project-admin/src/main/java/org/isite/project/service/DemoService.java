package org.isite.project.service;

import org.isite.project.data.dto.DemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 远程调用示例
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class DemoService {

    private DemoCallback demoCallback;

    public void callback() {
        demoCallback.call(new DemoDto("1", "2"));
    }

    @Autowired
    public void setDemoCallback(DemoCallback demoCallback) {
        this.demoCallback = demoCallback;
    }
}
