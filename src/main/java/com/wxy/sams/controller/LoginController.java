package com.wxy.sams.controller;

import com.wxy.sams.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录！");
    }

    @GetMapping("test")
    public RespBean test(){
        return RespBean.ok("登录成功！");
    }
}
