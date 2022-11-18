package com.example.retrofit;

import java.util.List;

public class GetModel {
    private List<InfoModel> info=null; //should be the same name as array in the receiving file
    private String status;
    private String msg;

    public List<InfoModel> getInfo() {
        return info;
    }

    public void setInfo(List<InfoModel> info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
