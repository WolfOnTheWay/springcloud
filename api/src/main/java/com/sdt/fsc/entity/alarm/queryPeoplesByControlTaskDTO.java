package com.sdt.fsc.entity.alarm;

import lombok.Data;

import java.io.Serializable;

@Data
public class queryPeoplesByControlTaskDTO implements Serializable {
    private String controlTaskName;
    private Long startTime;
    private Long stopTime;
}
