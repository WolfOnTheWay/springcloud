package com.sdt.fsc.entity.response;

import java.io.Serializable;

/**
 * 知识库相关
 */
public class Content implements Serializable {

    private static final long serialVersionUID = -7720219142470420303L;
    /**
     * 时间戳
     */
    private  long ts;
    /**
     * 阈值
     */
    private  float value;

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
