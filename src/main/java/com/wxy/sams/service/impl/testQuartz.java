package com.wxy.sams.service.impl;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class testQuartz {

    public void sayHello(){
        System.out.println("meteor is saying hello" + new Date());
    }
}
