package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 获取手动录像状态
 * @Datetime 2020/8/10 14:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraRecordStatusDTO {
    /**
     * 手动录像状态,0：未接入 1：正常执行 2：异常
     */
    private String taskStatus;
}
