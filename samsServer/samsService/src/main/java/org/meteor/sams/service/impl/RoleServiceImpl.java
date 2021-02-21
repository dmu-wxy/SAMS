package org.meteor.sams.service.impl;

import org.meteor.sams.mapper.RoleMapper;
import org.meteor.sams.model.Role;
import org.meteor.sams.service.RoleService;
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
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_" + role.getName());
        }
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
