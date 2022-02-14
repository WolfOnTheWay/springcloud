package com.sdt.fsc.service.impl.video;

import com.sdt.fsc.entity.common.PageTemplateDTO;
import com.sdt.fsc.entity.video.*;
import com.sdt.fsc.service.contract.video.IVideoCameraService;
import com.sdt.fsc.util.RestApiHKUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/8/10 11:42
 */
@Service
public class VideoCameraServiceImpl implements IVideoCameraService {
    @Resource
    RestApiHKUtils restApiHKUtils;
    @Value("${hk.url}")
    private String hk;

//    @Override
//    public PageTemplateDTO<RegionListDTO> getRegionList(RegionListParamDTO regionListParamDTO) throws Exception {
//        String url = hk + "resource/v1/regions";
//        return restApiHKUtils.getInfoFromHK(PageTemplateDTO.class,regionListParamDTO,url);
//    }
//
//    @Override
//    public PageTemplateDTO<CameraNewDTO> getCameraList(CameraListParamDTO cameraListParamDTO) throws Exception {
//        String url = hk + "resource/v1/cameras";
//        return restApiHKUtils.getInfoFromHK(PageTemplateDTO.class,cameraListParamDTO,url);
//    }
//
//    @Override
//    public PageTemplateDTO<CameraDTO> getCameraListAdvance(CameraListAdvanceParamDTO cameraListAdvanceParamDTO) throws Exception {
//        String url = hk + "resource/v1/camera/advance/cameraList";
//        return restApiHKUtils.getInfoFromHK(PageTemplateDTO.class,cameraListAdvanceParamDTO,url);
//    }
//
//    @Override
//    public CameraPreviewUrlDTO getCameraPreviewUrl(CameraPreviewUrlParamDTO cameraPreviewUrlParamDTO) throws Exception {
//        String url = hk + "video/v1/cameras/previewURLs";
//        return restApiHKUtils.getInfoFromHK(CameraPreviewUrlDTO.class,cameraPreviewUrlParamDTO,url);
//    }
//
//    @Override
//    public String ptzControlling(CameraPTZParamDTO cameraPTZParamDTO) throws Exception {
//        String url = hk + "video/v1/ptzs/controlling";
//        return restApiHKUtils.getInfoFromHK(String.class,cameraPTZParamDTO,url);
//    }
//
//    @Override
//    public CameraPlaybackUrlDTO getCameraPlaybackUrl(CameraPlaybackUrlParamDTO cameraPlaybackUrlParamDTO) throws Exception {
//        String url = hk + "video/v1/cameras/playbackURLs";
//        return restApiHKUtils.getInfoFromHK(CameraPlaybackUrlDTO.class,cameraPlaybackUrlParamDTO,url);
//    }
//
//    @Override
//    public CameraCaptureDTO capture(CameraCaptureParamDTO cameraCaptureParamDTO) throws Exception {
//        String url = hk + "video/v1/manualCapture";
//        return restApiHKUtils.getInfoFromHK(CameraCaptureDTO.class,cameraCaptureParamDTO,url);
//    }
//
//    @Override
//    public CameraStartRecordDTO startRecord(CameraStartRecordParamDTO cameraStartRecordParamDTO) throws Exception {
//        String url = hk + "video/v1/manualRecord/start";
//        return restApiHKUtils.getInfoFromHK(CameraStartRecordDTO.class,cameraStartRecordParamDTO,url);
//    }
//
//    @Override
//    public String stopRecord(CameraStopRecordParamDTO cameraStopRecordParamDTO) throws Exception {
//        String url = hk + "video/v1/manualRecord/stop";
//        return restApiHKUtils.getInfoFromHK(String.class,cameraStopRecordParamDTO,url);
//    }
//
//    @Override
//    public CameraRecordStatusDTO getRecordStatus(CameraStopRecordParamDTO cameraStopRecordParamDTO) throws Exception {
//        String url = hk + "video/v1/manualRecord/status";
//        return restApiHKUtils.getInfoFromHK(CameraRecordStatusDTO.class,cameraStopRecordParamDTO,url);
//    }
//
//    @Override
//    public CameraRecordSearchDTO getRecordSearch(CameraRecordSearchParamDTO cameraRecordSearchParamDTO) throws Exception {
//        String url = hk + "video/v1/manualRecord/taskId/search";
//        return restApiHKUtils.getInfoFromHK(CameraRecordSearchDTO.class,cameraRecordSearchParamDTO,url);
//    }
//
//    @Override
//    public PageTemplateDTO<CameraEncodeDevDTO> getEncodeDev(CameraEncodeDevParamDTO cameraEncodeDevParamDTO) throws Exception {
//        String url = hk + "resource/v1/encodeDevice/search";
//        return restApiHKUtils.getInfoFromHK(PageTemplateDTO.class,cameraEncodeDevParamDTO,url);
//    }
//
//    @Override
//    public PageTemplateDTO<CameraStatusDTO> getCameraStatus(CameraStatusParamDTO cameraStatusParamDTO) throws Exception {
//        String url = hk+"nms/v1/online/camera/get";
//        return restApiHKUtils.getInfoFromHK(PageTemplateDTO.class,cameraStatusParamDTO,url);
//    }
//
//    @Override
//    public PageTemplateDTO<RecordResultListDTO> getRecordResult(RecordResultListParamDTO recordResultListParamDTO) throws Exception {
//        String url = hk+"nms/v1/record/list";
//        return restApiHKUtils.getInfoFromHK(PageTemplateDTO.class,recordResultListParamDTO,url);
//    }
//
//    @Override
//    public PageTemplateDTO<VideoQualityListDTO> getVideoQualityResult(VideoQualityListParamDTO videoQualityListParamDTO) throws Exception {
//        String url = hk+"nms/v1/vqd/list";
//        return restApiHKUtils.getInfoFromHK(PageTemplateDTO.class,videoQualityListParamDTO,url);
//    }
}
