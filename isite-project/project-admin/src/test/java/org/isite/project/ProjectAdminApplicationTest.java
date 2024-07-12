package org.isite.project;

import org.isite.commons.file.client.FtpClient;
import org.isite.commons.file.parse.Parser;
import org.isite.commons.file.parse.XmlParser;
import org.isite.commons.lang.data.Result;
import org.isite.project.api.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

import static java.lang.Integer.parseInt;
import static org.isite.commons.cloud.PropertyUtils.getProperty;
import static org.isite.commons.lang.json.Jackson.toJsonString;

@SpringBootTest(classes = ProjectApplication.class)
public class ProjectAdminApplicationTest {

    @Autowired
    private Executor executor;

    @Autowired
    private DemoService demoService;

    @Test
    void testFtpFile() throws IOException, InterruptedException {
        System.out.println("testFtpFile ------ start");
        FtpClient client = new FtpClient(
                getProperty("ftp.host"), parseInt(getProperty("ftp.port")),
                getProperty("ftp.username"), getProperty("ftp.password"));

        Parser parser = new XmlParser<Result<?>>(client) {
            @Override
            protected boolean handle(Result<?> data) {
                System.out.println("Result: " + toJsonString(data));
                return true;
            }
        };
        client.setExecutor(executor);
        client.setParser(parser);

        System.out.println("uploadFile: " + client.upload(
                "test中文上传下载.xml",
                new FileInputStream("D:/Work/test中文上传下载.xml"),
                "/test"));

        System.out.println("testFtpFile ------ end");
        Thread.sleep(6000);
    }

    @Test
    public void testCallback() throws InterruptedException {
        demoService.callback();
        //等待异步任务完成：发送告警邮件
        Thread.sleep(60000);
    }
}
