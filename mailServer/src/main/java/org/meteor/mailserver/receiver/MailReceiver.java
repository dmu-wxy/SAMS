package org.meteor.mailserver.receiver;

import org.meteor.sams.model.Manager;
import org.meteor.sams.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class MailReceiver {

    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailProperties mailProperties;

    @Autowired
    TemplateEngine templateEngine;

    @RabbitListener(queues = "sams.mail.welcome")
    public void handler(Manager manager){
        logger.info("消息队列>>>>" + manager.toString());
        String to = manager.getMemail();
        if(to == null || to.equals("")) {
            //操作员不是邮箱注册则跳过
            return;
        }

        //todo: 需要添加操作员功能
        //收到消息，发送邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject("欢迎邮件");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("mname",manager.getMname() != null ? manager.getMname() : "");
            context.setVariable("gender",manager.getGender() == 0 ? "男" : "女");
            context.setVariable("birth",manager.getBirth() != null ? manager.getBirth() : "");
            context.setVariable("mphone",manager.getMphone() != null ? manager.getMphone() : "");
            String role = "";
            if(manager.getRoles() != null) {
                for (Role temp : manager.getRoles()) {
                    role += temp.getNameZh() + "/";
                }
            }
            context.setVariable("roleName",role);
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            helper.setTo(to);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("邮件发送失败：" + e.getMessage());
        }

    }
}
