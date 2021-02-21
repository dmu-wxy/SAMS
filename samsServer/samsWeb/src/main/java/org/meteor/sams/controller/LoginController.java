package org.meteor.sams.controller;

import org.meteor.sams.model.RespBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    @CrossOrigin("*")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录！");
    }

}
