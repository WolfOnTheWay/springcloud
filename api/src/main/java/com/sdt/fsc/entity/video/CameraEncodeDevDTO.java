package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 视频编码设备
 * @Datetime 2020/9/23 10:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraEncodeDevDTO {
    /**
     * 所属服务编号
     */
    private String belongIndexCode;
    /**
     * 能力集，详见[附录A.44 设备能力集]@[软件产品-综合安防管理平台-附录-附录A 数据字典#附录A.44 设备能力集]
     */
    private String capability;
    /**
     * 设备驱动
     */
    private String deviceKey;
    /**
     * 设备驱动版本号
     */
    private String deviceKeyVersion;
    /**
     * 设备系列
     */
    private String deviceType;
    /**
     * 设备序列号
     */
    private String devSerialNum;
    /**
     * 资源唯一编码
     */
    private String indexCode;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 厂商
     */
    private String manufacturer;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 网域
     */
    private String netZoneId;
    /**
     * 端口
     */
    private String port;
    /**
     * 所属区域编码
     */
    private String regionIndexCode;
    /**
     * 资源类型，encodeDevice：编码设备
     */
    private String resourceType;
    /**
     * 接入协议，详见[附录A.6 编码设备接入协议]@[软件产品-综合安防管理平台-附录-附录A 数据字典#附录A.6 编码设备接入协议]
     */
    private String treatyType;
    /**
     * 创建时间，ISO8601标准
     */
    private String createTime;
    /**
     * 更新时间，ISO8601标准
     */
    private String updatetime;
    /**
     * 编码设备登录用户名
     */
    private String userName;
    /**
     * 编码设备登录密码
     */
    private String password;
    /**
     * 萤石应用开发者key
     */
    private String appKey;
    /**
     * 萤石应用秘钥
     */
    private String secret;
}
