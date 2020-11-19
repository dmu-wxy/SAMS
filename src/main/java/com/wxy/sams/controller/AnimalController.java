package com.wxy.sams.controller;

import com.wxy.sams.model.Animal;
import com.wxy.sams.service.impl.AnimalServiceImpl;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalController {

    @Autowired
    private AnimalServiceImpl animalService;

    @RequestMapping("findAll")
    public List<Animal> findAll(){
        List<Animal> animalList = animalService.findAll();
        return animalList;
    }
}
