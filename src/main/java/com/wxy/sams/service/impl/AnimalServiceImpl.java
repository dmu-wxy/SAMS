package com.wxy.sams.service.impl;

import com.wxy.sams.mapper.AnimalMapper;
import com.wxy.sams.model.Animal;
import com.wxy.sams.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalMapper animalMapper;

    @Override
    public List<Animal> findAll() {
        return animalMapper.findAll();
    }

    @Override
    public Animal findById(int aid) {
        return animalMapper.findById(aid);
    }

    @Override
    public void insert(Animal animal) {
        animalMapper.insert(animal);
    }

    @Override
    public void update(Animal animal) {
        animalMapper.update(animal);
    }

    @Override
    public void delete(int aid) {
        animalMapper.delete(aid);
    }
}
