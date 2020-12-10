package com.wxy.sams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class redisController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping("/set")
    public void set(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("name","meteor");
    }

    @RequestMapping("/get")
    public void get(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        System.out.println(ops.get("name"));
    }
}
