package com.sdt.fsc.service.impl.record;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.camera.CameraIdDTO;
import com.sdt.fsc.entity.camera.DeviceLoginInfo;
import com.sdt.fsc.entity.record.*;
import com.sdt.fsc.hik.HCNetSDK;
import com.sdt.fsc.mapper.dm.ManualRecordInfoMapper;
import com.sdt.fsc.service.contract.camera.ICameraService;
import com.sdt.fsc.service.contract.record.IDVRRecordService;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Slf4j
@Service
public class DVRRecordServiceImpl implements IDVRRecordService {

    @Autowired
    private ICameraService cameraServiceImpl;
    @Resource
    private ManualRecordInfoMapper manualRecordInfoMapper;

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    NativeLong lUserID;//用户句柄
    HCNetSDK.NET_DVR_DEVICEINFO_V30 m_struDeviceInfoV30;//设备信息
    HCNetSDK.NET_DVR_DEVICEINFO_V40 m_struDeviceInfoV40;//设备信息
    HCNetSDK.NET_DVR_IPPARACFG_V40 m_struIpparaCfg;//IP通道配置参数

    //设备登录login_V30
    public boolean loginV30(DeviceLoginInfo deviceLoginInfo){
        if (deviceLoginInfo != null){
            hCNetSDK.NET_DVR_Init();
            //设置超时时间
            hCNetSDK.NET_DVR_SetConnectTime(2000,1);
            hCNetSDK.NET_DVR_SetReconnect(100000,true);
            //设置设备的注册信息
            HCNetSDK.NET_DVR_DEVICEINFO_V30 net_dvr_deviceinfo_v30 = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
            lUserID = hCNetSDK.NET_DVR_Login_V30(deviceLoginInfo.getIp(), Short.parseShort(deviceLoginInfo.getPort().toString()), deviceLoginInfo.getUsername(), deviceLoginInfo.getPassword(), net_dvr_deviceinfo_v30);
//            net_dvr_deviceinfo_v30.read();
//            m_struDeviceInfoV30 = net_dvr_deviceinfo_v30;
            if (lUserID.intValue() == -1) {
                log.info("登录失败,NET_DVR_Login_V30 Failed,Error Code=" + hCNetSDK.NET_DVR_GetLastError());
                return false;
            } else {
                log.info("登录成功,NET_DVR_Login_V30 Succeed!");
                return true;
            }
        } else {
            return false;
        }
    }

