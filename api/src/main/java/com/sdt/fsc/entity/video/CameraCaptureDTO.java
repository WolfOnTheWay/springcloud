package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 手动抓图消息体
 * @Datetime 2020/8/10 14:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraCaptureDTO {
    /**
     * 图片url信息
     */
    private String picUrl;
}
