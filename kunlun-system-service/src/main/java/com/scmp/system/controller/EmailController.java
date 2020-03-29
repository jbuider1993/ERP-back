package com.scmp.system.controller;

import com.scmp.system.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class EmailController {
    private Logger log = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public Object email() {
        // 简单邮件
//        mailService.sendSimpleMail("244354919@qq.com", "Simple Email", "Hello! This is email content!!!");

        // html模板邮件
        String html = "<html><body><div>Hello! This is html email content!!! 这是验证邮件,请点击下面的链接完成验证,<br/><a href='http://www.baidu.com'>激活账号</a></div></body></html>";
        mailService.sendHtmlMail("244354919@qq.com", "Html Email", html);

        // 附件邮件
//        String filePath = "D:\\QRCodeB.jpg";
//        mailService.sendAttachmentMail("244354919@qq.com", "Attachment Email", "Hello! This is attachment email content!!!", filePath);

        // 静态资源邮件
//        String resId = "res001";
//        String imgPath = "D:\\QRCodeB.jpg";
//        String content = "<html><body><div>Hello! This is resource email content: <img src=\'cid:" + resId + "\'></img></div></body></html>";
//        mailService.sendResourceMail("244354919@qq.com", "Resource Email", content, imgPath, resId);
        return "success";
    }
}
