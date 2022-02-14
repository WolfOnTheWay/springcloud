package com.sdt.fsc.entity.record;

import com.sdt.fsc.entity.camera.DeviceLoginInfo;

import java.io.Serializable;


public class FileVo implements Serializable {
    private DeviceLoginInfo deviceLoginInfo;
    private VideoPo videoPo;

    public DeviceLoginInfo getDeviceLoginInfo() {
        return deviceLoginInfo;
    }

    public void setDeviceLoginInfo(DeviceLoginInfo deviceLoginInfo) {
        this.deviceLoginInfo = deviceLoginInfo;
    }

    public VideoPo getVideoPo() {
        return videoPo;
    }

    public void setVideoPo(VideoPo videoPo) {
        this.videoPo = videoPo;
    }
}
