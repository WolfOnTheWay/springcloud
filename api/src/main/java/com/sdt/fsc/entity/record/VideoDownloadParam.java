package com.sdt.fsc.entity.record;

import com.sdt.fsc.entity.camera.DeviceLoginInfo;

import java.io.Serializable;

public class VideoDownloadParam implements Serializable {

    private DeviceLoginInfo deviceLoginInfo;
    private String fileName;
    private String lChannel;
    private VideoPo videoPo;
    private String filePath; //文件存放的路径

    public String getFilePath() {
        return filePath;
    }

    public DeviceLoginInfo getDeviceLoginInfo() {
        return deviceLoginInfo;
    }

    public void setDeviceLoginInfo(DeviceLoginInfo deviceLoginInfo) {
        this.deviceLoginInfo = deviceLoginInfo;
    }

    public String getlChannel() {
        return lChannel;
    }

    public void setlChannel(String lChannel) {
        this.lChannel = lChannel;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public VideoPo getVideoPo() {
        return videoPo;
    }

    public void setVideoPo(VideoPo videoPo) {
        this.videoPo = videoPo;
    }
}
