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

    /**
     * 传多个3，测试可重入次数2
     */
    public void callback() {
        demoCallback.call(new DemoDto("SYN", new String[] {"1", "2", "3", "3", "3"}));
    }

    @Autowired
    public void setDemoCallback(DemoCallback demoCallback) {
        this.demoCallback = demoCallback;
    }
}
