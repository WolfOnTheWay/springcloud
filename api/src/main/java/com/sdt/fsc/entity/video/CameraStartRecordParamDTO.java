package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 开始手动录像输入参数
 * @Datetime 2020/8/10 14:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraStartRecordParamDTO {
    /**
     * 监控点唯一标识，[分页获取监控点资源]
     * @[软件产品-综合安防管理平台-API列表-视频应用服务-视频资源#分页获取监控点资源]接口获取返回参数cameraIndexCode
     * 必填
     */
    private String cameraIndexCode;
    /**
     * 录像类型： 范围:0-65535，参考[附录A.31 录像类型]
     * @[软件产品-综合安防管理平台-附录-附录A
     * 数据字典#附录A.31 录像类型]，中心存储必填
     */
    private Integer recordType;
    /**
     * 下发的存储类型。0-中心存储 1-设备存储。如果不传入，默认为0-中心存储
     */
    private Integer type;
}
