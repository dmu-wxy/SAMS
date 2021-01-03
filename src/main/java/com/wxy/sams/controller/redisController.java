package com.wxy.sams.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "缓存相关接口")
public class redisController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @PostMapping("/set")
    @ApiOperation(value = "添加缓存",notes = "详细信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "设置成功"),
            @ApiResponse(code = 500, message = "设置失败")
    })
    public void set(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("name","meteor");
    }

    //@ApiImplicitParam(name="参数名字",value = "参数描述",required = true,defaultValue = "默认值")  //返回json中参数描述设置
    @ApiIgnore //忽略
    @GetMapping("/get")
    public void get(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        System.out.println(ops.get("name"));
    }
}
