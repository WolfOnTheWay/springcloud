package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 根据监控点编号进行云台操作输入参数
 * @Datetime 2020/8/10 13:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraPTZParamDTO {
    /**
     * 监控点唯一标识，[分页获取监控点资源]
     * @[软件产品-综合安防管理平台-API列表-视频应用服务-视频资源#分页获取监控点资源]接口获取返回参数cameraIndexCode
     * 必填
     */
    private String cameraIndexCode;
    /**
     * 0-开始 1-停止
     * 必填
     */
    private Integer action;
    /**
     * 不区分大小写
     * 说明： LEFT 左转 RIGHT右转 UP 上转 DOWN 下转 ZOOM_IN 焦距变大
     * ZOOM_OUT 焦距变小 LEFT_UP 左上 LEFT_DOWN 左下 RIGHT_UP 右上 RIGHT_DOWN 右下
     * FOCUS_NEAR 焦点前移 FOCUS_FAR 焦点后移 IRIS_ENLARGE 光圈扩大 IRIS_REDUCE 光圈缩小
     * WIPER_SWITCH 接通雨刷开关 START_RECORD_TRACK
     * 开始记录轨迹 STOP_RECORD_TRACK 停止记录轨迹 START_TRACK 开始轨迹 STOP_TRACK 停止轨迹
     * 以下命令presetIndex不可 为空： GOTO_PRESET到预置点
     * 必填
     */
    private String command;
    /**
     * 云台速度，取值范围为1-100，默认50
     */
    private Integer speed;
    /**
     * 预置点编号，整数，通常在300以内
     */
    private Integer presetIndex;
}
