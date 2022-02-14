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
public class CameraPlaybackUrlVO {
    private String cameraId;
    private String cameraName;
    private String serve;
    private String url;
}
