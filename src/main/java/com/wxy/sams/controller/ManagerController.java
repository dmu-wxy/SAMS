package com.wxy.sams.controller;

import com.wxy.sams.model.Manager;
import com.wxy.sams.service.ManagerService;
import com.wxy.sams.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ManagerController {

    @Autowired
    private ManagerServiceImpl managerService;

    private Manager manager;

    @RequestMapping("/")
    public String interceptor(Model model, @CookieValue(value = "mid",required = false)String mid){
        String toUrl = "index";
        if(mid == null){
            toUrl = "login";
            model.addAttribute("msg","");
        }
        System.out.println(toUrl);
        return toUrl;
    }

    @RequestMapping("/loginAccount")
    public String login(String account, String password, Model model, HttpServletResponse response){
        Manager manager = null;
        if(account != null) {
            if (account.indexOf("@") < 0) {
                //电话
                manager = managerService.findByPhone(account);
            } else {
                //邮箱
                manager = managerService.findByEmail(account);
            }
        }
        if(manager == null){
            model.addAttribute("msg","账号不存在");
            System.out.println("账号(" + account + ")不存在");
            return "login";
        }else{
            System.out.println(password + "--" + manager.getPassword());
            if(!password.equals(manager.getPassword())){
                model.addAttribute("msg","密码错误");
                System.out.println("密码(" + account + ")错误");
                return "login";
            }else{
                model.addAttribute("manager",manager);
                System.out.println("账号(" + account + ")登录成功");
                this.manager = manager;
                Cookie cookie = new Cookie("mid",String.valueOf(this.manager.getMid()));
                cookie.setMaxAge(30);
                response.addCookie(cookie);
                return "index";
            }
        }
    }
    @RequestMapping("/registAccout")
    public String regist(Model model,String account,String password, HttpServletResponse response){
        Manager manager = new Manager();
        this.manager = new Manager();
        if(account != null){
            if(account.indexOf("@") < 0){ //手机
                if (managerService.isExistsByPhone(account)){
                    model.addAttribute("msg","该手机号已经注册过");
                    return "register";
                }
                manager.setMphone(account);
                manager.setPassword(password);
                managerService.insert(manager);
                this.manager = managerService.findByPhone(account);
                Cookie cookie = new Cookie("mid",String.valueOf(this.manager.getMid()));
                cookie.setMaxAge(30);
                response.addCookie(cookie);
            }else {  //邮箱
                if(managerService.isExistsByEmail(account)){
                    model.addAttribute("msg","该邮箱已经注册过");
                    return "register";
                }
                manager.setMemail(account);
                manager.setPassword(password);
                managerService.insert(manager);
                this.manager = managerService.findByEmail(account);
                Cookie cookie = new Cookie("mid",String.valueOf(this.manager.getMid()));
                cookie.setMaxAge(30);
                response.addCookie(cookie);
            }
        }
        return "index";
    }

    /**
     * 我的信息  页面
     * @param model
     * @return
     */
    @RequestMapping("/myInfo")
    public String myInfo(Model model){
        model.addAttribute("manager",manager);
        return "myInfo";
    }

    /**
     * 更新 我的信息 页面
     * @param model
     * @param manager
     * @return
     */
    @RequestMapping("/updateInfo")
    public String updateMyInfo(Model model,Manager manager){
        if(managerService.isExistsByPhone(manager.getMphone())){
            model.addAttribute("msg1","该手机号已经被其他账号注册");
            return "updateMyInfo";
        }else if(managerService.isExistsByEmail(manager.getMemail())){
            model.addAttribute("msg2","该邮箱已经被其他账号注册");
            return "updateMyInfo";
        }else {
            managerService.update(manager);
            this.manager = manager;
            model.addAttribute("manager", manager);
            return "myInfo";
        }
    }

    /**
     * 从 我的页面 跳转到  更新我的信息  页面
     * @param mid
     * @param model
     * @return
     */
    @RequestMapping("/toUpdateMyInfo")
    public String toUpdateMyInfo(String mid,Model model){
        this.manager = managerService.findById(mid);
        model.addAttribute("manager",manager);
        model.addAttribute("msg1","");
        model.addAttribute("msg2","");
        return "updateMyInfo";
    }
}
