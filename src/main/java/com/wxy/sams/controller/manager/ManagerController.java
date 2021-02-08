package com.wxy.sams.controller.manager;

import com.wxy.sams.mapper.RoleMapper;
import com.wxy.sams.model.Manager;
import com.wxy.sams.model.RespBean;
import com.wxy.sams.model.Role;
import com.wxy.sams.service.ManagerService;
import com.wxy.sams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager/info")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Manager> getAllManagers(String keywords){
        return managerService.getAllManagers(keywords);
    }

    @PutMapping("/")
    public RespBean updateManager(@RequestBody Manager manager){
        if(managerService.updateManager(manager) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PutMapping("/roles")
    public RespBean updateManagerRole(Integer mid,Integer[] rids){
        if(managerService.updateManagerRole(mid,rids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteManagerById(@PathVariable Integer id){
        if(managerService.delete(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败！");
    }
}
