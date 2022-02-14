package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 获取监控点预览取流URL返回消息体
 * @Datetime 2020/8/10 13:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraPreviewUrlDTO {
    /**
     * 取流URL
     */
    private String url;
}
