package com.wxy.sams.service.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;


public class testQuartz2 extends QuartzJobBean {

    private String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("second job say hello:" + name + ":" + new Date());
    }
}
