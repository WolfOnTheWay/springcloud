package com.sdt.fsc.entity.camera;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aubin
 * @since 2021-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DevDeviceCamera extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 摄像机编号 用于对外可见唯一性标识
可能作为键盘编号
     */
    private String cameraNo;

    private String name;

    private String pinYin;

    private String pinYinAd;

    private Integer level;

    private Long cid;

    private String nodeId;

    /**
     * 分组id
     */
    private String groupId;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 所属设备类型：0：纯摄像机设备，1：其他设备
     */
    private Integer deviceType;

    /**
     * 通道编号
     */
    private String chanNo;

    /**
     * 通道参数
     */
    private String chanParam;

    private String gbId;

    /**
     * 国标映射编号
     */
    private String gbIdMap;

    /**
     * 清晰度 标清(SD)/高清(HD)/超清(UHD)
     */
    private String dpi;

    /**
     * 云台标志位
     */
    private String ptz;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 合并id
可null,null表示未合并摄像机。
merge==id,表示主通道。
merge!=id,表示次通道,被合并项。
     */
    private String merge;

    private String ext;

    private String type;

    /**
     * 0-禁用 1-启用
     */
    private Integer status;

    private String relationId;

    private String fromGroupId;

    private String fromMerge;

    /**
     * 国标流传输方式：udp,tcp_active(主动tcp),tcp_passive(被动tcp)
     */
    private String transport;

    private Long sort;

    private String path;

    /**
     * 当为设备时，设备厂商（必选）
     */
    private String manufacturer;

    /**
     * 当为设备时，设备型号（必选）
     */
    private String model;

    /**
     * 当为设备时，设备归属（必选）
     */
    private String owner;

    /**
     * 警区（可选）
     */
    private String block;

    /**
     * 当为设备时，安装地址（必选）
     */
    private String address;

    /**
     * 当为设备时，是否有子设备（必选）1有，0没有
     */
    private Integer parental;

    /**
     * 信令安全模式（可选）缺省为0； 0：不采用；2：S/MIME签名方式；3：S/MIME加密签名同时采用方式；4：数字摘要方式
     */
    private Integer safetyway;

    /**
     * 保密属性（必选）缺省为0；0：不涉密，1：涉密
     */
    private Integer secrecy;

    /**
     * 摄像机位置类型扩展。1-省际检查站、2-党政机关、3-车站码头、4-中心广场、5-体育场馆、6-商业中心、7-宗教场所、8-校园周边、9-治安复杂区域、10-交通干线。摄像机时可选。
     */
    private Integer position;

    /**
     * 摄像机安装位置室外、室内属性。1-室外、2-室内。当目录项为摄像机时可选，缺省为1
     */
    private Integer room;

    /**
     * 摄像机用途属性。1-治安、2-交通、3-重点。当目录项为摄像机时可选
     */
    private Integer uses;

    /**
     * 摄像机补光属性。1-无补光、2-红外补光、3-白光补光。当目录项为摄像机时可选，缺省为1
     */
    private Integer supplylight;

    /**
     * 摄像机监视方位属性。1-东、2-西、3-南、4-北、5-东南、6-东北、7-西南、8-西北。当目录项为摄像机时且为固定摄像机或设置看守位摄像机时可选
     */
    private Integer direction;

    /**
     * 码流
     */
    private Integer streamNumber;

    /**
     * 1:数字摄像机 2:模拟摄像机
     */
    private Integer analogType;

    private LocalDateTime modifyTime;

    private String mac;

    /**
     * 记录版本号
     */
    private Long version;

    /**
     * 1:本地资源,0:同步资源
     */
    private Integer isLocal;

    /**
     * 通道号排序字段
     */
    private String chanSort;

    /**
     * 录像key
     */
    private String recordKey;

    /**
     * 录像取流方式：-1-无,0-服务器取流,1-前端取流,2-平台取流
     */
    private Integer videoFlowType;

    /**
     * 取流平台，并非摄像机所属平台
     */
    private String flowPlatformId;

    /**
     * 绑定语音通道id
     */
    private String voiceTalkChannelId;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 审核内容
     */
    private String auditRecord;

    /**
     * 摄像机照射角度
     */
    private Double angle;

    /**
     * 摄像机照射半径
     */
    private Double radius;

    /**
     * 摄像机在线状态：ON-在线，OFF-离线
     */
    @TableField("SXJZXZT")
    private String sxjzxzt;

    /**
     * 用于通道合并设置是否录像
     */
    private Integer isVideoPlan;

    private String rtspUrl;


}
