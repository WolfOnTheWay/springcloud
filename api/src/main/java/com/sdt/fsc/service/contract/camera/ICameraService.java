package com.sdt.fsc.service.contract.camera;

import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.entity.camera.*;
import com.sdt.fsc.entity.camera.DeviceLoginInfo;

import java.util.List;

/**
 *
 * @description: 视频数据服务接口
 * @author: hegonglei
 * @date: 2021-6-9 14:54
 */
public interface ICameraService {

    /**
     *
     * @description: 获取资源分组列表
     * @param
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 14:56
     */
    JSONObject getGroupList() throws Exception;

    /**
     *
     * @description:  获取资源分组下的摄像机列表
     * @param cameraListParamDTO
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 15:04
     */
    JSONObject getCameraList(CameraListParamDTO cameraListParamDTO) throws Exception;

    /**
     *
     * @description: 获取业务分组列表
     * @param
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 14:56
     */
    JSONObject getBusinessGroupList() throws Exception;

    /**
     *
     * @description:  获取虚拟组织下的摄像机列表
     * @param cameraListParamDTO
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 15:04
     */
    JSONObject getVirtualOrganizationCameraList(CameraListParamDTO cameraListParamDTO) throws Exception;

    /**
     *
     * @description: 获取摄像机信息
     * @param id
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 15:04
     */
    JSONObject getCameraInfo(String id) throws Exception;

    /**
     *
     * @description: 获取视频预览URL
     * @param cameraIdDTO
     * @return: java.lang.String
     * @author: hegonglei
     * @date: 2021-7-3 16:27
     */
    CameraPreviewUrlVO getCameraPreviewUrl(CameraIdDTO cameraIdDTO) throws Exception;

    /**
     *
     * @description: 获取视频录像回放URL
     * @param cameraIdDTO
     * @return: java.lang.String
     * @author: hegonglei
     * @date: 2021-7-3 16:27
     */
    CameraPlaybackUrlVO getCameraPlaybackUrl(CameraIdDTO cameraIdDTO) throws Exception;

    /**
     *
     * @description: 获取视频播放服务器URL
     * @return: java.lang.String
     * @author: hegonglei
     * @date: 2021-7-3 16:27
     */
    String getVideoServeUrl() throws Exception;

    /**
     * @description: 获取CVR登录设备信息
     * @param cameraId
     * @return
     * @throws Exception
     */
    DeviceLoginInfo getDeviceLoginInfoByCameraId(String cameraId) throws Exception;

    /**
     *
     * @description: 获取摄像头对应的通道编号
     * @param cameraId
     * @return: java.lang.Integer
     * @author: hegonglei
     * @date: 2021-7-5 15:17
     */
    Integer getChannelNoByCameraId(String cameraId) throws Exception;

    /**
     * @description: 获取资源设备信息
     * @return
     * @throws Exception
     */
    List<DeviceInfo> getDeviceInfoList() throws Exception;

    /**
     * @description: 添加资源设备信息
     * @param addDeviceInfoParamDTO
     * @return
     * @throws Exception
     */
    Boolean addDeviceInfo(AddDeviceInfoParamDTO addDeviceInfoParamDTO) throws Exception;

    /**
     * @description: 编辑资源设备信息
     * @param updateDeviceInfoParamDTO
     * @return
     * @throws Exception
     */
    Boolean updateDeviceInfoById(UpdateDeviceInfoParamDTO updateDeviceInfoParamDTO) throws Exception;

    /**
     * @description: 删除资源设备信息
     * @param id
     * @return
     * @throws Exception
     */
    Boolean deleteDeviceInfoById(String id) throws Exception;

    List<CameraInfo> getAllCameraList() throws Exception;

}
