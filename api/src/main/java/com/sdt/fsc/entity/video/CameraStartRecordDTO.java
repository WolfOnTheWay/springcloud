package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 开始手动录像返回消息体
 * @Datetime 2020/8/10 14:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraStartRecordDTO {
    private String taskID;
}
