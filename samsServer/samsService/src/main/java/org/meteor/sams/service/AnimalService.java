package org.meteor.sams.service;


import org.meteor.sams.model.Animal;
import org.meteor.sams.model.RespPageBean;

import java.util.Date;
import java.util.List;


public interface AnimalService {

    public int insert(Animal animal);

    public Integer update(Animal animal);

    public Integer delete(Integer aid);

//    RespPageBean getAnimalByPage(Integer page, Integer size, Animal animal);

    int insertAnimals(List<Animal> animals);

    RespPageBean getAnimalByPage(Integer page, Integer size, Animal animal, Date[] birthDate);
}
