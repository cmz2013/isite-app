package org.isite.project.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.isite.commons.web.sync.Lock;
import org.isite.commons.web.sync.Synchronized;
import org.isite.data.handler.JsonHandler;
import org.isite.project.data.dto.DemoDto;
import org.isite.project.data.vo.DemoResult;
import org.isite.project.data.vo.DemoVo;
import org.springframework.stereotype.Component;

import static org.isite.commons.cloud.data.Converter.convert;
import static org.springframework.http.HttpStatus.OK;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Slf4j
@Component
public class DemoHandler extends JsonHandler<DemoDto, DemoVo> {

    public DemoHandler() {
        super(reqData -> {
            DemoResult result = new DemoResult();
            reqData.setBody("ACK");
            result.setData(convert(reqData, DemoVo::new));
            result.setCode(OK.value());
            return result;
        });
    }

    /**
     * 在Service层细粒度加锁
     * @param demoDto 请求参数
     * @return 响应数据
     */
    @SneakyThrows
    @Synchronized(locks = {
            @Lock(name = "demon:body:${body}:${arg0}",
                    keys = {"#demoDto", "#demoDto.keys"}, reentry = 2)
    })
    public String handle(DemoDto demoDto) {
        return handle(getDataApi("1"), demoDto);
    }
}
