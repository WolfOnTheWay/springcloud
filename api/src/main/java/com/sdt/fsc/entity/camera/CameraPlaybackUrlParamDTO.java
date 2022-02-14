package com.sdt.fsc.entity.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-7-3 16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraPlaybackUrlParamDTO {
    private String cameraId;
    private Integer recordType;  //录像类型：0-手动录像，1-定时录像
}
