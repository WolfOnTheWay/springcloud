package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 停止手动录像输入参数
 * @Datetime 2020/8/10 14:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraStopRecordParamDTO {
    /**
     * 监控点唯一标识，[分页获取监控点资源]
     * @[软件产品-综合安防管理平台-API列表-视频应用服务-视频资源#分页获取监控点资源]接口获取返回参数cameraIndexCode
     * 必填
     */
    private String cameraIndexCode;
    /**
     * 手动录像编号，从[开始手动录像]
     * @[软件产品-综合安防管理平台-API列表-视频应用服务-视频能力#开始手动录像]接口获取返回参数taskID
     * 必填
     */
    private String taskID;
    /**
     * 下发的存储类型,0-中心存储 1-设备存储 如果不传入，默认为0-中心存储
     */
    private Integer type;
}
