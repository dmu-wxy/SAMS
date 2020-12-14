package com.wxy.sams;

import com.wxy.sams.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SamsApplicationTests {

    @Autowired
    public ManagerService managerService;

    @Test
    void contextLoads() {
        System.out.println(managerService.findByEmail("2290502632@qq.com"));
    }

}
