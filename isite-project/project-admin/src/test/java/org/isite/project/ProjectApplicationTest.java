package org.isite.project;

import org.isite.commons.cloud.data.vo.Result;
import org.isite.commons.web.ftp.FtpClient;
import org.isite.misc.data.vo.FileRecord;
import org.isite.misc.file.FtpFileHandler;
import org.isite.misc.file.Parser;
import org.isite.misc.file.XmlParser;
import org.isite.project.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.util.concurrent.Executor;

import static java.lang.Thread.sleep;
import static org.isite.commons.lang.json.Jackson.toJsonString;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ProjectApplication.class)
class ProjectApplicationTest {

    @Autowired
    private FtpClient ftpClient;
    @Autowired
    private DemoService demoService;
    @Autowired
    private Executor executor;

    @Test
    void testFtpFile() throws Exception {
        System.out.println("testFtpFile ------ start");
        FtpFileHandler ftpFileHandler = new FtpFileHandler(executor, ftpClient);
        Parser<?> parser = new XmlParser<Result<?>>() {
            @Override
            protected boolean handle(Result<?> data) {
                System.out.println("Result: " + toJsonString(data));
                return true;
            }
        };
        FileRecord fileRecord = ftpFileHandler.uploadFile(
                "test中文上传下载.xml",
                new FileInputStream("D:/Work/test中文上传下载.xml"),
                "/test", parser);
        assertNotNull(fileRecord);
        System.out.println("uploadFile: " + toJsonString(fileRecord));
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
