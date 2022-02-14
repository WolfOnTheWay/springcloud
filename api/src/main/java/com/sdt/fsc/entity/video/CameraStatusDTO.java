package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 获取监控点在线状态返回
 * @Datetime 2020/10/29 14:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraStatusDTO {
    /**
     * 设备型号
     */
    private String deviceType;
    /**
     * 设备唯一编码
     */
    private String deviceIndexCode;
    /**
     * 区域编码
     */
    private String regionIndexCode;
    /**
     * 采集时间
     */
    private String collectTime;
    /**
     * 区域名字
     */
    private String regionName;
    /**
     * 资源唯一编码
     */
    private String indexCode;
    /**
     * 设备名称
     */
    private String cn;
    /**
     * 协议类型， 海康私有协议： hiksdk_net， GB/T28181： gb_reg, eHome协议： ehome_reg， 大华私有协议： dhsdk_net， ONVIF协议： onvif_net， 萤石协议： ezviz_net， 级联： cascade
     */
    private String treatyType;
    /**
     * 厂商，hikvision-海康，dahua-大华
     */
    private String manufacturer;
    /**
     * ip地址，监控点无此值
     */
    private String ip;
    /**
     * 端口，监控点无此值
     */
    private Integer port;
    /**
     * 在线状态，0离线，1在线
     */
    private Integer online;

}
