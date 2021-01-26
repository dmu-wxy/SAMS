package com.wxy.sams.controller;

import com.wxy.sams.model.Animal;
import com.wxy.sams.model.RespBean;
import com.wxy.sams.service.impl.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("animal")
public class AnimalController {

    @Autowired
    private AnimalServiceImpl animalService;

    @RequestMapping("findAll")
    public List<Animal> findAll(){
        List<Animal> animalList = animalService.findAll();
        return animalList;
    }

    @RequestMapping("findById")
    public Animal findById(int aid){
        return animalService.findById(aid);
    }

    @RequestMapping("update")
    public RespBean updateAnimal(Animal animal){
        animalService.update(animal);
        return RespBean.ok("OK");
    }

    @RequestMapping("delete")
    public RespBean deleteAnimal(int aid){
        animalService.delete(aid);
        return RespBean.ok("OK");
    }

}
