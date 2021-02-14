package com.wxy.sams.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "流浪动物实体类",description = "描述")
public class Animal implements Serializable {

    @ApiModelProperty(value = "id")
    private int aid;

    @ApiModelProperty(value = "流浪动物名称")
    private String aname;

    @ApiModelProperty(value = "流浪动物品种")
    private String breed;

    @ApiModelProperty(value = "流浪动物位置")
    private String p_addr;

    @ApiModelProperty(value = "流浪动物性别")
    private String gender;

    @ApiModelProperty(value = "流浪动物生日")
    private Date birth;

    @Override
    public String toString() {
        return "Animal{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", breed='" + breed + '\'' +
                ", p_addr='" + p_addr + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getP_addr() {
        return p_addr;
    }

    public void setP_addr(String p_addr) {
        this.p_addr = p_addr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
