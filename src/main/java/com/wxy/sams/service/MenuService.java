package com.wxy.sams.service;

import com.wxy.sams.model.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MenuService {
    public List<Menu> getMenuById();

    public List<Menu> getAllMenusWithRole();
}
