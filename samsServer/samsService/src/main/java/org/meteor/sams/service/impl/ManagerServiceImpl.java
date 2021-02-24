package org.meteor.sams.service.impl;

import org.meteor.sams.mapper.ManagerMapper;
import org.meteor.sams.mapper.ManagerRoleMapper;
import org.meteor.sams.model.Manager;
import org.meteor.sams.service.ManagerService;
import org.meteor.sams.util.ManagerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService , UserDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private ManagerRoleMapper managerRoleMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Integer delete(int mid) {
        return managerMapper.delete(mid);
    }


    @Override
    public Manager findById(String mid) {
        return managerMapper.findById(mid);
    }

    @Override
    public Integer insertManager(Manager manager) {
        int result = 1;
        if(result == 1){
            //添加成功后发送邮件
            logger.info("服务端>>>>" + manager.toString());
            rabbitTemplate.convertAndSend("sams.mail.welcome",manager);
        }
        return result;
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
    public List<Manager> getAllManagersExceptCurrentManager() {
        return managerMapper.getAllManagersExceptCurrentManager(ManagerUtils.getCurrentManager().getMid());
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
