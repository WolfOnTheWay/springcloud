package com.sdt.fsc.service.impl.camera;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.camera.*;
import com.sdt.fsc.exception.CustomException;
import com.sdt.fsc.mapper.dm.DeviceInfoMapper;
import com.sdt.fsc.mapper.mysql2.*;
import com.sdt.fsc.service.contract.camera.ICameraService;
import com.sdt.fsc.util.RestApiUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author:
 * @date: 2021-6-9 15:06
 */
@Service
public class CameraServiceImpl implements ICameraService {

    @Autowired
    private RestApiUtils restApiUtils;
    @Value("${dcm.preurl}")
    private String preUrl;
    @Value("${dcm.serve}")
    private String serve;
    @Resource
    DevDeviceCameraMapper devDeviceCameraMapper;
    @Resource
    DevDeviceMapper devDeviceMapper;
    @Resource
    DevAccessSdkMapper devAccessSdkMapper;
    @Resource
    DevAccessGbMapper devAccessGbMapper;
    @Resource
    DevGbPlatformMapper devGbPlatformMapper;
    @Resource
    DeviceInfoMapper deviceInfoMapper;

    @Override
    public JSONObject getGroupList() throws Exception {
        String url = preUrl + "group/list";
        return restApiUtils.doHttpsGetRequest(url);
    }

    @Override
    public JSONObject getCameraList(CameraListParamDTO cameraListParamDTO) throws Exception {
        String url = preUrl + "group/camera/list?id="+cameraListParamDTO.getId()+"&page="+(cameraListParamDTO.getPageNo()-1)+"&page-size="+cameraListParamDTO.getPageSize()+"&order="+cameraListParamDTO.getOrder();
        return restApiUtils.doHttpsGetRequest(url);
    }

    @Override
    public JSONObject getBusinessGroupList() throws Exception {
        String url = preUrl + "business-group/list";
        return restApiUtils.doHttpsGetRequest(url);
    }

    @Override
    public JSONObject getVirtualOrganizationCameraList(CameraListParamDTO cameraListParamDTO) throws Exception {
        String url = preUrl + "virtual-organization/camera/list?id="+cameraListParamDTO.getId()+"&page="+(cameraListParamDTO.getPageNo()-1)+"&page-size="+cameraListParamDTO.getPageSize()+"&order="+cameraListParamDTO.getOrder();
        return restApiUtils.doHttpsGetRequest(url);
    }

    @Override
    public JSONObject getCameraInfo(String id) throws Exception {
        String url = preUrl + "camera/info?id=" + id;
        return restApiUtils.doHttpsGetRequest(url);
    }

    @Override
    public CameraPreviewUrlVO getCameraPreviewUrl(CameraIdDTO cameraIdDTO) throws Exception {
        CameraPreviewUrlVO cameraPreviewUrlVO = new CameraPreviewUrlVO();
        JSONObject cameraInfo = this.getCameraInfo(cameraIdDTO.getCameraId());
        cameraPreviewUrlVO.setCameraId(cameraInfo.get("ID").toString());
        cameraPreviewUrlVO.setCameraName(cameraInfo.get("Name").toString());
        cameraPreviewUrlVO.setServe(serve);
        String mainUrl = serve + "/" + cameraIdDTO.getCameraId() + "#0";
        String subUrl = serve + "/" + cameraIdDTO.getCameraId() + "#1";
        cameraPreviewUrlVO.setMainUrl(mainUrl);
        cameraPreviewUrlVO.setSubUrl(subUrl);
        cameraPreviewUrlVO.setPtzEnable((Boolean) cameraInfo.get("PTZEnable"));
        return cameraPreviewUrlVO;
    }

    @Override
    public CameraPlaybackUrlVO getCameraPlaybackUrl(CameraIdDTO cameraIdDTO) throws Exception {
        CameraPlaybackUrlVO cameraPlaybackUrlVO = new CameraPlaybackUrlVO();
        JSONObject cameraInfo = this.getCameraInfo(cameraIdDTO.getCameraId());
        cameraPlaybackUrlVO.setCameraId(cameraInfo.get("ID").toString());
        cameraPlaybackUrlVO.setCameraName(cameraInfo.get("Name").toString());
        cameraPlaybackUrlVO.setServe(serve);
        String playbackUrl = serve + "/" + cameraIdDTO.getCameraId();
        cameraPlaybackUrlVO.setUrl(playbackUrl);
        return cameraPlaybackUrlVO;
    }

