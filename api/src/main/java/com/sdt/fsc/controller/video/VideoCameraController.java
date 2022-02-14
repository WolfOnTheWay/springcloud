package com.sdt.fsc.controller.video;

import com.sdt.fsc.entity.video.*;
import com.sdt.fsc.entity.common.PageTemplateDTO;
import com.sdt.fsc.service.contract.video.IVideoCameraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/8/10 10:13
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/camera")
@Api(description = "海康视频监控API")
public class VideoCameraController {
    @Resource
    IVideoCameraService videoCameraService;

//    @PostMapping("/resource/v1/regions")
//    @ApiOperation(value = "分页获取区域列表")
//    PageTemplateDTO<RegionListDTO> getRegionList(
//            @RequestBody RegionListParamDTO regionListParamDTO
//    ) throws Exception {
//        return videoCameraService.getRegionList(regionListParamDTO);
//    }
//
//    @PostMapping("/resource/cameras")
//    @ApiOperation(value = "分页获取监控点资源")
//    PageTemplateDTO<CameraNewDTO> getCameraList(
//            @RequestBody CameraListParamDTO cameraListParamDTO
//    ) throws Exception {
//        return videoCameraService.getCameraList(cameraListParamDTO);
//    }
//
//    @PostMapping("/resource/cameras_advance")
//    @ApiOperation(value = "获取监控点资源advance")
//    PageTemplateDTO<CameraDTO> getCameraListAdvance(
//            @RequestBody CameraListAdvanceParamDTO cameraListAdvanceParamDTO
//    ) throws Exception {
//        return videoCameraService.getCameraListAdvance(cameraListAdvanceParamDTO);
//    }
//
//    @PostMapping("/video/preview_url")
//    @ApiOperation(value = "获取监控点预览取流URL")
//    CameraPreviewUrlDTO getCameraPreviewUrl(
//            @RequestBody CameraPreviewUrlParamDTO cameraPreviewUrlParamDTO
//    ) throws Exception {
//        return videoCameraService.getCameraPreviewUrl(cameraPreviewUrlParamDTO);
//    }
//
//    @PostMapping("video/ptzs/controlling")
//    @ApiOperation(value = "根据监控点编号进行云台操作")
//    String ptzControlling(
//            @RequestBody CameraPTZParamDTO cameraPTZParamDTO
//    ) throws Exception {
//        return videoCameraService.ptzControlling(cameraPTZParamDTO);
//    }
//
//    @PostMapping("/video/playback_url")
//    @ApiOperation(value = "获取监控点回放取流URL")
//    CameraPlaybackUrlDTO getCameraPlaybackUrl(
//            @RequestBody CameraPlaybackUrlParamDTO cameraPlaybackUrlParamDTO
//    ) throws Exception {
//        return videoCameraService.getCameraPlaybackUrl(cameraPlaybackUrlParamDTO);
//    }
//
//    @PostMapping("/video/manual_capture")
//    @ApiOperation(value = "手动抓图")
//    CameraCaptureDTO capture(
//            @RequestBody CameraCaptureParamDTO cameraCaptureParamDTO
//    ) throws Exception {
//        return videoCameraService.capture(cameraCaptureParamDTO);
//    }
//
//    @PostMapping("video/manual_record/start")
//    @ApiOperation(value = "开始手动录像")
//    CameraStartRecordDTO startRecord(
//            @RequestBody CameraStartRecordParamDTO cameraStartRecordParamDTO
//    ) throws Exception {
//        return videoCameraService.startRecord(cameraStartRecordParamDTO);
//    }
//
//    @PostMapping("video/manual_record/stop")
//    @ApiOperation(value = "停止手动录像")
//    String stopRecord(
//            @RequestBody CameraStopRecordParamDTO cameraStopRecordParamDTO
//    ) throws Exception {
//        return videoCameraService.stopRecord(cameraStopRecordParamDTO);
//    }
//
//    @PostMapping("video/manual_record/status")
//    @ApiOperation(value = "获取手动录像状态")
//    CameraRecordStatusDTO getRecordStatus(
//            @RequestBody CameraStopRecordParamDTO cameraStopRecordParamDTO
//    ) throws Exception {
//        return videoCameraService.getRecordStatus(cameraStopRecordParamDTO);
//    }
//
//    @PostMapping("video/manual_record/task_id/search")
//    @ApiOperation(value = "查询手动录像编号")
//    CameraRecordSearchDTO getRecordSearch(
//            @RequestBody CameraRecordSearchParamDTO cameraRecordSearchParamDTO
//    ) throws Exception {
//        return videoCameraService.getRecordSearch(cameraRecordSearchParamDTO);
//    }
//
//    @PostMapping("/resource/encode_dev")
//    @ApiOperation(value = "查询编码设备列表")
//    PageTemplateDTO<CameraEncodeDevDTO> getEncodeDev(
//            @RequestBody CameraEncodeDevParamDTO cameraEncodeDevParamDTO
//    ) throws Exception{
//        return videoCameraService.getEncodeDev(cameraEncodeDevParamDTO);
//    }
//
//    @PostMapping("/video/status")
//    @ApiOperation(value = "获取监控点在线状态")
//    PageTemplateDTO<CameraStatusDTO> getCameraStatus(
//            @RequestBody CameraStatusParamDTO cameraStatusParamDTO
//    ) throws Exception{
//        return videoCameraService.getCameraStatus(cameraStatusParamDTO);
//    }
//
//    @PostMapping("/video/record/result")
//    @ApiOperation(value = "根据监控点列表查询录像完整性结果")
//    PageTemplateDTO<RecordResultListDTO> getRecordResult(
//            @RequestBody RecordResultListParamDTO recordResultListParamDTO
//    ) throws Exception{
//        return videoCameraService.getRecordResult(recordResultListParamDTO);
//    }
//
//    @PostMapping("/video/quality/result")
//    @ApiOperation(value = "根据监控点列表查询视频质量诊断结果")
//    PageTemplateDTO<VideoQualityListDTO> getVideoQualityResult(
//            @RequestBody VideoQualityListParamDTO videoQualityListParamDTO
//    ) throws Exception{
//        return videoCameraService.getVideoQualityResult(videoQualityListParamDTO);
//    }

}
