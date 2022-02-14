package com.sdt.fsc.entity.camera;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class DevDevice extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String pinYin;

    private String pinYinAd;

    private String access;

    private String groupId;

    /**
     * 使用类型：camera(摄像机),monitor(监视器),codec(编解码通道)
     */
    private String useType;

    /**
     * 所属dcau
     */
    private String dcauServiceId;

    /**
     * 服务id
     */
    private String serviceId;

    private String deviceType;

    /**
     * 通道数
     */
    private Integer channelNum;

    /**
     * 0-禁用 1-启用
     */
    private Integer status;

    private Long sort;

    /**
     * 0-无 1-有
     */
    private Integer voiceTalk;

    private String host;

    private Integer port;

    /**
     * 区分dcs级别:0为下级,1为上级
     */
    private String level;

    /**
     * 设备属性
     */
    private String deviceProperty;

    /**
     * 开启心跳检测: 0-不校时，1-校时
     */
    private Integer synchroClock;

    /**
     * 开启心跳检测: 0-不开启，1-开启
     */
    private Integer heartDetection;

    /**
     * 心跳超时次数（默认3次），支持1-100次输入。
     */
    private Integer heartTimeoutTimes;

    /**
     * 心跳超时时间（默认60秒），支持1-3600秒输入
     */
    private Integer heartTimeoutTime;

    /**
     * 关闭密码鉴权:0-不关闭，1-关闭
     */
    private Integer closePasswordAuthentication;

    /**
     * 是否查询目录,0否,1是
     */
    private Integer queryDirectory;

    /**
     * 是否订阅目录,0否,1是
     */
    private Integer subscribeDirectory;

    /**
     * 国标上级注册有效期
     */
    private Long expires;

    /**
     * 0:不允许 1：允许
     */
    private Boolean forwardFlag;

    private LocalDateTime modifyTime;

    private String relationId;

    /**
     * 抓拍是否可用（0-不开启  1-开启）
     */
    private Integer captureAvailable;

    /**
     * 抓拍协议
     */
    private String captureProtocol;

    /**
     * 抓拍用户名
     */
    private String captureUsername;

    /**
     * 抓拍密码
     */
    private String capturePassword;

    /**
     * 抓拍端口
     */
    private Integer capturePort;

    private String rtspUrl;


}
