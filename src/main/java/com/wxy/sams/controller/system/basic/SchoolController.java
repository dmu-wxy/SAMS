package com.wxy.sams.controller.system.basic;

import com.wxy.sams.model.School;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/basic/school")
public class SchoolController {
    public List<School> getSchools(Integer index,Integer No,Integer cityId){
        return null;
    }
}
