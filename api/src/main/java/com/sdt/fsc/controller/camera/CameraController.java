package com.sdt.fsc.controller.camera;

import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.entity.camera.*;
import com.sdt.fsc.service.impl.camera.CameraServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @description: 视频接入配置控制层
 * @author: hedonglei
 * @date: 2021-6-9 14:34
 */
@RestController
@CrossOrigin
@RequestMapping(value="/api/v1/video")
@Api(description = "视频接入配置API")
public class CameraController {
    @Resource
    private CameraServiceImpl cameraServiceImpl;

    @GetMapping("/group/list")
    @ResponseBody
    @ApiOperation(value = "获取资源分组列表")
    public JSONObject groupList()throws Exception{
        return cameraServiceImpl.getGroupList();
    }

    @PostMapping("/group/camera/list")
    @ResponseBody
    @ApiOperation(value = "获取资源组摄像机列表")
    public JSONObject cameraList(@RequestBody @Valid CameraListParamDTO cameraListParamDTO)throws Exception{
        return cameraServiceImpl.getCameraList(cameraListParamDTO);
    }

    @GetMapping("/businessGroup/list")
    @ResponseBody
    @ApiOperation(value = "获取资源分组列表")
    public JSONObject businessGroupList()throws Exception{
        return cameraServiceImpl.getBusinessGroupList();
    }

    @PostMapping("/virtualOrganization/camera/list")
    @ResponseBody
    @ApiOperation(value = "获取业务组摄像机列表")
    public JSONObject businessCameraList(@RequestBody @Valid CameraListParamDTO cameraListParamDTO)throws Exception{
        return cameraServiceImpl.getVirtualOrganizationCameraList(cameraListParamDTO);
    }

    @PostMapping("/camera/info")
    @ResponseBody
    @ApiOperation(value = "获取摄像机信息")
    public JSONObject cameraInfo(@RequestBody CameraIdDTO cameraIdDTO)throws Exception{
        return cameraServiceImpl.getCameraInfo(cameraIdDTO.getCameraId());
    }

    @PostMapping("/camera/previewUrl")
    @ResponseBody
    @ApiOperation(value = "获取实时预览URL")
    public CameraPreviewUrlVO cameraPreviewUrl(@RequestBody CameraIdDTO cameraIdDTO)throws Exception{
        return cameraServiceImpl.getCameraPreviewUrl(cameraIdDTO);
    }

    @PostMapping("/camera/playbackUrl")
    @ResponseBody
    @ApiOperation(value = "获取录像回放URL")
    public CameraPlaybackUrlVO cameraPlaybackUrl(@RequestBody CameraIdDTO cameraIdDTO)throws Exception{
        return cameraServiceImpl.getCameraPlaybackUrl(cameraIdDTO);
    }

    @GetMapping("/camera/serveUrl")
    @ResponseBody
    @ApiOperation(value = "获取视频服务器URL")
    public String videoServeUrl()throws Exception{
        return cameraServiceImpl.getVideoServeUrl();
    }

    @GetMapping("/device/list")
    @ResponseBody
    @ApiOperation(value = "获取资源设备列表")
    public List<DeviceInfo> getDeviceInfoList()throws Exception {
        return cameraServiceImpl.getDeviceInfoList();
    }

    @PostMapping("/device/addDeviceInfo")
    @ResponseBody
    @ApiOperation(value = "添加资源设备信息")
    public Boolean addDeviceInfo(@RequestBody AddDeviceInfoParamDTO addDeviceInfoParamDTO)throws Exception{
        return cameraServiceImpl.addDeviceInfo(addDeviceInfoParamDTO);
    }

    @PostMapping("/device/updateDeviceInfoById")
    @ResponseBody
    @ApiOperation(value = "编辑资源设备信息")
    public Boolean updateDeviceInfoById(@RequestBody UpdateDeviceInfoParamDTO updateDeviceInfoParamDTO)throws Exception{
        return cameraServiceImpl.updateDeviceInfoById(updateDeviceInfoParamDTO);
    }

    @GetMapping("/device/deleteDeviceInfoById")
    @ResponseBody
    @ApiOperation(value = "删除资源设备信息")
    public Boolean deleteDeviceInfoById(@RequestParam String id)throws Exception{
        return cameraServiceImpl.deleteDeviceInfoById(id);
    }

    @GetMapping("/camera/list/all")
    @ResponseBody
    @ApiOperation(value = "获取所有摄像机列表")
    public List<CameraInfo> getAllCameraList() throws Exception{
        return cameraServiceImpl.getAllCameraList();
    }

}
