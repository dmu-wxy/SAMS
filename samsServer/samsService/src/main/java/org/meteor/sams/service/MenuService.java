package org.meteor.sams.service;

import org.meteor.sams.model.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> getMenuById();

    public List<Menu> getAllMenusWithRole();

    public List<Menu> getAllMenus();

    public List<Integer> getMidByRid(Integer rid);

    public boolean updateMenuRole(Integer rid, Integer[] mids);
}
