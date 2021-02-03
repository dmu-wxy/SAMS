package com.wxy.sams.controller.system.basic;

import com.wxy.sams.model.RespBean;
import com.wxy.sams.model.Role;
import com.wxy.sams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role){
        if(roleService.updateRole(role) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteRole(@PathVariable Integer id){
        if(roleService.deleteRoleById(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @DeleteMapping("/")
    public RespBean deleteRolesByIds(Integer[] ids){
        if(roleService.deleteRolesByIds(ids) == ids.length){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
