package com.sdt.fsc.entity.alarm;

import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 10:51
 */
public class NameDTO implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
