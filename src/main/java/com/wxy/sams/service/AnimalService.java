package com.wxy.sams.service;


import com.wxy.sams.model.Animal;
import com.wxy.sams.model.RespPageBean;


public interface AnimalService {

    public int insert(Animal animal);

    public Integer update(Animal animal);

    public Integer delete(Integer aid);

    RespPageBean getAnimalByPage(Integer page, Integer size, String keywords);
}
