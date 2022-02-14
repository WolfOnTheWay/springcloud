package com.sdt.fsc.entity.record;


import java.io.Serializable;
import java.util.List;

public class DeviceStateInfo implements Serializable {

    private List<DeviceStateDTO> deviceStateDTOs;

    private List<ManualRecordInfo> videoInfoList;

    public List<DeviceStateDTO> getDeviceStateDTOs() {
        return deviceStateDTOs;
    }

    public void setDeviceStateDTOs(List<DeviceStateDTO> deviceStateDTOs) {
        this.deviceStateDTOs = deviceStateDTOs;
    }

    public List<ManualRecordInfo> getVideoInfoList() {
        return videoInfoList;
    }

    public void setVideoInfoList(List<ManualRecordInfo> videoInfoList) {
        this.videoInfoList = videoInfoList;
    }
}
