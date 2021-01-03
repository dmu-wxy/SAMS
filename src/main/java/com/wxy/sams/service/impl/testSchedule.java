package com.wxy.sams.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;

//@Service
public class testSchedule {

    //前面任务结束时间和后面任务开始时间间隔2s
    @Scheduled(fixedDelay = 2000)
    public void fixedDelay(){
        System.out.println("延迟两秒>>" + new Date(System.currentTimeMillis()));
    }

    //两次定时任务开始的时间间隔为2s
    @Scheduled(fixedRate = 2000)
    public void fixedRate(){
        System.out.println("等待两秒>>" + new Date(System.currentTimeMillis()));
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void corn(){
        System.out.println("cron>>");
    }
}
