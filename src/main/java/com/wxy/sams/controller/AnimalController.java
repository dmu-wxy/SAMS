package com.wxy.sams.controller;

import com.wxy.sams.model.Animal;
import com.wxy.sams.model.Msg;
import com.wxy.sams.service.impl.AnimalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 前后端不分离
 */
@Controller
public class AnimalController {

    @Autowired
    private AnimalServiceImpl animalService;

    @RequestMapping("main")
    public String findAll(Model model){
        List<Animal> animalList = animalService.findAll();
        model.addAttribute("animals",animalList);
        return "main";
    }

    @RequestMapping("findById")
    public String findById(int aid,Model model){
        Animal animal = animalService.findById(aid);
        model.addAttribute("animal",animal);
        return "update";
    }

    @RequestMapping("update")
    public String updateAnimal(Animal animal){
        animalService.update(animal);
        return "redirect:main";
    }

    @GetMapping("delete")
    public String deleteAnimal(int aid){
        animalService.delete(aid);
        return "redirect:main";
    }

    @RequestMapping("insert")
    public String insertAnimal(Animal animal){
        animalService.insert(animal);
        return "redirect:main";
    }
}

/**
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
    public AnimalMsg updateAnimal(Animal animal){
        AnimalMsg animalMsg = new AnimalMsg();
        animalMsg.setCode(200);
        animalMsg.setMsg("success");
        return animalMsg;
    }

    @RequestMapping("delete")
    public AnimalMsg deleteAnimal(int aid){
        AnimalMsg animalMsg = new AnimalMsg();
        animalMsg.setCode(200);
        animalMsg.setMsg("success");
        return animalMsg;
    }

}
*/