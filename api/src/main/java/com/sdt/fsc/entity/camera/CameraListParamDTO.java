package com.sdt.fsc.entity.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

/**
 * @description: getCameraList获取摄像机列表接口请求参数
 * @author:
 * @date: 2021-6-9 14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraListParamDTO {
    @NotNull(message = "分组ID不能为空")
    private String id;
    @NotNull(message = "当前页不能为空")
    private Integer pageNo;
    @NotNull(message = "每页大小不能为空")
    private Integer pageSize;
    private String order;
}
