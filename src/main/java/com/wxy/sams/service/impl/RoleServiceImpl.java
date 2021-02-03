package com.wxy.sams.service.impl;

import com.wxy.sams.mapper.RoleMapper;
import com.wxy.sams.model.RespBean;
import com.wxy.sams.model.Role;
import com.wxy.sams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getAllRoles() {
        return roleMapper.getAllRoles();
    }

    @Override
    public Integer addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public Integer updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public Integer deleteRoleById(Integer id) {
        return roleMapper.deleteRoleById(id);
    }

    @Override
    public Integer deleteRolesByIds(Integer[] ids) {
        return roleMapper.deleteRolesByIds(ids);
    }
}
