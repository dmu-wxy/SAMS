package org.meteor.sams.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Money {
    private int id;

    private double amount;

    private String IO;

    private String type;

    private String status;

    private Date date;

    private String Info;

    private String mName;
}
