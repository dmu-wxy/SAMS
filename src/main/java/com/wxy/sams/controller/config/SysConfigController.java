package com.wxy.sams.controller.config;

import com.wxy.sams.model.Animal;
import com.wxy.sams.model.Menu;
import com.wxy.sams.model.RespPageBean;
import com.wxy.sams.service.AnimalService;
import com.wxy.sams.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private AnimalService animalService;

    @GetMapping("/menu")
    public List<Menu> getMenuById(){
        return menuService.getMenuById();
    }

    @GetMapping("/animal")
    public RespPageBean getAnimalByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size){
        Animal animal = new Animal();
        animal.setAname("");
        return animalService.getAnimalByPage(page,size,animal,null);
    }

}
