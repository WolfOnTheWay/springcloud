package com.sdt.fsc.entity.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-7-3 17:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraChannelInfoDTO {
    private String belongType;
    private String chanNo;
    private String deviceId;
    private String dpi;
    private String gbId;
    private String host;
    private String id;
    private Integer isVideoPlan;
    private String name;
    private String nodeId;
    private String ptz;
    private Integer streamNumber;
    private String transport;
}
