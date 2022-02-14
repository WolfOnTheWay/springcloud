package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 分页获取监控点资源返回消息体
 * @Datetime 2020/8/10 10:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraNewDTO {
    /**
     * 海拔
     */
    private String altitude;

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * 监控点唯一标识
     */
    private String cameraIndexCode;
    /**
     * 监控点名称
     */
    private String cameraName;
    /**
     * 监控点类型
     */
    private Integer cameraType;
    /**
     * 监控点类型说明
     */
    private String cameraTypeName;
    /**
     * 能力集
     */
    private String capabilitySet;
    /**
     * 能力集说明
     */
    private String capabilitySetName;
    /**
     * 智能分析能力集
     */
    private String intelligentSet;
    /**
     * 智能分析能力集说明
     */
    private String intelligentSetName;
    /**
     * 通道编号
     */
    private String channelNo;
    /**
     * 通道类型
     */
    private String channelType;
    /**
     * 通道子类型说明
     */
    private String channelTypeName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 所属编码设备唯一标识
     */
    private String encodeDevIndexCode;
    /**
     * 所属设备类型
     */
    private String encodeDevResourceType;
    /**
     * 所属设备类型说明
     */
    private String encodeDevResourceTypeName;
    /**
     * 监控点国标编号
     */
    private String gbIndexCode;
    /**
     * 安装位置
     */
    private String installLocation;
    /**
     * 键盘控制码
     */
    private String keyBoardCode;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 摄像机像素（1-普通像素，2-130万高清，3-200万高清，4-300万高清）
     */
    private String pixel;
    /**
     * 云镜类型（1-全方位云台（带转动和变焦），2-只有变焦,不带转动，3-只有转动，不带变焦，4-无云台，无变焦）
     */
    private String ptz;
    /**
     * 云镜类型说明
     */
    private String ptzName;
    /**
     * 云台控制(1-DVR，2-模拟矩阵，3-MU4000，4-NC600)
     */
    private String ptzController;
    /**
     * 云台控制说明
     */
    private String ptzControllerName;
    /**
     * 录像存储位置
     */
    private String recordLocation;
    /**
     * 录像存储位置说明
     */
    private String recordLocationName;
    /**
     * 所属区域唯一标识
     */
    private String regionIndexCode;
    /**
     * 是否被删除
     */
    private String status;
    /**
     * 状态说明
     */
    private String statusName;
    /**
     * 传输协议（0-UDP，1-TCP）
     */
    private Integer transType;
    /**
     * 传输协议类型说明
     */
    private String transTypeName;
    /**
     * 接入协议
     */
    private String treatyType;
    /**
     * 接入协议类型说明
     */
    private String treatyTypeName;
    /**
     * 可视域相关（JSON格式）
     */
    private String viewshed;
    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * featureCode
     */
    private String featureCode;
    /**
     * departmentID
     */
    private Integer departmentID;
}
