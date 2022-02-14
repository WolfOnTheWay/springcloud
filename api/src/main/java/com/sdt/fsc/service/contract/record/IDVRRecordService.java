package com.sdt.fsc.service.contract.record;

import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.entity.camera.CameraIdDTO;
import com.sdt.fsc.entity.record.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDVRRecordService {
    /**
     * 开始手动录像
     * @param recordControlVO
     * @return
     */
    BaseResponse<Boolean> startRecord(RecordControlVO recordControlVO);

    /**
     * 停止手动录像
     * @param recordControlVO
     * @return
     */
    BaseResponse<Boolean> stopRecord(RecordControlVO recordControlVO);

    /**
     * 获取当前通道的手动录像状态
     * @param cameraIdDTO
     * @return
     */
    BaseResponse<Integer> getRecordStatus(CameraIdDTO cameraIdDTO);

    /**
     * 从设备上查找手动录像文件
     * @param fileParamDTO
     * @return
     */
    List<ManualRecordInfo> getRecordFileNameInfo(FileParamDTO fileParamDTO);

    /**
     *
     * @description:  查找手动录像文件信息
     * @param manualRecordInfoParamDTO
     * @return: java.util.List<com.sdt.fsc.entity.record.ManualRecordInfo>
     * @author: hegonglei
     * @date: 2021-7-6 12:31
     */
    List<ManualRecordInfo> findManualRecordInfo(ManualRecordInfoParamDTO manualRecordInfoParamDTO);

    /**
     * 保存录像标签
     * @param recordLabelDTO
     * @return
     */
    BaseResponse<Boolean> updateRecordLabel(RecordLabelDTO recordLabelDTO);

    /**
     * 获取通道录像起止时间
     * @param recordTimeSpanParamDTO
     * @return
     */
    JSONObject getRecordTimeSpan(RecordTimeSpanParamDTO recordTimeSpanParamDTO);

    /**
     * 停止手动录像并保存录像标签
     * @param stopRecordParamDTO
     * @return
     */
    BaseResponse<Boolean> StopRecordWithRecordLabel(StopRecordParamDTO stopRecordParamDTO);

    BaseResponse<Object> findEquipmentInfo(FileParamDTO fileParamDTO);

}
