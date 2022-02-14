package com.sdt.fsc.entity.alarm;

import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 8:53
 */
public class ControlTaskStatusDTO implements Serializable {
    private String id;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
