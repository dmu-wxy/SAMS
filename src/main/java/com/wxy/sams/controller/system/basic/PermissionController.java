package com.wxy.sams.controller.system.basic;

import com.wxy.sams.model.Menu;
import com.wxy.sams.model.RespBean;
import com.wxy.sams.model.Role;
import com.wxy.sams.service.MenuService;
import com.wxy.sams.service.RoleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限组
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissionController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/mids/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        return menuService.getMidByRid(rid);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        if(menuService.updateMenuRole(rid,mids)){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        if(roleService.addRole(role) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleById(@PathVariable Integer rid){
        if(roleService.deleteRoleById(rid) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败!");
    }
}
