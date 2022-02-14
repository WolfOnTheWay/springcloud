package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 查询手动录像编号输入参数
 * @Datetime 2020/8/10 14:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraRecordSearchParamDTO {
    /**
     * 监控点编号集合，一次性最多传入1000个监控点编号进行查询
     * 必填
     */
    private String[] cameraIndexCodes;
}
