package com.wxy.sams.service.impl;

import com.wxy.sams.mapper.ManagerMapper;
import com.wxy.sams.mapper.ManagerRoleMapper;
import com.wxy.sams.model.Manager;
import com.wxy.sams.service.ManagerService;
import com.wxy.sams.util.ManagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService , UserDetailsService {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private ManagerRoleMapper managerRoleMapper;

    @Override
    public void update(Manager manager) {
        managerMapper.update(manager);
    }

    @Override
    public Integer delete(int mid) {
        return managerMapper.delete(mid);
    }


    @Override
    public Manager findById(String mid) {
        return managerMapper.findById(mid);
    }


    @Override
    public List<Manager> getAllManagers(String keywords) {
        List<Manager> allManagers = managerMapper.getAllManagers(ManagerUtils.getCurrentManager().getMid(),keywords);
        allManagers.forEach(manager -> {
            manager.setPassword("");
        });
        return allManagers;
    }

    @Override
    public int updateManager(Manager manager) {
        return managerMapper.update(manager);
    }

    @Override
    @Transactional
    public boolean updateManagerRole(Integer mid, Integer[] rids) {
        managerRoleMapper.deleteByManagerId(mid);
        return managerRoleMapper.addRole(mid,rids) == rids.length;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Manager manager = managerMapper.findByName(userName);
        if(manager == null){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        manager.setRoles(managerMapper.getManagerRoleById(manager.getMid()));
        return manager;
    }
}