    //设备登录login_V40
    public Boolean loginV40(DeviceLoginInfo deviceLoginInfo) {
        try {
            if (deviceLoginInfo != null){
                hCNetSDK.NET_DVR_Init();
                //设置超时时间
                hCNetSDK.NET_DVR_SetConnectTime(2000,1);
                hCNetSDK.NET_DVR_SetReconnect(100000,true);
                //设置设备的注册信息
                HCNetSDK.NET_DVR_USER_LOGIN_INFO m_struLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();//设备登录信息
                HCNetSDK.NET_DVR_DEVICEINFO_V40 m_deviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();//设备信息
                String m_sDeviceIP = deviceLoginInfo.getIp();//设备ip地址
                m_struLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
                System.arraycopy(m_sDeviceIP.getBytes(), 0, m_struLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());
                String m_sUsername = deviceLoginInfo.getUsername();//设备用户名
                m_struLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
                System.arraycopy(m_sUsername.getBytes(), 0, m_struLoginInfo.sUserName, 0, m_sUsername.length());
                String m_sPassword = deviceLoginInfo.getPassword();//设备密码
                m_struLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
                System.arraycopy(m_sPassword.getBytes(), 0, m_struLoginInfo.sPassword, 0, m_sPassword.length());
                m_struLoginInfo.wPort = Short.parseShort(deviceLoginInfo.getPort().toString());
                m_struLoginInfo.bUseAsynLogin = false;
                m_struLoginInfo.write();
                lUserID = hCNetSDK.NET_DVR_Login_V40(m_struLoginInfo, m_deviceInfo);
//            m_deviceInfo.read();
//            m_struDeviceInfoV40 = m_deviceInfo;
                if (lUserID.intValue() == -1) {
                    String errorMsg = hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError())));
                    log.info("登录失败,NET_DVR_Login_V40 Failed,错误消息:" + errorMsg);
                    return false;
                } else {
                    log.info("登录成功,NET_DVR_Login_V40 Succeed!");
                    m_struIpparaCfg = getIPParaCfg();
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //获取IP接入配置参数
    private HCNetSDK.NET_DVR_IPPARACFG_V40 getIPParaCfg() {
        IntByReference ibrBytesReturned = new IntByReference(0);
        HCNetSDK.NET_DVR_IPPARACFG_V40 net_dvr_ipparacfg_v40 = new HCNetSDK.NET_DVR_IPPARACFG_V40();
        net_dvr_ipparacfg_v40.write();
        //lpIpParaConfig 接收数据的缓冲指针
        Pointer lpIpParaConfig = net_dvr_ipparacfg_v40.getPointer();
        boolean bRet = hCNetSDK.NET_DVR_GetDVRConfig(lUserID, HCNetSDK.NET_DVR_GET_IPPARACFG_V40, new NativeLong(0), lpIpParaConfig, net_dvr_ipparacfg_v40.size(), ibrBytesReturned);
        net_dvr_ipparacfg_v40.read();
        if (!bRet) {
            log.info("获取IP接入配置参数失败,NET_DVR_GET_IPPARACFG_V40 Failed,错误消息:" +
                    hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError()))));
            return null;
        }
        return net_dvr_ipparacfg_v40;
    }

    //获取录像状态
    @Override
    public BaseResponse<Integer> getRecordStatus(CameraIdDTO cameraIdDTO) {
        //获取设备登录信息
        try {
            if (lUserID == null || lUserID.intValue() == -1) {
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(cameraIdDTO.getCameraId());
                boolean flag = loginV40(deviceLoginInfo);
                if(!flag){
                    return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR, -1);
                }
            }
            //视频接入配置系统必须设置正确通道号，否则报错
            Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(cameraIdDTO.getCameraId());
            if (iChannel==-1 || iChannel<m_struIpparaCfg.dwStartDChan || iChannel>(m_struIpparaCfg.dwStartDChan+m_struIpparaCfg.dwDChanNum-1)) {
                return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR, -1);
            }
            HCNetSDK.NET_DVR_WORKSTATE_V30 m_struWorkstateV30 = new HCNetSDK.NET_DVR_WORKSTATE_V30();
            boolean state = hCNetSDK.NET_DVR_GetDVRWorkState_V30(lUserID, m_struWorkstateV30);
            m_struWorkstateV30.read();
            if (!state){
                log.info("获取设备工作状态失败,NET_DVR_GetDVRWorkState_V30 Failed,错误消息:" +
                        hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError()))));
                return new BaseResponse<>(CustomErrorType.DVR_WorkState_ERROR, -1);
            } else {
                log.info("获取设备工作状态成功,NET_DVR_GetDVRWorkState_V30 Succeed.");
                Integer iByRecordStatic = (int) m_struWorkstateV30.struChanStatic[iChannel - 1].byRecordStatic;
                return new BaseResponse<>(CustomErrorType.SUCCESS, iByRecordStatic);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hCNetSDK.NET_DVR_Logout(lUserID);
            hCNetSDK.NET_DVR_Cleanup();
            lUserID = null;
            return new BaseResponse<>(CustomErrorType.DVR_RecordStatus_ERROR, -1);
        }
    }

    /**
     *
     * @description: 远程手动启动设备录像
     * @param recordControlVO
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-7-2 19:47
     */
    @Override
    public BaseResponse<Boolean> startRecord(RecordControlVO recordControlVO) {
        try {
            if (lUserID == null || lUserID.intValue() == -1) {
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(recordControlVO.getCameraId());
                boolean flag = loginV40(deviceLoginInfo);
                if(!flag){
                    return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR, false);
                }
            }
            //获取通道号
            Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(recordControlVO.getCameraId());
            if (iChannel==-1 || iChannel<m_struIpparaCfg.dwStartDChan || iChannel>(m_struIpparaCfg.dwStartDChan+m_struIpparaCfg.dwDChanNum-1)) {
                return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR, false);
            }
            boolean bRet = hCNetSDK.NET_DVR_StartDVRRecord(lUserID, new NativeLong((long) iChannel), new NativeLong(0));
            String errorMsg = hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError())));
            if (!bRet) {
                log.info("远程手动启动设备录像失败,NET_DVR_StartDVRRecord Failed,错误消息:" + errorMsg);
                return new BaseResponse<>(CustomErrorType.DVR_StartDVRRecord_ERROR, false);
            } else {
                log.info("远程手动启动设备录像成功,NET_DVR_StartDVRRecord Succeed.");
                return new BaseResponse<>(CustomErrorType.SUCCESS, true);
            }

           /* //获取录像状态
            Integer recordStatus = getRecordStatusV30(new CameraIdDTO(recordControlVO.getCameraId())).getData();
            if (recordStatus == 0) { //当前通道不录像
                boolean bRet = hCNetSDK.NET_DVR_StartDVRRecord(lUserID, new NativeLong((long) iChannel), new NativeLong(0));
                String errorMsg = hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError())));
                if (!bRet) {
                    log.info("远程手动启动设备录像失败,NET_DVR_StartDVRRecord Failed,错误消息:" + errorMsg);
                    return new BaseResponse<>(CustomErrorType.DVR_StartDVRRecord_ERROR, false);
                } else {
                    log.info("远程手动启动设备录像成功,NET_DVR_StartDVRRecord Succeed.");
                    return new BaseResponse<>(CustomErrorType.SUCCESS, true);
                }
            } else if(recordStatus == 1) { //当前通道正在录像
                return new BaseResponse<>(CustomErrorType.BUSSINE_ERROR.getCode(), "当前通道正在录像", false);
            } else {
                return new BaseResponse<>(CustomErrorType.DVR_StartDVRRecord_ERROR, false);
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
            hCNetSDK.NET_DVR_Logout(lUserID);
            hCNetSDK.NET_DVR_Cleanup();
            lUserID = null;
            return new BaseResponse<>(CustomErrorType.DVR_StartDVRRecord_ERROR, false);
        }
    }

    /**
     *
     * @description: 远程手动停止设备录像
     * @param recordControlVO
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-7-2 19:48
     */
    @Override
    public BaseResponse<Boolean> stopRecord(RecordControlVO recordControlVO) {
        try {
            //获取设备登录信息
            if (lUserID == null || lUserID.intValue() == -1) {
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(recordControlVO.getCameraId());
                boolean flag = loginV40(deviceLoginInfo);
                if(!flag){
                    return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR, false);
                }
            }
            //获取通道号
            Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(recordControlVO.getCameraId());
            if (iChannel==-1 || iChannel<m_struIpparaCfg.dwStartDChan || iChannel>(m_struIpparaCfg.dwStartDChan+m_struIpparaCfg.dwDChanNum-1)) {
                return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR, false);
            }
            boolean bRet = hCNetSDK.NET_DVR_StopDVRRecord(lUserID, new NativeLong((long) iChannel));
            String errorMsg = hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError())));
            Thread.sleep(2000);
            hCNetSDK.NET_DVR_Logout(lUserID);
            hCNetSDK.NET_DVR_Cleanup();
            lUserID = null;
            if (!bRet) {
                log.info("远程手动停止设备录像失败,NET_DVR_StopDVRRecord Failed,错误消息：" + errorMsg);
                return new BaseResponse<>(CustomErrorType.DVR_StopDVRRecord_ERROR, false);
            } else {
                log.info("远程手动停止设备录像成功,NET_DVR_StopDVRRecord Succeed.");
                return new BaseResponse<>(CustomErrorType.SUCCESS, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hCNetSDK.NET_DVR_Logout(lUserID);
            hCNetSDK.NET_DVR_Cleanup();
            lUserID = null;
            return new BaseResponse<>(CustomErrorType.DVR_StopDVRRecord_ERROR, false);
        }
    }

    /**
     * 查找录像文件信息
     * @param
     * @param fileParamDTO
     * @return
     */
    @Override
    public List<ManualRecordInfo> getRecordFileNameInfo(FileParamDTO fileParamDTO) {
        try {
            List<ManualRecordInfo> manualRecordInfoList = new ArrayList<>();
            List<HCNetSDK.NET_DVR_FINDDATA_V30> finddataV30s = (List<HCNetSDK.NET_DVR_FINDDATA_V30>) getFileNameInfo(fileParamDTO);
            if (finddataV30s != null && finddataV30s.size() > 0) {
                Iterator<HCNetSDK.NET_DVR_FINDDATA_V30> it = finddataV30s.iterator();
                //将查询结果入库
                while (it.hasNext()) {
                    HCNetSDK.NET_DVR_FINDDATA_V30 finddataV30 = it.next();
                    String fileName = new String(finddataV30.sFileName, "UTF-8").trim();
                    if (!"".equals(fileName) && finddataV30.dwFileSize>0){
                        ManualRecordInfo recordInfo = new ManualRecordInfo();
                        recordInfo.setFileId(UUID.randomUUID().toString());
                        recordInfo.setFileName(fileName);
                        recordInfo.setFileSize(String.valueOf(finddataV30.dwFileSize));
                        recordInfo.setStartTime(parseNETDVRTIME(finddataV30.struStartTime));
                        recordInfo.setStopTime(parseNETDVRTIME(finddataV30.struStopTime));
                        recordInfo.setCameraId(fileParamDTO.getCameraId());
                        recordInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        manualRecordInfoList.add(recordInfo);
                    }
                }
            }
            log.info("录像文件数量:" + manualRecordInfoList.size());
            return manualRecordInfoList;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("查找录像文件信息失败");
            return null;
        }
    }

    public List<?> getFileNameInfo(FileParamDTO fileParamDTO) {
        try {
            List<HCNetSDK.NET_DVR_FINDDATA_V30> list = new ArrayList<>();
            HCNetSDK.NET_DVR_FILECOND pFindCond = new HCNetSDK.NET_DVR_FILECOND();
            if (lUserID == null || lUserID.intValue() == -1) {
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(fileParamDTO.getCameraId());
                boolean flag = loginV40(deviceLoginInfo);
                if(!flag){
                    return null;
                }
            }
            Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(fileParamDTO.getCameraId());
            if (iChannel==-1 || iChannel<m_struIpparaCfg.dwStartDChan || iChannel>(m_struIpparaCfg.dwStartDChan+m_struIpparaCfg.dwDChanNum-1)) {
                return null;
            }
            pFindCond.lChannel = iChannel;
            pFindCond.dwFileType = 6; //6表示手动录像
            pFindCond.dwIsLocked = 0xff;
            pFindCond.dwUseCardNo = 0;
            pFindCond.sCardNumber = new byte[32];
            //时间参数封装
            pFindCond.struStartTime = parseDateTimeToDVRTIME(fileParamDTO.getStartTime());
            pFindCond.struStopTime = parseDateTimeToDVRTIME(fileParamDTO.getStopTime());
            pFindCond.write();
            NativeLong lFindHandle = hCNetSDK.NET_DVR_FindFile_V30(lUserID, pFindCond);
            HCNetSDK.NET_DVR_FINDDATA_V30 lpFindData = new HCNetSDK.NET_DVR_FINDDATA_V30();
            int nativeLong = hCNetSDK.NET_DVR_FindNextFile_V30(lFindHandle, lpFindData).intValue();
            if (nativeLong == -1) {
                String errorMsg = hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError())));
                log.info("逐个获取查找到的文件信息失败,NET_DVR_FindNextFile_V30 Failed,错误消息:" + errorMsg);
                return null;
            }
            while (nativeLong == 1000 || nativeLong == 1002) {
                if (nativeLong == 1000) {
                    list.add(lpFindData);
                    lpFindData = new HCNetSDK.NET_DVR_FINDDATA_V30();
                }
                nativeLong = hCNetSDK.NET_DVR_FindNextFile_V30(lFindHandle, lpFindData).intValue();
            }
            hCNetSDK.NET_DVR_FindClose_V30(lFindHandle);
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            hCNetSDK.NET_DVR_Logout(lUserID);
            hCNetSDK.NET_DVR_Cleanup();
            lUserID = null;
            return null;
        }
    }

    /**
     * 录像文件查找
     * @param
     * @return
     */
    @Override
    public BaseResponse<Object> findEquipmentInfo(FileParamDTO fileParamDTO) {
        try {
            DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
            if (lUserID==null || lUserID.intValue() == -1) {
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(fileParamDTO.getCameraId());
                boolean flag = loginV40(deviceLoginInfo);
                if(!flag){
                    return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR);
                }
            }
            //判断当前设备信息
            HCNetSDK.NET_DVR_WORKSTATE_V30 workstateV30 = new HCNetSDK.NET_DVR_WORKSTATE_V30();
            boolean workStateFlag = hCNetSDK.NET_DVR_GetDVRWorkState_V30(lUserID, workstateV30);
            if (!workStateFlag){
                log.info("获取当前设备状态失败！" + hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError()))));
                return new BaseResponse<>(CustomErrorType.DVR_WorkState_ERROR);
            }
            //当前设备的状态信息
            IntByReference intByReference = new IntByReference(0); //获取IP接入的参数
            HCNetSDK.NET_DVR_IPPARACFG dvrIpparacfg = new HCNetSDK.NET_DVR_IPPARACFG();
            dvrIpparacfg.write();
            Pointer pointer = dvrIpparacfg.getPointer();
            Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(fileParamDTO.getCameraId());
            if (iChannel==-1 || iChannel<m_struIpparaCfg.dwStartDChan || iChannel>(m_struIpparaCfg.dwStartDChan+m_struIpparaCfg.dwDChanNum-1)) {
                return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR);
            }
            boolean stateFlag = hCNetSDK.NET_DVR_GetDVRConfig(lUserID, hCNetSDK.NET_DVR_GET_IPPARACFG,
                    new NativeLong(iChannel), pointer, dvrIpparacfg.size(), intByReference);
            dvrIpparacfg.read();
            if (!stateFlag){
                log.info("获取IP接入配置参数失败！" + hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError()))));
                return new BaseResponse<>(CustomErrorType.DVR_GET_IPPARACFG_ERROR);
            }else {
                //设备状态信息
                List<HCNetSDK.NET_DVR_CHANNELSTATE_V30> dvrChannelstateV30s = Arrays.asList(workstateV30.struChanStatic);//通道的状态
                List<DeviceStateDTO> deviceStateDTOList = new ArrayList<>();
                for (int i=0; i<dvrChannelstateV30s.size(); i++) {
                    DeviceStateDTO deviceStateDTO = new DeviceStateDTO();
                    deviceStateDTO.setDeviceState(String.valueOf(workstateV30.dwDeviceStatic)); //设备状态
                    HCNetSDK.NET_DVR_CHANNELSTATE_V30 channelstateV30 = dvrChannelstateV30s.get(i);
                    deviceStateDTO.setByRecordStatic(String.valueOf(channelstateV30.byRecordStatic));
                    deviceStateDTOList.add(deviceStateDTO);
                }
                deviceStateInfo.setDeviceStateDTOs(deviceStateDTOList);
                return new BaseResponse<>(CustomErrorType.SUCCESS, deviceStateInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hCNetSDK.NET_DVR_Logout(lUserID);
            hCNetSDK.NET_DVR_Cleanup();
            lUserID = null;
            return new BaseResponse<>(CustomErrorType.DVR_WorkState_ERROR);
        }
    }

    /**
     *
     * @description: 保存手动录像标签
     * @param recordLabelDTO
     * @return: boolean
     * @author: hegonglei
     * @date: 2021-7-7 9:10
     */
    @Override
    public BaseResponse<Boolean> updateRecordLabel(RecordLabelDTO recordLabelDTO) {
        try {
            QueryWrapper<ManualRecordInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("FILE_ID",recordLabelDTO.getFileId());
            ManualRecordInfo manualRecordInfo = manualRecordInfoMapper.selectOne(queryWrapper);
            BeanUtils.copyProperties(recordLabelDTO, manualRecordInfo);
            //保存
            boolean bRet = manualRecordInfoMapper.updateById(manualRecordInfo)==1;
            if (bRet) {
                return new BaseResponse<>(CustomErrorType.SUCCESS, true);
            } else {
                return new BaseResponse<>(CustomErrorType.CRUD_UPDATE_ERROR, false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new BaseResponse<>(CustomErrorType.CRUD_UPDATE_ERROR, false);
        }
    }

    //添加手动录像文件信息到数据库
    public void addManualRecordInfo(ManualRecordInfoParamDTO manualRecordInfoParamDTO) {
        try {
            //参数封装
            FileParamDTO fileParamDTO = new FileParamDTO();
            fileParamDTO.setCameraId(manualRecordInfoParamDTO.getCameraId());
            fileParamDTO.setStartTime(manualRecordInfoParamDTO.getStartTime());
            fileParamDTO.setStopTime(manualRecordInfoParamDTO.getStopTime());
            List<HCNetSDK.NET_DVR_FINDDATA_V30> finddataV30s = (List<HCNetSDK.NET_DVR_FINDDATA_V30>) getFileNameInfo(fileParamDTO);
            if (finddataV30s != null && finddataV30s.size() > 0) {
                QueryWrapper<ManualRecordInfo> wrapper = new QueryWrapper<>();
                wrapper.eq("CAMERA_ID", manualRecordInfoParamDTO.getCameraId());
                wrapper.ge("START_TIME", manualRecordInfoParamDTO.getStartTime());
                wrapper.le("STOP_TIME", manualRecordInfoParamDTO.getStopTime());
                List<ManualRecordInfo> manualRecordInfoList = manualRecordInfoMapper.selectList(wrapper);
                Iterator<HCNetSDK.NET_DVR_FINDDATA_V30> it = finddataV30s.iterator();
                for (ManualRecordInfo recordInfo : manualRecordInfoList) {
                    String file_name = recordInfo.getFileName();
                    while (it.hasNext()) {
                        String sFileName = new String(it.next().sFileName, "UTF-8").trim();
                        if (file_name.equals(sFileName)) {
                            it.remove();
                        }
                    }
                }
                //将查询结果入库
                while (it.hasNext()) {
                    HCNetSDK.NET_DVR_FINDDATA_V30 finddataV30 = it.next();
                    String fileName = new String(finddataV30.sFileName, "UTF-8").trim();
                    if (!"".equals(fileName) && finddataV30.dwFileSize>0){
                        ManualRecordInfo recordInfo = new ManualRecordInfo();
                        recordInfo.setFileId(UUID.randomUUID().toString());
                        recordInfo.setCameraId(manualRecordInfoParamDTO.getCameraId());
                        recordInfo.setFileName(fileName);
                        recordInfo.setFileSize(String.valueOf(finddataV30.dwFileSize));
                        recordInfo.setStartTime(parseNETDVRTIME(finddataV30.struStartTime));
                        recordInfo.setStopTime(parseNETDVRTIME(finddataV30.struStopTime));
                        recordInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        manualRecordInfoMapper.insert(recordInfo);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("添加手动录像文件信息失败");
        }
    }

    /**
     *
     * @description: 查找手动录像文件信息
     * @param manualRecordInfoParamDTO
     * @return: java.util.List<com.sdt.fsc.entity.record.ManualRecordInfo>
     * @author: hegonglei
     * @date: 2021-7-7 9:10
     */
    @Override
    public List<ManualRecordInfo> findManualRecordInfo(ManualRecordInfoParamDTO manualRecordInfoParamDTO) {
        QueryWrapper<ManualRecordInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("CAMERA_ID", manualRecordInfoParamDTO.getCameraId());
        wrapper.ge("START_TIME", manualRecordInfoParamDTO.getStartTime());
        wrapper.le("STOP_TIME", manualRecordInfoParamDTO.getStopTime());
        if (manualRecordInfoParamDTO.getRecordLabelName()!=null && !manualRecordInfoParamDTO.getRecordLabelName().equals("")) {
            wrapper.like("RECORD_LABEL_NAME", manualRecordInfoParamDTO.getRecordLabelName());
        }
        if (manualRecordInfoParamDTO.getRecordLabelTime()!=null && manualRecordInfoParamDTO.getRecordLabelTime()!=0) {
            wrapper.eq("RECORD_LABEL_TIME", new Timestamp(manualRecordInfoParamDTO.getRecordLabelTime()));
        }
        if (manualRecordInfoParamDTO.getOperator()!=null && !manualRecordInfoParamDTO.getOperator().equals("")) {
            wrapper.eq("OPERATOR", manualRecordInfoParamDTO.getOperator());
        }
        List<ManualRecordInfo> manualRecordInfoList = manualRecordInfoMapper.selectList(wrapper);
        if (manualRecordInfoList.size() <= 0){
            //将硬盘设备上查询结果入库
            addManualRecordInfo(manualRecordInfoParamDTO);
            List<ManualRecordInfo> manualRecordInfos = manualRecordInfoMapper.selectList(wrapper);
            return manualRecordInfos;
        } else {
            return manualRecordInfoList;
        }
    }

    /**
     *
     * @description: 格式化字符串为NET_DVR_TIME
     * @param date
     * @return: com.sdt.fsc.hik.HCNetSDK.NET_DVR_TIME
     * @author: hegonglei
     * @date: 2021-7-7 9:08
     */
    private HCNetSDK.NET_DVR_TIME parseDateTimeToDVRTIME(String date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HCNetSDK.NET_DVR_TIME struStartTime = new HCNetSDK.NET_DVR_TIME();
        try {
            calendar.setTime(sdf.parse(date));
            //封装时间参数
            struStartTime.dwYear = calendar.get(Calendar.YEAR);
            struStartTime.dwMonth = calendar.get(Calendar.MONTH) + 1;
            struStartTime.dwDay = calendar.get(Calendar.DAY_OF_MONTH);
            struStartTime.dwHour = calendar.get(Calendar.HOUR_OF_DAY);
            struStartTime.dwMinute = calendar.get(Calendar.MINUTE);
            struStartTime.dwSecond = calendar.get(Calendar.SECOND);
            struStartTime.write();
            return struStartTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
//            throw new CustomException(CustomErrorType.ERROR.getCode(),e.getLocalizedMessage()==null?String.valueOf(e):e.getLocalizedMessage());
        }
    }

    /**
     *
     * @description: 格式化NET_DVR_TIME为字符串
     * @param netDvrTime
     * @return: java.lang.String
     * @author: hegonglei
     * @date: 2021-7-7 9:07
     */
    private String parseNETDVRTIME(HCNetSDK.NET_DVR_TIME netDvrTime) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        calendar.set(netDvrTime.dwYear,netDvrTime.dwMonth,netDvrTime.dwDay,netDvrTime.dwHour,netDvrTime.dwMinute,netDvrTime.dwSecond);
        String strDateTime = calendar.get(Calendar.YEAR) +"-"+calendar.get(Calendar.MONTH)+"-"
                +calendar.get(Calendar.DAY_OF_MONTH)+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"
                +calendar.get(Calendar.MINUTE) +":"+calendar.get(Calendar.SECOND);
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-M-d H:m:s");
        LocalDateTime localDateTime = LocalDateTime.parse(strDateTime,sdf);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = df.format(localDateTime);
        return formattedDateTime;
    }

    /**
     *
     * @description: 获取当前通道录像的起止时间
     * @param recordTimeSpanParamDTO
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-7-7 9:09
     */
    @Override
    public JSONObject getRecordTimeSpan(RecordTimeSpanParamDTO recordTimeSpanParamDTO) {
        try {
            JSONObject resultObj = new JSONObject();
            if (lUserID == null || lUserID.intValue() == -1) {
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(recordTimeSpanParamDTO.getCameraId());
                boolean flag = loginV40(deviceLoginInfo);
                if(!flag){
                    return null;
                }
            }
            //获取通道号
            Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(recordTimeSpanParamDTO.getCameraId());
            if (iChannel==-1 || iChannel<m_struIpparaCfg.dwStartDChan || iChannel>(m_struIpparaCfg.dwStartDChan+m_struIpparaCfg.dwDChanNum-1)) {
                return null;
            }
            HCNetSDK.NET_DVR_RECORD_TIME_SPAN_INQUIRY recordTimeSpanInquiry = new HCNetSDK.NET_DVR_RECORD_TIME_SPAN_INQUIRY();
            recordTimeSpanInquiry.dwSize = recordTimeSpanInquiry.size();
            recordTimeSpanInquiry.byType = 0;
            recordTimeSpanInquiry.write();
            HCNetSDK.NET_DVR_RECORD_TIME_SPAN recordTimeSpan = new HCNetSDK.NET_DVR_RECORD_TIME_SPAN();
            boolean bRet = hCNetSDK.NET_DVR_InquiryRecordTimeSpan(lUserID, iChannel ,recordTimeSpanInquiry, recordTimeSpan);
            recordTimeSpan.read();
            if (!bRet) {
                log.info("获取通道录像起止时间失败,NET_DVR_InquiryRecordTimeSpan Failed,错误码：" + hCNetSDK.NET_DVR_GetLastError());
                return null;
            }else {
                resultObj.put("strBeginTime", parseNETDVRTIME(recordTimeSpan.strBeginTime));
                resultObj.put("strEndTime", parseNETDVRTIME(recordTimeSpan.strEndTime));
                return resultObj;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hCNetSDK.NET_DVR_Logout(lUserID);
            hCNetSDK.NET_DVR_Cleanup();
            lUserID = null;
            return null;
        }

    }

    /**
     *
     * @description: 停止录像并保存录像标签
     * @param stopRecordParamDTO
     * @return: boolean
     * @author: hegonglei
     * @date: 2021-7-6 18:22
     */
    @Override
    public BaseResponse<Boolean> StopRecordWithRecordLabel(StopRecordParamDTO stopRecordParamDTO) {
        try {
            if (lUserID == null || lUserID.intValue() == -1) {
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(stopRecordParamDTO.getCameraId());
                boolean flag = loginV40(deviceLoginInfo);
                if(!flag){
                    return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR, false);
                }
            }
            //获取通道号
            Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(stopRecordParamDTO.getCameraId());
            if (iChannel==-1 || iChannel<m_struIpparaCfg.dwStartDChan || iChannel>(m_struIpparaCfg.dwStartDChan+m_struIpparaCfg.dwDChanNum-1)) {
                return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR, false);
            }
            boolean bRet = hCNetSDK.NET_DVR_StopDVRRecord(lUserID, new NativeLong((long) iChannel));
            String errorMsg = hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError())));
            if (!bRet) {
                log.info("远程手动停止设备录像失败,NET_DVR_StopDVRRecord Failed,错误消息：" + errorMsg);
                return new BaseResponse<>(CustomErrorType.DVR_StopDVRRecord_ERROR, false);
            }
            RecordTimeSpanParamDTO recordTimeSpanParamDTO = new RecordTimeSpanParamDTO();
            recordTimeSpanParamDTO.setCameraId(stopRecordParamDTO.getCameraId());
            JSONObject recordTimeSpan = getRecordTimeSpan(recordTimeSpanParamDTO);
            if (recordTimeSpan == null) {
                log.info(CustomErrorType.DVR_InquiryRecordTimeSpan_ERROR.getMessage());
                return new BaseResponse<>(CustomErrorType.DVR_InquiryRecordTimeSpan_ERROR, false);
            }
            String startTime = recordTimeSpan.getString("strEndTime");
            String stopTime = LocalDate.now().toString() + " " +"23:59:59";
            FileParamDTO fileParamDTO = new FileParamDTO(stopRecordParamDTO.getCameraId(), startTime, stopTime);
            List<HCNetSDK.NET_DVR_FINDDATA_V30> finddataV30s = (List<HCNetSDK.NET_DVR_FINDDATA_V30>) getFileNameInfo(fileParamDTO);
            if (finddataV30s!= null && finddataV30s.size()>0) {
                List<ManualRecordInfo> manualRecordInfos = new ArrayList<>();
                for (HCNetSDK.NET_DVR_FINDDATA_V30 finddataV30 : finddataV30s) {
                    ManualRecordInfo manualRecordInfo = new ManualRecordInfo();
                    String fileName = new String(finddataV30.sFileName, "UTF-8").trim();
                    if (!"".equals(fileName) && finddataV30.dwFileSize>0){
                        manualRecordInfo.setFileId(UUID.randomUUID().toString());
                        manualRecordInfo.setCameraId(stopRecordParamDTO.getCameraId());
                        manualRecordInfo.setFileName(fileName);
                        manualRecordInfo.setFileSize(String.valueOf(finddataV30.dwFileSize));
                        manualRecordInfo.setStartTime(parseNETDVRTIME(finddataV30.struStartTime));
                        manualRecordInfo.setStopTime(parseNETDVRTIME(finddataV30.struStopTime));
                        manualRecordInfo.setRecordLabelName(stopRecordParamDTO.getRecordLabeName());
                        manualRecordInfo.setRecordLabelTime(new Timestamp(stopRecordParamDTO.getRecordLabeTime()));
                        manualRecordInfo.setOperator(stopRecordParamDTO.getUsername());
                        manualRecordInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        manualRecordInfos.add(manualRecordInfo);
                        manualRecordInfoMapper.insert(manualRecordInfo);
                    }
                }

                Thread.sleep(2000);
                hCNetSDK.NET_DVR_Logout(lUserID);
                hCNetSDK.NET_DVR_Cleanup();
                lUserID = null;
                return new BaseResponse<>(CustomErrorType.SUCCESS, true);
            } else {
                return new BaseResponse<>(CustomErrorType.DVR_StopDVRRecord_ERROR, false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            hCNetSDK.NET_DVR_Logout(lUserID);
            hCNetSDK.NET_DVR_Cleanup();
            lUserID = null;
            return new BaseResponse<>(CustomErrorType.DVR_StopDVRRecord_ERROR, false);
        }
    }

}
