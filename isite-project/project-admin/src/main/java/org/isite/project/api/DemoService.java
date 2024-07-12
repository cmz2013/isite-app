package org.isite.project.api;

import org.isite.project.data.dto.DemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 远程调用示例
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class DemoService {

    private DemoCallback demoCallback;

    /**
     * 传多个3，测试可重入次数2
     * @see DemoHandler#handle(DemoDto)
     */
    public void callback() {
        demoCallback.call(new DemoDto("SYN", new String[] {"1", "2", "3", "3", "3"}));
    }

    @Autowired
    public void setDemoCallback(DemoCallback demoCallback) {
        this.demoCallback = demoCallback;
    }
}
