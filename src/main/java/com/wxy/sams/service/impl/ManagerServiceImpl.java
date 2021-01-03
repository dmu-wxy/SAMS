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

    public boolean isExists(String account){
        if(account.indexOf("@") < 0)
            return isExistsByPhone(account);
        else
            return isExistsByEmail(account);
    }

    public Manager findManager(String account){
        if (account.indexOf("@") < 0) {
            //电话
            return findByPhone(account);
        } else {
            //邮箱
            return findByEmail(account);
        }
    }

    public Manager insertManager(String account,String password){
        Manager manager = new Manager();
        if(account.indexOf("@") < 0){
            manager.setMphone(account);
        }else{
            manager.setMemail(account);
        }
        manager.setPassword(password);
        insert(manager);
        return findManager(account);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
