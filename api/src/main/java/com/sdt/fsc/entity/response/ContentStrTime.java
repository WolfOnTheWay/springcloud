package com.sdt.fsc.entity.response;

import java.io.Serializable;

public class ContentStrTime implements Serializable {
    private static final long serialVersionUID = 164303932954051311L;
    /**
     * 时间戳
     */
    private  String ts;
    /**
     * 阈值
     */
    private float value;
    private String judge;

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
