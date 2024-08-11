package org.isite.project;

import org.isite.commons.lang.data.Result;
import org.isite.misc.file.FtpClient;
import org.isite.misc.file.Parser;
import org.isite.misc.file.XmlParser;
import org.isite.project.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
import static org.isite.commons.cloud.utils.PropertyUtils.getProperty;
import static org.isite.commons.lang.json.Jackson.toJsonString;

@SpringBootTest(classes = ProjectApplication.class)
public class ProjectApplicationTest {

    @Autowired
    private DemoService demoService;

    @Test
    void testFtpFile() throws IOException, InterruptedException {
        System.out.println("testFtpFile ------ start");
        FtpClient client = new FtpClient(
                getProperty("ftp.host"), parseInt(getProperty("ftp.port")),
                getProperty("ftp.username"), getProperty("ftp.password"));

        Parser<?> parser = new XmlParser<Result<?>>() {
            @Override
            protected boolean handle(Result<?> data) {
                System.out.println("Result: " + toJsonString(data));
                return true;
            }
        };
        client.setParser(parser);

        System.out.println("uploadFile: " + client.upload(
                "test中文上传下载.xml",
                new FileInputStream("D:/Work/test中文上传下载.xml"),
                "/test"));

        System.out.println("testFtpFile ------ end");
        sleep(6000);
    }

    @Test
    void testCallback() throws InterruptedException {
        demoService.callback();
        //等待异步任务完成：发送告警邮件
        sleep(60000);
    }
}
