package com.wxy.sams.service;

import com.wxy.sams.model.Manager;

import java.util.List;

public interface ManagerService {

    public void update(Manager manager);

    public Integer delete(int mid);

    public Manager findById(String mid);


    public List<Manager> getAllManagers(String keywords);

    int updateManager(Manager manager);

    boolean updateManagerRole(Integer mid, Integer[] rids);
}
