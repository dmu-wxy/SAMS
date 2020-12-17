package com.wxy.sams.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Manager implements Serializable {

    private int mid;

    private String mname;

    private String mphone;

    private String password;

    private String memail;

    private String duty;

    private int gender;

    private Date birth;

}
