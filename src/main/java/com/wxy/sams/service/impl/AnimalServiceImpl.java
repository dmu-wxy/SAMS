package com.wxy.sams.service.impl;

import com.wxy.sams.mapper.AnimalMapper;
import com.wxy.sams.model.Animal;
import com.wxy.sams.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "c1")
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalMapper animalMapper;

    @Override
    public List<Animal> findAll() {
        return animalMapper.findAll();
    }

    @Override
    @Cacheable(key = "#aid")
    public Animal findById(int aid) {
        return animalMapper.findById(aid);
    }

    @Override
    @CachePut(key = "#animal.aid")
    public void insert(Animal animal) {
        animalMapper.insert(animal);
    }

    @Override
    @CachePut(key = "#animal.aid")
    public void update(Animal animal) {
        animalMapper.update(animal);
    }

    @Override
    @CacheEvict(key = "#aid")
    public void delete(int aid) {
        animalMapper.delete(aid);
    }
}
