package com.wxy.sams.model;

import lombok.Data;

import java.util.Date;

@Data
public class Manager {

    private int mid;

    private String mname;

    private String mphone;

    private String password;

    private String memail;

    private String duty;

    private int gender;

    private Date birth;
}
