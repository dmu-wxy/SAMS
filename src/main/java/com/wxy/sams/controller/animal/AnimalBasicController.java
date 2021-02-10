package com.wxy.sams.controller.animal;

import com.wxy.sams.model.Animal;
import com.wxy.sams.model.RespBean;
import com.wxy.sams.model.RespPageBean;
import com.wxy.sams.service.AnimalService;
import com.wxy.sams.util.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public RespPageBean getAnimalByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keywords){
        return animalService.getAnimalByPage(page,size,keywords);
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
        List<Animal> animalList = (List<Animal>)animalService.getAnimalByPage(null,null,null).getData();
        return POIUtils.animal2Excel(animalList);
    }
}
