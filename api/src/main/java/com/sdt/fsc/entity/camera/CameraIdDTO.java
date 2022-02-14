package com.sdt.fsc.entity.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-7-3 16:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraIdDTO {
    private String cameraId;
}
