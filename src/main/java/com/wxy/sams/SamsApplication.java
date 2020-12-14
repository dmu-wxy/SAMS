package com.wxy.sams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.wxy.sams.mapper")
@EnableCaching
public class SamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamsApplication.class, args);
    }

}
