package org.isite.project.api;

import lombok.SneakyThrows;
import org.isite.data.callback.JsonCallback;
import org.isite.project.data.dto.DemoDto;
import org.isite.project.data.vo.DemoResult;
import org.springframework.stereotype.Component;

import static org.isite.commons.lang.data.ResultUtils.isOk;
import static org.isite.commons.lang.json.Jackson.toJsonString;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Component
public class DemoCallback extends JsonCallback<DemoDto, DemoResult> {

    public DemoCallback() {
        super(result -> {
            System.out.println("DemoCallback: " + toJsonString(result));
            return isOk(result);
        });
    }

    @SneakyThrows
    public void call(DemoDto reqData) {
        this.execute(getDataApi("2"), reqData);
    }
}
