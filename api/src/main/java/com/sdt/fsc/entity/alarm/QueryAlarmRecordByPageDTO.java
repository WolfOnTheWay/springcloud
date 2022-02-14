package com.sdt.fsc.entity.alarm;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 9:43
 */
@Data
public class QueryAlarmRecordByPageDTO implements Serializable {
    private String controlTypeCode;
    private Set<String> controlTaskIds;
    private String controlTaskName;
    private Set<String> devices;
    private Long startTime;
    private Long endTime;
    private Integer pageNo;
    private Integer pageSize;
}
