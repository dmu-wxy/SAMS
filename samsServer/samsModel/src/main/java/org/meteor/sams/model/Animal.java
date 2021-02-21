package org.meteor.sams.model;

import java.io.Serializable;
import java.util.Date;

public class Animal implements Serializable {

    private int aid;

    private String aname;

    private String breed;

    private String p_addr;

    private String gender;

    private Date birth;

    private String face;

    private String intro;

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

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", breed='" + breed + '\'' +
                ", p_addr='" + p_addr + '\'' +
                ", gender='" + gender + '\'' +
                ", birth=" + birth +
                ", face='" + face + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
