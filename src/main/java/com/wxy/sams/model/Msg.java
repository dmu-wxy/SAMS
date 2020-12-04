package com.wxy.sams.model;

import lombok.Data;

@Data
public class Msg {
    private int code;
    private String msg;
    private String data;

    public Msg() {
    }

    public Msg(int code, String msg,String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
