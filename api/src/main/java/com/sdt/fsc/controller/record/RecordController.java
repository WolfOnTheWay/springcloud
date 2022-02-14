package com.sdt.fsc.controller.record;

import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.entity.camera.CameraIdDTO;
import com.sdt.fsc.entity.record.*;
import com.sdt.fsc.service.contract.record.IDVRRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/manualRecord")
@Api(description = "手动录像控制API")
public class RecordController {

    @Resource
    private IDVRRecordService idvrRecordService;

    @ApiOperation(value = "开始手动录像")
    @PostMapping("/startRecord")
    public BaseResponse<Boolean> startRecord(@ApiParam(value = "开始手动录像参数", required = true)
                                           @RequestBody @Valid RecordControlVO recordControlVO) throws Exception {
        return idvrRecordService.startRecord(recordControlVO);
    }

    @PostMapping("stopRecord")
    @ApiOperation(value = "停止手动录像")
    public BaseResponse<Boolean> stopRecord(@ApiParam(value = "停止手动录像参数", required = true)
                                          @RequestBody RecordControlVO recordControlVO) throws Exception {
        return idvrRecordService.stopRecord(recordControlVO);
    }

    @PostMapping("/getRecordStatus")
    @ApiOperation(value = "获取当前通道的手动录像状态")
    public BaseResponse<Integer> getRecordStatus(@ApiParam(value = "摄像头ID", required = true)
                                                 @RequestBody CameraIdDTO cameraIdDTO) throws Exception {
        return idvrRecordService.getRecordStatus(cameraIdDTO);
    }

    @PostMapping("/status")
    @ApiOperation(value = "查询当前设备信息状态")
    public BaseResponse<Object> findEquipmentInfo(@ApiParam(value = "查询设备状态参数", required = true)
                                                 @RequestBody FileParamDTO fileParamDTO) throws Exception {
        return idvrRecordService.findEquipmentInfo(fileParamDTO);
    }

    /*@PostMapping("/findVideoInfo")
    @ApiOperation(value = "查询手动录像文件信息")
    public List<VideoInfo> findVideoInfo(@ApiParam(value = "手动录像文件查找参数", required = true)
                                         @RequestBody VideoInfoParamDTO videoInfoParamDTO) throws Exception {
        return idvrRecordService.findVideoInfo(videoInfoParamDTO);
    }*/

    /*@PostMapping("/saveVideoTagInfo")
    @ApiOperation(value = "保存视频标签")
    public void saveVideoTagInfo(@ApiParam(value = "给视频文件打标签", required = true)
                                 @RequestBody VideoTagDTO videoTagDTO) {
        idvrRecordService.saveVideoTagInfo(videoTagDTO);
    }*/

    @PostMapping("/getRecordFileNameInfo")
    @ApiOperation(value = "获取手动录像文件信息")
    public List<ManualRecordInfo> getRecordFileNameInfo(@ApiParam(value = "获取手动录像文件信息参数", required = true)
                                                       @RequestBody @Valid FileParamDTO fileParamDTO) throws Exception {
        return idvrRecordService.getRecordFileNameInfo(fileParamDTO);
    }

    @PostMapping("/findManualRecordInfo")
    @ApiOperation(value = "查询手动录像文件信息")
    public List<ManualRecordInfo> findManualRecordInfo(@ApiParam(value = "查询手动录像文件信息参数", required = true)
                                         @RequestBody @Valid ManualRecordInfoParamDTO manualRecordInfoParamDTO) throws Exception {
        return idvrRecordService.findManualRecordInfo(manualRecordInfoParamDTO);
    }

    @PostMapping("/updateRecordLabel")
    @ApiOperation(value = "保存录像标签")
    public BaseResponse<Boolean> updateRecordLabel(@ApiParam(value = "保存录像标签参数", required = true)
                                 @RequestBody @Valid RecordLabelDTO recordLabelDTO) {
        return idvrRecordService.updateRecordLabel(recordLabelDTO);
    }

    @PostMapping("/getRecordTimeSpan")
    @ApiOperation(value = "获取通道录像起止时间")
    public JSONObject getRecordTimeSpan(@ApiParam(value = "获取通道录像起止时间参数", required = true)
                               @RequestBody RecordTimeSpanParamDTO recordTimeSpanParamDTO) throws Exception {
        return idvrRecordService.getRecordTimeSpan(recordTimeSpanParamDTO);
    }

    /*@PostMapping("/stopRecord")
    @ApiOperation(value = "停止录像并保存录像标签")
    public BaseResponse<Boolean> StopRecordWithRecordLabel(@ApiParam(value = "停止录像并保存录像标签", required = true)
                                             @RequestBody @Valid StopRecordParamDTO stopRecordParamDTO) throws Exception {
        return idvrRecordService.StopRecordWithRecordLabel(stopRecordParamDTO);
    }*/

    /*@PostMapping("/downloadRecord")
    @ApiOperation(value = "手动录像下载接口")
    public BaseResponse<Object> downloadVideoByFileName(@ApiParam(value = "手动录像下载参数实体", required = true)
                                                       @RequestBody DownloadVideoParamDTO downloadVideoParamDTO) throws Exception {
        return idvrRecordService.downloadVideoByFileName(downloadVideoParamDTO);
    }*/

    /*@PostMapping("/getDownloadProgress")
    @ApiOperation(value = "获取下载文件的进度")
    public Map<String, Object> getDownloadProgress(@ApiParam(value = "下载文件的句柄", required = true)
                                                       @RequestParam String lPlayHandle) throws Exception {
        return idvrRecordService.getDownloadProgress(lPlayHandle);
    }*/

    /*
    @GetMapping("/getDeviceChannelInfo")
    @ApiOperation(value = "获取设备通道编号信息")
    public Map<String, Object> getDeviceChannelInfo(@ApiParam(value = "通道号", required = true)  @RequestParam String channel) {
        Map<String, Object> resultMap = idvrRecordService.getDeviceChannelInfo(channel);
        return resultMap;
    }*/

}
