package org.isite.project;

import org.isite.commons.lang.data.Result;
import org.isite.commons.lang.ftp.FtpClient;
import org.isite.commons.lang.ftp.FtpProperties;
import org.isite.misc.data.dto.FileRecordDto;
import org.isite.misc.file.FtpFileHandler;
import org.isite.misc.file.Parser;
import org.isite.misc.file.XmlParser;
import org.isite.project.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.util.concurrent.Executor;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;
import static org.isite.commons.cloud.utils.PropertyUtils.getProperty;
import static org.isite.commons.lang.json.Jackson.toJsonString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ProjectApplication.class)
class ProjectApplicationTest {

    @Autowired
    private DemoService demoService;
    @Autowired
    private Executor executor;

    @Test
    void testFtpFile() throws Exception {
        System.out.println("testFtpFile ------ start");
        FtpProperties ftpProperties = new FtpProperties(getProperty("ftp.host"), parseInt(getProperty("ftp.port")),
                getProperty("ftp.username"), getProperty("ftp.password"));
        FtpClient ftpClient = new FtpClient(ftpProperties);
        FtpFileHandler ftpFileHandler = new FtpFileHandler(executor, ftpClient);
        Parser<?> parser = new XmlParser<Result<?>>() {
            @Override
            protected boolean handle(Result<?> data) {
                System.out.println("Result: " + toJsonString(data));
                return true;
            }
        };
        FileRecordDto fileRecordDto = ftpFileHandler.uploadFile(
                "test中文上传下载.xml",
                new FileInputStream("D:/Work/test中文上传下载.xml"),
                "/test", parser);
        assertNotNull(fileRecordDto);
        System.out.println("uploadFile: " + toJsonString(fileRecordDto));
        System.out.println("testFtpFile ------ end");
        //等待异步任务完成
        sleep(6000);
    }

    @Test
    void testCallback() throws InterruptedException {
        demoService.callback();
        //等待异步任务完成：发送告警邮件
        sleep(60000);
    }
}
