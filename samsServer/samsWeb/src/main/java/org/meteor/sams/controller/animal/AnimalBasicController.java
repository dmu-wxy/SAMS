package org.meteor.sams.controller.animal;

import org.meteor.sams.model.Animal;
import org.meteor.sams.model.RespBean;
import org.meteor.sams.model.RespPageBean;
import org.meteor.sams.service.AnimalService;
import org.meteor.sams.util.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/animal/info")
public class AnimalBasicController {

    @Autowired
    private AnimalService animalService;
    /**
     * 分页查询，默认查询第一页10条记录
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public RespPageBean getAnimalByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,Animal animal,Date[] birthDate){
        System.out.println(Arrays.toString(birthDate));
        return animalService.getAnimalByPage(page,size,animal,birthDate);
    }

    @PostMapping("/")
    public RespBean addAnimal(@RequestBody Animal animal){
        if(animalService.insert(animal) == 1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteAnimalById(@PathVariable Integer id){
        if(animalService.delete(id) == 1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败！");
    }

    @PutMapping("/")
    public RespBean updateAnimal(@RequestBody Animal animal){
        if(animalService.update(animal) == 1){
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败！");
    }

    /**
     * 导出流浪动物数据
     * @return
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData(){
        List<Animal> animalList = (List<Animal>)animalService.getAnimalByPage(null,null,null,null).getData();
        return POIUtils.animal2Excel(animalList);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Animal> animals = POIUtils.excel2Animal(file);
        if(animalService.insertAnimals(animals) == animals.size()){
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }
}
