package com.sdt.fsc.entity.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraInfo implements Serializable {
    private String id;
    private String name;
    private String groupId;
    private String channelNo;
    private String voiceTalkChannelId;
    private Double longitude;
    private Double latitude;
    private Integer direction;
    private boolean online;
    private boolean PTZEnable;
    private Double angle;
    private Double radius;
    private String gbId;
    private String manufacturer;
    private String address;
    private String deviceId;
    private boolean isLocal;
}
