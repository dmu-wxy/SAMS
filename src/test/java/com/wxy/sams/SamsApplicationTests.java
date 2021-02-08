package com.wxy.sams;

import com.wxy.sams.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@SpringBootTest
class SamsApplicationTests {

    @Autowired
    public ManagerService managerService;

    @Autowired
    JavaMailSender javaMailSender;



    @Test
    void createPassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }

    @Test
    void sendMail(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("这是主题");
        msg.setText("这是内容");
        msg.setFrom("2290502632@qq.com");
        msg.setSentDate(new Date());
        msg.setTo("15965076221@163.com");
        javaMailSender.send(msg);
    }

    @Test
    void sendMailWithPhone() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这封邮件带有图片");
        helper.setFrom("2290502632@qq.com");
        helper.setSentDate(new Date());
        helper.setText("有张图片请接收");
        helper.setTo("15965076221@163.com");
        helper.addAttachment("1.png",new File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\1.png"));
        javaMailSender.send(mimeMessage);
    }

    @Test
    void sendMailWithPictrueInText() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("2290502632@qq.com");
        helper.setSubject("这封邮件正文有图片");
        helper.setText("这里面有图片<br/>这是第一张图片<img src='cid:p01'/><br/>这是第二章图片：<img src='cid:p02'/>",true);
        helper.setTo("15965076221@163.com");
        helper.setSentDate(new Date());
        helper.addInline("p01",new File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\1.png"));
        helper.addInline("p02",new File("C:\\Users\\ASUS\\Pictures\\Saved Pictures\\啦啦啦啦.jpg"));
        javaMailSender.send(msg);
    }

    @Autowired
    TemplateEngine templateEngine;

    @Test
    void sendMailWithThymeleaf() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("2290502632@qq.com");
        helper.setSubject("这是thymeleaf模板做的");
        Context context = new Context();
        context.setVariable("username","meteor");
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        helper.setTo("15965076221@163.com");
        helper.setSentDate(new Date());
        javaMailSender.send(msg);
    }
}
