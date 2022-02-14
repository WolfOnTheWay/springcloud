package com.sdt.fsc.service.contract.record;

import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.entity.camera.CameraIdDTO;
import com.sdt.fsc.entity.record.*;
import com.sdt.fsc.hik.HCNetSDK;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IRecordService {
    /**
     * 开始手动录像
     * @param recordControlVO
     * @return
     */
    BaseResponse<Boolean> startRecord(RecordControlVO recordControlVO) throws Exception;

    /**
     * 停止手动录像
     * @param recordControlVO
     * @return
     */
    BaseResponse<Boolean> stopRecord(RecordControlVO recordControlVO) throws Exception;

    /**
     * 从设备上查找手动录像文件
     * @param fileParamDTO
     * @return
     */
    List<?> getFileNameInfo(FileParamDTO fileParamDTO) throws Exception;

    /**
     * 查找手动录像文件信息
     * @param videoInfoParamDTO
     * @return
     */
    List<VideoInfo> findVideoInfo(VideoInfoParamDTO videoInfoParamDTO) throws Exception;

    /**
     * 视频打标签
     * @param videoTagDTO
     * @return
     */
    void saveVideoTagInfo(VideoTagDTO videoTagDTO);

    /**
     *
     * @description:  查找手动录像文件信息
     * @param manualRecordInfoParamDTO
     * @return: java.util.List<com.sdt.fsc.entity.record.ManualRecordInfo>
     * @author: hegonglei
     * @date: 2021-7-6 12:31
     */
    List<ManualRecordInfo> findManualRecordInfo(ManualRecordInfoParamDTO manualRecordInfoParamDTO) throws Exception;

    /**
     * 保存录像标签
     * @param recordLabelDTO
     * @return
     */
    BaseResponse<Boolean> updateRecordLabel(RecordLabelDTO recordLabelDTO);

    /**
     * 停止手动录像并保存录像标签
     * @param stopRecordParamDTO
     * @return
     */
    BaseResponse<Boolean> StopRecordWithRecordLabel(StopRecordParamDTO stopRecordParamDTO) throws Exception;

    /**
     * 获取通道录像起止时间
     * @param recordTimeSpanParamDTO
     * @return
     */
    JSONObject getRecordTimeSpan(RecordTimeSpanParamDTO recordTimeSpanParamDTO) throws Exception;

    /**
     * 获取当前通道的手动录像状态
     * @param cameraIdDTO
     * @return
     */
    BaseResponse<Integer> getRecordStatusV30(CameraIdDTO cameraIdDTO) throws Exception;

    BaseResponse<Object> findEquipmentInfo(FileParamDTO fileParamDTO) throws Exception;

    BaseResponse<Object> downloadVideoByFileName(DownloadVideoParamDTO downloadVideoParamDTO) throws Exception;

    Map<String, Object> getDownloadProgress(Integer lPlayHandle) throws Exception;

    Map<String, Object> getDeviceChannelInfo(String channel);

}
