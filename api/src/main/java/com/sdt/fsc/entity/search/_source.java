package com.sdt.fsc.entity.search;

import lombok.experimental.Accessors;

import java.io.Serializable;
@Accessors(chain = true)
public class _source implements Serializable {
    private static final long serialVersionUID = -8938300098564507318L;
    private String timestamps;

    public String getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(String timestamps) {
        this.timestamps = timestamps;
    }
}