    public CameraPlaybackUrlVO getMergeCameraPlaybackUrl(CameraPlaybackUrlParamDTO cameraPlaybackUrlParamDTO) throws Exception {
        CameraPlaybackUrlVO cameraPlaybackUrlVO = new CameraPlaybackUrlVO();
        String cameraId = cameraPlaybackUrlParamDTO.getCameraId();
        if (cameraPlaybackUrlParamDTO.getRecordType() == 0) {
            cameraPlaybackUrlVO.setCameraId(cameraId);
            String playbackUrl = serve + "/" + cameraId;
            cameraPlaybackUrlVO.setUrl(playbackUrl);
        } else if (cameraPlaybackUrlParamDTO.getRecordType() == 1) {
            String actualCameraId = getCameraIdOfMergeChannels(cameraPlaybackUrlParamDTO.getCameraId());
            cameraPlaybackUrlVO.setCameraId(actualCameraId);
            String playbackUrl = serve + "/" + actualCameraId;
            cameraPlaybackUrlVO.setUrl(playbackUrl);
        }
        cameraPlaybackUrlVO.setServe(serve);
        return cameraPlaybackUrlVO;
    }

    @Override
    public String getVideoServeUrl() {
        return serve;
    }

    public String getCameraIdOfMergeChannels(String cameraId) {
        QueryWrapper<DevDeviceCamera> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", cameraId);
        String mergeCameraId = "";
        return devDeviceCameraMapper.selectOne(queryWrapper).getMerge();
    }

    @Override
    public Integer getChannelNoByCameraId(String cameraId) throws Exception {
        Integer channelNo = -1;
        QueryWrapper<DevDeviceCamera> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", cameraId);
        String sChannelNo = devDeviceCameraMapper.selectOne(queryWrapper).getChanNo();
        if (!"".equals(sChannelNo)) {
            channelNo = Integer.parseInt(sChannelNo);
        }
        return channelNo;
    }

    @Override
    public DeviceLoginInfo getDeviceLoginInfoByCameraId(String cameraId) throws Exception {
        DeviceLoginInfo deviceLoginInfo = new DeviceLoginInfo();
        String deviceId = devDeviceCameraMapper.selectById(cameraId).getDeviceId();
        DevDevice devDevice = devDeviceMapper.selectById(deviceId);
        String access = devDevice.getAccess();
        if (access.equals("sdk")) {
            deviceLoginInfo.setIp(devDevice.getHost());
            deviceLoginInfo.setPort(devDevice.getPort());
            DevAccessSdk devAccessSdk = devAccessSdkMapper.selectById(deviceId);
            deviceLoginInfo.setUsername(devAccessSdk.getUsername());
            deviceLoginInfo.setPassword(devAccessSdk.getPassword());
        } else if (access.equals("gb")) {
            String gbId = devAccessGbMapper.selectById(deviceId).getGbId();
            DeviceInfo deviceInfo = deviceInfoMapper.selByGBID(gbId);
            deviceLoginInfo.setIp(deviceInfo.getIp());
            deviceLoginInfo.setPort(deviceInfo.getPort());
            deviceLoginInfo.setUsername(deviceInfo.getUsername());
            deviceLoginInfo.setPassword(deviceInfo.getPassword());
        } else if (access.equals("gb_platform")) {
            String gbId = devGbPlatformMapper.selectById(deviceId).getGbId();
            DeviceInfo deviceInfo = deviceInfoMapper.selByGBID(gbId);
            deviceLoginInfo.setIp(deviceInfo.getIp());
            deviceLoginInfo.setPort(deviceInfo.getPort());
            deviceLoginInfo.setUsername(deviceInfo.getUsername());
            deviceLoginInfo.setPassword(deviceInfo.getPassword());
        }
        return deviceLoginInfo;
    }


    @Override
    public List<DeviceInfo> getDeviceInfoList() throws Exception {
        List<DeviceInfo> deviceInfoList = new ArrayList<>();
        QueryWrapper<DeviceInfo> wrapper = new QueryWrapper<>();
        deviceInfoList = deviceInfoMapper.selectList(wrapper);
        return deviceInfoList;
    }

