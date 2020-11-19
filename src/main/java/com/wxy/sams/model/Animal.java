package com.wxy.sams.model;

import lombok.Data;

import java.util.Date;

@Data
public class Animal {

    private int aid;

    private String aname;

    private String breed;

    private String p_addr;

    private int gender;

    private Date birth;

}
