package org.meteor.sams.controller;

import com.google.code.kaptcha.Producer;
import org.meteor.sams.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {
    @GetMapping("/login")
    @CrossOrigin(originPatterns = "*")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录！");
    }

    @Autowired
    Producer producer;

    @GetMapping("/verifyCode")
    public void getVerifyCode(HttpServletResponse response, HttpSession session) throws IOException{
        response.setContentType("image/jpeg");
        String text = producer.createText();
        session.setAttribute("verify_code",text);
        BufferedImage image = producer.createImage(text);
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image,"jpg",out);
        }
    }
}
