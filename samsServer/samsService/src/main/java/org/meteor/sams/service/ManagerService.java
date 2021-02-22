package org.meteor.sams.service;

import org.meteor.sams.model.Manager;

import java.util.List;

public interface ManagerService {


    public Integer delete(int mid);

    public Manager findById(String mid);

    public Integer insertManager(Manager manager);

    public List<Manager> getAllManagers(String keywords);

    int updateManager(Manager manager);

    boolean updateManagerRole(Integer mid, Integer[] rids);
}
