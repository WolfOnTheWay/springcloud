package com.sdt.fsc.entity.alarm;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 15:42
 */
@Data
public class DeviceDTO implements Serializable {
    private String deviceName;
    private String deviceCode;
    private Integer onlineStatus;
    private Integer status;
    private Integer currentPage;
    private Integer rowsOfPage;

}
