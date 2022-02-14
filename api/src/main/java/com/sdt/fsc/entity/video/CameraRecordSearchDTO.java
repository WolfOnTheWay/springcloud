package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 查询手动录像编号返回消息体
 * @Datetime 2020/8/10 14:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraRecordSearchDTO {
    /**
     * 下发手动录像的用户ID
     */
    private String userId;
    /**
     * 手动录像编号集合，只查询未结束的手动录像编号信息
     */
    private ManualRecordTask[] manualRecordTask;

    private class ManualRecordTask{
        /**
         * 监控点唯一标识
         */
        String cameraIndexCode;
        /**
         * 手动录像编号
         */
        String taskID;
        /**
         * 下发的存储类型。0-中心存储，1-设备存储。如果不传入，默认为0-中心存储
         */
        Integer type;
    }
}
