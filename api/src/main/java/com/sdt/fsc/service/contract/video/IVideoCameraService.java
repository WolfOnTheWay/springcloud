package com.sdt.fsc.service.contract.video;

import com.sdt.fsc.entity.video.*;
import com.sdt.fsc.entity.common.PageTemplateDTO;

/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/8/10 11:41
 */
public interface IVideoCameraService {
    /**
//     * 分页获取区域列表
//     *
//     * @param regionListParamDTO
//     * @return
//     */
//    PageTemplateDTO<RegionListDTO> getRegionList(RegionListParamDTO regionListParamDTO) throws Exception;
//
//    /**
//     * 分页获取监控点资源
//     *
//     * @param cameraListParamDTO
//     * @return
//     */
//    PageTemplateDTO<CameraNewDTO> getCameraList(CameraListParamDTO cameraListParamDTO) throws Exception;
//
//    /**
//     * @param cameraListAdvanceParamDTO
//     * @return api.entity.common.PageTemplateDTO<api.entity.camera.proxy.CameraDTO>
//     * @desciption 分页获取监控点资源advance
//     * @author sdt-zcd
//     * @date 2020/10/20 10:16
//     */
//    PageTemplateDTO<CameraDTO> getCameraListAdvance(CameraListAdvanceParamDTO cameraListAdvanceParamDTO) throws Exception;
//
//    /**
//     * 获取监控点预览取流URL
//     *
//     * @param cameraPreviewUrlParamDTO
//     * @return
//     */
//    CameraPreviewUrlDTO getCameraPreviewUrl(CameraPreviewUrlParamDTO cameraPreviewUrlParamDTO) throws Exception;
//
//    /**
//     * 根据监控点编号进行云台操作
//     *
//     * @param cameraPTZParamDTO
//     * @return
//     * @throws Exception
//     */
//    String ptzControlling(CameraPTZParamDTO cameraPTZParamDTO) throws Exception;
//
//    /**
//     * 获取监控点回放取流URL
//     *
//     * @param cameraPlaybackUrlParamDTO
//     * @return
//     * @throws Exception
//     */
//    CameraPlaybackUrlDTO getCameraPlaybackUrl(CameraPlaybackUrlParamDTO cameraPlaybackUrlParamDTO) throws Exception;
//
//    /**
//     * 手动抓图
//     *
//     * @param cameraCaptureParamDTO
//     * @return
//     * @throws Exception
//     */
//    CameraCaptureDTO capture(CameraCaptureParamDTO cameraCaptureParamDTO) throws Exception;
//
//    /**
//     * 开始手动录像
//     *
//     * @param cameraStartRecordParamDTO
//     * @return
//     * @throws Exception
//     */
//    CameraStartRecordDTO startRecord(CameraStartRecordParamDTO cameraStartRecordParamDTO) throws Exception;
//
//    /**
//     * 停止手动录像
//     *
//     * @param cameraStopRecordParamDTO
//     * @return
//     * @throws Exception
//     */
//    String stopRecord(CameraStopRecordParamDTO cameraStopRecordParamDTO) throws Exception;
//
//    /**
//     * 获取手动录像状态
//     *
//     * @param cameraStopRecordParamDTO
//     * @return
//     * @throws Exception
//     */
//    CameraRecordStatusDTO getRecordStatus(CameraStopRecordParamDTO cameraStopRecordParamDTO) throws Exception;
//
//    /**
//     * 查询手动录像编号
//     *
//     * @param cameraRecordSearchParamDTO
//     * @return
//     * @throws Exception
//     */
//    CameraRecordSearchDTO getRecordSearch(CameraRecordSearchParamDTO cameraRecordSearchParamDTO) throws Exception;
//
//    /**
//     * 查询编码设备列表
//     *
//     * @param cameraEncodeDevParamDTO
//     * @return
//     * @throws Exception
//     */
//    PageTemplateDTO<CameraEncodeDevDTO> getEncodeDev(CameraEncodeDevParamDTO cameraEncodeDevParamDTO) throws Exception;
//
//    /**
//     * 获取监控点在线状态
//     *
//     * @param cameraStatusParamDTO
//     * @return
//     * @throws Exception
//     */
//    PageTemplateDTO<CameraStatusDTO> getCameraStatus(CameraStatusParamDTO cameraStatusParamDTO) throws Exception;
//
//    /**
//     * 根据监控点列表查询录像完整性结果
//     *
//     * @param recordResultListParamDTO
//     * @return
//     * @throws Exception
//     */
//    PageTemplateDTO<RecordResultListDTO> getRecordResult(RecordResultListParamDTO recordResultListParamDTO) throws Exception;
//
//    /**
//     * 根据监控点列表查询视频质量诊断结果
//     *
//     * @param videoQualityListParamDTO
//     * @return
//     * @throws Exception
//     */
//    PageTemplateDTO<VideoQualityListDTO> getVideoQualityResult(VideoQualityListParamDTO videoQualityListParamDTO) throws Exception;

}
