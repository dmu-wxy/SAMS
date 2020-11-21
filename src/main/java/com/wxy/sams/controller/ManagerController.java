package com.wxy.sams.controller;

import com.wxy.sams.model.Manager;
import com.wxy.sams.service.ManagerService;
import com.wxy.sams.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {

    @Autowired
    private ManagerServiceImpl managerService;

    private Manager manager;

    @RequestMapping("/loginAccount")
    public String login(String account, String password, Model model){
        //todo: 先判断是邮箱还是电话
        Manager manager = managerService.findByPhone(account);
        if(manager == null){
            model.addAttribute("msg","账号不存在");
            return "redirect:login";
        }else{
            System.out.println(password + "--" + manager.getPassword());
            if(!password.equals(manager.getPassword())){
                model.addAttribute("msg","密码错误");
                return "redirect:login";
            }else{
                model.addAttribute("manager",manager);
                this.manager = manager;
                return "index";
            }
        }
    }
    @RequestMapping("/registAccout")
    public String regist(String account,String password){
        //todo: 判断手机号还是邮箱，查看是否注册过
        Manager manager = new Manager();
        manager.setMphone(account);
        manager.setPassword(password);
        managerService.insert(manager);
        this.manager = manager;
        return "index";
    }
    @RequestMapping("/myInfo")
    public String myInfo(Model model){
        model.addAttribute("manager",manager);
        return "myInfo";
    }

    @RequestMapping("/updateInfo")
    public String updateMyInfo(Model model,Manager manager){
        managerService.update(manager);
        this.manager = manager;
        model.addAttribute("manager",manager);
        return "myInfo";
    }
    @RequestMapping("/toUpdateMyInfo")
    public String toUpdateMyInfo(String mid,Model model){
        this.manager = managerService.findById(mid);
        model.addAttribute("manager",manager);
        return "updateMyInfo";
    }
}
