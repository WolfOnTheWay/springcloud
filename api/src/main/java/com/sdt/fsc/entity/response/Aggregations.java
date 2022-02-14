package com.sdt.fsc.entity.response;

import java.io.Serializable;

public class Aggregations implements Serializable {
    private static final long serialVersionUID = 5557692248406738695L;
    private GroupDate groupDate;
    public void setGroupDate(GroupDate groupDate) {
        this.groupDate = groupDate;
    }
    public GroupDate getGroupDate() {
        return groupDate;
    }
}