    //添加设备信息
    @Override
    public Boolean addDeviceInfo(AddDeviceInfoParamDTO addDeviceInfoParamDTO) throws Exception {
        QueryWrapper<DeviceInfo> wrapper = new QueryWrapper<>();
        List<DeviceInfo> deviceInfoList = deviceInfoMapper.selectList(wrapper);
        for (DeviceInfo device : deviceInfoList) {
            if (device.getGbId().equals(addDeviceInfoParamDTO.getGbId())) {
                return false;
            }
        }
        DeviceInfo device = new DeviceInfo();
        device.setId(UUID.randomUUID().toString());
        device.setName(addDeviceInfoParamDTO.getName());
        device.setIp(addDeviceInfoParamDTO.getIp());
        device.setPort(addDeviceInfoParamDTO.getPort());
        device.setUsername(addDeviceInfoParamDTO.getUsername());
        device.setPassword(addDeviceInfoParamDTO.getPassword());
        device.setGbId(addDeviceInfoParamDTO.getGbId());
        try {
            Boolean flag = deviceInfoMapper.insert(device)==1;
            return flag;
        } catch (Exception e) {
            throw new CustomException(CustomErrorType.CRUD_INSERT_ERROR);
        }
    }

    //编辑设备登录信息
    @Override
    public Boolean updateDeviceInfoById(UpdateDeviceInfoParamDTO updateDeviceInfoParamDTO) throws Exception {
        DeviceInfo device = new DeviceInfo();
        BeanUtils.copyProperties(updateDeviceInfoParamDTO, device);
        try {
            Boolean flag = deviceInfoMapper.updateById(device)==1;
            return flag;
        } catch (Exception e) {
            throw new CustomException(CustomErrorType.CRUD_UPDATE_ERROR);
        }
    }

    //删除设备登录信息
    @Override
    public Boolean deleteDeviceInfoById(String id) throws Exception {
        try {
            Boolean flag = deviceInfoMapper.deleteById(id)==1;
            return flag;
        } catch (Exception e) {
            throw new CustomException(CustomErrorType.CRUD_DELETE_ERROR);
        }
    }

    @Override
    public List<CameraInfo> getAllCameraList() throws Exception {
        List<CameraInfo> cameraInfoList = new ArrayList<>();
        List<DevDeviceCamera> devDeviceCameraList = devDeviceCameraMapper.getAllDevDeviceCameraList();
        for (DevDeviceCamera devDeviceCamera : devDeviceCameraList) {
            CameraInfo cameraInfo = new CameraInfo();
            cameraInfo.setId(devDeviceCamera.getId());
            cameraInfo.setName(devDeviceCamera.getName());
            cameraInfo.setGroupId(devDeviceCamera.getGroupId());
            cameraInfo.setChannelNo(devDeviceCamera.getChanNo());
            cameraInfo.setVoiceTalkChannelId(devDeviceCamera.getVoiceTalkChannelId());
            cameraInfo.setLongitude(devDeviceCamera.getLongitude());
            cameraInfo.setLatitude(devDeviceCamera.getLatitude());
            cameraInfo.setDirection(devDeviceCamera.getDirection());
            if ("ON"==devDeviceCamera.getSxjzxzt()) {
                cameraInfo.setOnline(true);
            } else {
                cameraInfo.setOnline(false);
            }
            if ("yes"==devDeviceCamera.getPtz()) {
                cameraInfo.setPTZEnable(true);
            } else {
                cameraInfo.setPTZEnable(false);
            }
            cameraInfo.setAngle(devDeviceCamera.getAngle());
            cameraInfo.setRadius(devDeviceCamera.getRadius());
            cameraInfo.setGbId(devDeviceCamera.getGbId());
            cameraInfo.setManufacturer(devDeviceCamera.getManufacturer());
            cameraInfo.setDeviceId(devDeviceCamera.getDeviceId());
            cameraInfo.setAddress(devDeviceCamera.getAddress());
            if (1==devDeviceCamera.getIsLocal()) {
                cameraInfo.setLocal(true);
            } else {
                cameraInfo.setLocal(false);
            }
            cameraInfoList.add(cameraInfo);
        }
        return cameraInfoList;
    }

}
