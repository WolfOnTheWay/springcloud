package com.sdt.fsc.common;

/**
 * @description: 系统错误类型
 * @author: hedonglei
 * @date: 2021-6-15 9:12
 */
public enum CustomErrorType {
    //成功
    SUCCESS(200, "成功"),
    //资源参数问题
    PARAMS_NULL(10001, "参数为空"),
    CODE_NULL(10001, "code参数为空"),
    PARAM_NOT_COMPLETE(10002, "参数不全"),
    PARAM_TYPE_ERROR(10003, "参数类型错误"),
    PARAM_INVALID(10004, "参数不合法"),
    //业务错误
    BUSSINE_ERROR(30001, "系统业务出现问题"),
    SUBSYSTEM_DCM_UNCONNECT(30002, "视频接入配置子系统未启动"),
    SUBSYSTEM_QZ_UNCONNECT(30003, "视频分析服务子系统未启动"),
    SUBSYSTEM_HK_UNCONNECT(30004, "海康子系统未启动"),
    VIDEO_CHANNEL_ERROR(30010, "视频通道号错误"),
    DVR_LOGIN_ERROR(30011, "视频存储设备登录失败"),
    DVR_WorkState_ERROR(30012, "获取设备工作状态失败"),
    DVR_StartDVRRecord_ERROR(30013, "远程手动启动设备录像失败"),
    DVR_StopDVRRecord_ERROR(30014, "远程手动停止设备录像失败"),
    DVR_InquiryRecordTimeSpan_ERROR(30015, "获取通道录像起止时间失败"),
    DVR_GET_IPPARACFG_ERROR(30016, "获取IP接入配置参数失败"),
    DVR_PlayBackByName_ERROR(30017, "按文件名回放录像文件失败"),
    DVR_PLAYSTART_ERROR(30018, "控制录像开始回放失败"),
    DVR_RecordStatus_ERROR(30019, "获取通道录像状态失败"),

    //系统错误,
    ERROR(40001, "系统错误"),
    //数据错误
    DATA_NOT_FOUND(50001, "数据未找到"),
    DATA_ERROR(50002, "数据错误"),
    DATA_ALREADY_EXITED(50003, "数据已存在"),
    DATA_IS_NULL(50003, "数据为空"),
    PARAM_IS_NULL(50004, "参数不能为空或者参数不合法"),
    //接口错误
    INTERFACE_INNER_INVOKE_ERROR(60001, "系统内部接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "系统外部接口调用异常"),
    INTERFACE_FORBIDDEN(60003, "接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),
    //权限错误
    PERMISSION_NO_ACCESS(70001, "没有访问权限"),
    //数据库操作错误
    CRUD_INSERT_ERROR(80001,"插入失败"),
    CRUD_DELETE_ERROR(80002,"删除失败"),
    CRUD_UPDATE_ERROR(80003,"更新失败"),
    CRUD_QUERY_ERROR(80004,"查询失败");

    private int code;

    private String message;

    CustomErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

