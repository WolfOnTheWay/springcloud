package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 获取监控点回放取流URL输入参数
 * @Datetime 2020/8/10 13:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraPlaybackUrlParamDTO {
    /**
     * 监控点唯一标识，[分页获取监控点资源]
     * @[软件产品-综合安防管理平台-API列表-视频应用服务-视频资源#分页获取监控点资源]接口获取返回参数cameraIndexCode
     * 必填
     */
    private String cameraIndexCode;
    /**
     * 存储类型,0：中心存储 1：设备存储 默认为中心存储
     */
    private String recordLocation;
    /**
     * 取流协议（应用层协议)，
     * “rtsp”:RTSP协议,“rtmp”:RTMP协议,“hls”:HLS协议（HLS协议只支持海康SDK协议、EHOME协议、ONVIF协议接入的设备；只支持H264视频编码和AAC音频编码；
     * 云存储版本要求v2.2.4及以上的2.x版本，或v3.0.5及以上的3.x版本；ISC版本要求v1.2.0版本及以上，需在运管中心-视频联网共享中切换成启动平台内置VOD）
     * ,参数不填，默认为RTSP协议
     */
    private String protocol;
    /**
     * 传输协议（传输层协议）0:UDP 1:TCP 默认为tcp，在protocol设置为rtsp或者rtmp时有效 注：EHOME设备回放只支持TCP传输 GB28181 2011及以前版本只支持UDP传输
     */
    private Integer transmode;
    /**
     * 开始查询时间（IOS8601格式：yyyy-MM-dd'T'HH:mm:ss.SSSXXX） 例如北京时间： 2017-06-14T00:00:00.000+08:00
     * 必填
     */
    private String beginTime;
    /**
     * 结束查询时间，开始时间和结束时间相差不超过三天； （IOS8601格式：yyyy-MM-dd'T'HH:mm:ss.SSSXXX）例如北京时间： 2017-06-15T00:00:00.000+08:00
     * 必填
     */
    private String endTime;
    /**
     * 分页查询id，上一次查询返回的uuid，用于继续查询剩余片段，默认为空字符串
     */
    private String uuid;
    /**
     * 扩展内容，格式：key=value， 调用方根据其播放控件支持的解码格式选择相应的封装类型；
     * 支持的内容详见[附录F expand扩展内容说明]
     * @[软件产品-综合安防管理平台-附录-附录F expand扩展内容说明]
     */
    private String expand;
}
