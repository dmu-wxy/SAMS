package com.wxy.sams.service.impl;

import com.wxy.sams.mapper.ManagerMapper;
import com.wxy.sams.model.Manager;
import com.wxy.sams.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService , UserDetailsService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public void insert(Manager manager) {
        managerMapper.insert(manager);
    }

    @Override
    public void update(Manager manager) {
        managerMapper.update(manager);
    }

    @Override
    public void delete(int mid) {
        managerMapper.delete(mid);
    }

    @Override
    public Manager findByPhone(String mphone) {
        return managerMapper.findByPhone(mphone);
    }

    @Override
    public Manager findByEmail(String memail) {
        return managerMapper.findByEmail(memail);
    }

    @Override
    public Manager findById(String mid) {
        return managerMapper.findById(mid);
    }

    @Override
    public boolean isExistsByPhone(String mphone) {
        return managerMapper.isExistsByPhone(mphone) == 0 ? false : true;
    }

    @Override
    public boolean isExistsByEmail(String memail) {
        return managerMapper.isExistsByEmail(memail) == 0 ? false : true;
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
