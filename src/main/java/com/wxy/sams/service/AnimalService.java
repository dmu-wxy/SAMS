package com.wxy.sams.service;


import com.wxy.sams.model.Animal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AnimalService {
    public List<Animal> findAll();

    public Animal findById(int aid);

    public void insert(Animal animal);

    public void update(Animal animal);

    public void delete(int aid);
}
