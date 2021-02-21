package org.meteor.sams.service;

import org.meteor.sams.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();

    public Integer addRole(Role role);

    public Integer updateRole(Role role);

    public Integer deleteRoleById(Integer id);

    public Integer deleteRolesByIds(Integer[] ids);
}
