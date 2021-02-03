package com.wxy.sams.controller.config;

import com.wxy.sams.model.Menu;
import com.wxy.sams.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统配置Controller
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public List<Menu> getMenuById(){
        return menuService.getMenuById();
    }
}
