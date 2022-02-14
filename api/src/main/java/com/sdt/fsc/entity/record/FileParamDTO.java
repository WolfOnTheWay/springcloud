package com.sdt.fsc.entity.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-7-5 18:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileParamDTO {
    private String cameraId;
    private String startTime;
    private String stopTime;
}
