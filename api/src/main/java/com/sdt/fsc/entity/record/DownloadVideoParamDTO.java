package com.sdt.fsc.entity.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-7-8 20:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadVideoParamDTO implements Serializable {
    private String cameraId;
    private String startTime;
    private String stopTime;
    private String fileName;
}
