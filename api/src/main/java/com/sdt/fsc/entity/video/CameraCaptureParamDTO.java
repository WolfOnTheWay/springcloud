package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 手动抓图输入参数
 * @Datetime 2020/8/10 14:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraCaptureParamDTO {
    /**
     * 监控点唯一标识，[分页获取监控点资源]
     * @[软件产品-综合安防管理平台-API列表-视频应用服务-视频资源#分页获取监控点资源]接口获取返回参数cameraIndexCode
     * 必填
     */
    private String cameraIndexCode;
}
