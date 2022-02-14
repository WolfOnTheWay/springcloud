package com.sdt.fsc.entity.record;

import java.io.Serializable;

public class DeviceStateDTO implements Serializable {

    private String deviceState;
    private String byRecordStatic; //通道是否在录像

    public String getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    public String getByRecordStatic() {
        return byRecordStatic;
    }

    public void setByRecordStatic(String byRecordStatic) {
        this.byRecordStatic = byRecordStatic;
    }
}
