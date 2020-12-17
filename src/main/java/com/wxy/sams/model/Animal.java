package com.wxy.sams.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Animal implements Serializable {

    private int aid;

    private String aname;

    private String breed;

    private String p_addr;

    private int gender;

    private Date birth;

}
