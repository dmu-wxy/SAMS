package com.wxy.sams.service.impl;

import com.wxy.sams.mapper.AnimalMapper;
import com.wxy.sams.model.Animal;
import com.wxy.sams.model.RespPageBean;
import com.wxy.sams.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalMapper animalMapper;

    @Override
    public int insert(Animal animal) {
        return animalMapper.insert(animal);
    }

    @Override
    public Integer update(Animal animal) {
        return animalMapper.update(animal);
    }

    @Override
    public Integer delete(Integer aid) {
        return animalMapper.delete(aid);
    }

//    @Override
//    public RespPageBean getAnimalByPage(Integer page, Integer size, Animal animal) {
//        if(page != null && size != null){
//            page = (page - 1) * size;
//        }
//        List<Animal> animals = animalMapper.getAnimalByPage(page,size,animal.getAname());
//        Long total = animalMapper.getTotal(animal.getAname());
//        RespPageBean respPageBean = new RespPageBean();
//        respPageBean.setData(animals);
//        respPageBean.setTotal(total);
//        return respPageBean;
//    }

    @Override
    public int insertAnimals(List<Animal> animals) {
        return animalMapper.insertAnimals(animals);
    }

    @Override
    public RespPageBean getAnimalByPage(Integer page, Integer size, Animal animal, Date[] birthDate) {
        if(page != null && size != null){
            page = (page - 1) * size;
        }
        List<Animal> animals = animalMapper.getAnimalByPage(page,size,animal,birthDate);
        Long total = animalMapper.getTotal(animal,birthDate);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(animals);
        respPageBean.setTotal(total);
        return respPageBean;
    }

}
