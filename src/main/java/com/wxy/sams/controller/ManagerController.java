package com.wxy.sams.controller;

import com.wxy.sams.model.Manager;
import com.wxy.sams.model.Msg;
import com.wxy.sams.model.RespBean;
import com.wxy.sams.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ManagerController {

    @Autowired
    private ManagerServiceImpl managerService;

    private Msg msg;

    private Manager manager;


    /**
     * 我的信息  页面
     * @param model
     * @return
     */
    @RequestMapping("/myInfo")
    public RespBean myInfo(Model model){
        model.addAttribute("manager",manager);
        return RespBean.ok("ok",manager);
    }

    /**
     * 更新 我的信息 页面
     * @param model
     * @param manager
     * @return
     */
    @RequestMapping("/updateInfo")
    public RespBean updateMyInfo(Model model,Manager manager){
        if(managerService.isExistsByPhone(manager.getMphone())){
            model.addAttribute("msg1","该手机号已经被其他账号注册");
            return RespBean.error("该手机号已经被其他账号注册");
        }else if(managerService.isExistsByEmail(manager.getMemail())){
            model.addAttribute("msg2","该邮箱已经被其他账号注册");
            return RespBean.error("该邮箱已经被其他账号注册");
        }else {
            managerService.update(manager);
            this.manager = manager;
            model.addAttribute("manager", manager);
            return RespBean.ok("ok",manager);
        }
    }


}
