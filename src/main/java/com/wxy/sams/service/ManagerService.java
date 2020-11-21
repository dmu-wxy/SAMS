package com.wxy.sams.service;

import com.wxy.sams.model.Manager;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ManagerService {

    public void insert(Manager manager);

    public void update(Manager manager);

    public void delete(int mid);

    public Manager findByPhone(String mphone);

    public Manager findByEmail(String memail);

    public Manager findById(String mid);
}
