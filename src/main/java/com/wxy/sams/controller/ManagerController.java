package com.wxy.sams.controller;

import com.wxy.sams.model.Manager;
import com.wxy.sams.model.Msg;
import com.wxy.sams.service.impl.ManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
    public String login(String account, String password, Model model, HttpServletResponse response, HttpServletRequest request){
        System.out.println(account + "..." + password);
        Manager manager = managerService.findManager(account);
        if(manager == null){
            model.addAttribute("msg","账号不存在");
            return "login";
        }else{
            if(!password.equals(manager.getPassword())){
                model.addAttribute("msg","密码错误");
                return "login";
            }else{
                model.addAttribute("manager",manager);
                this.manager = manager;
                Cookie cookie = new Cookie("mid",String.valueOf(this.manager.getMid()));
                cookie.setMaxAge(30);
                response.addCookie(cookie);
                return "index";
            }
        }
    }
    @RequestMapping("/registAccount")
    public Msg regist(String account,String password,HttpServletResponse response){
        this.manager = new Manager();
        if(managerService.isExists(account)){
            msg = new Msg(1001,"该账号已经被注册过",null);
        }else {
            this.manager = managerService.insertManager(account, password);
            Cookie cookie = new Cookie("mid", String.valueOf(this.manager.getMid()));
            cookie.setMaxAge(30);
            response.addCookie(cookie);
            msg = new Msg(200, "success",this.manager.toString());
        }
        return msg;
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
