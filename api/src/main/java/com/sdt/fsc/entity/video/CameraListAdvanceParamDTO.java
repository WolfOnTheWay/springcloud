package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author sdt-zcd
 * @version 1.0
 * @description
 * @date 2020/10/20 10:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraListAdvanceParamDTO {
    private Integer pageNo;
    private Integer pageSize;
    private String cameraIndexCodes;
    private String cameraName;
    private String encodeDevIndexCode;
    private String regionIndexCode;
    private Integer isCascade;
}
