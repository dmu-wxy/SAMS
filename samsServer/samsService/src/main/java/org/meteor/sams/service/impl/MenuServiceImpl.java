package org.meteor.sams.service.impl;

import org.meteor.sams.mapper.MenuMapper;
import org.meteor.sams.mapper.MenuRoleMapper;
import org.meteor.sams.model.Manager;
import org.meteor.sams.model.Menu;
import org.meteor.sams.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    public List<Menu> getMenuById() {
        Integer id = ((Manager)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getMid();
        return id != null ? menuMapper.getMenuById(id) : null;
    }

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    @Override
    public List<Integer> getMidByRid(Integer rid) {
        return menuMapper.getMidByRid(rid);
    }

    @Override
    @Transactional
    public boolean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.deleteByRid(rid);
        Integer result = menuRoleMapper.insertRecords(rid,mids);
        return result == mids.length;
    }
}
