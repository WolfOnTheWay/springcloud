package com.sdt.fsc.service.impl.record;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.camera.CameraIdDTO;
import com.sdt.fsc.entity.camera.DeviceLoginInfo;
import com.sdt.fsc.entity.record.*;
import com.sdt.fsc.exception.CustomException;
import com.sdt.fsc.mapper.dm.ManualRecordInfoMapper;
import com.sdt.fsc.mapper.dm.VideoInfoMapper;
import com.sdt.fsc.service.contract.camera.ICameraService;
import com.sdt.fsc.hik.HCNetSDK;
import com.sdt.fsc.service.contract.record.IRecordService;
import com.sdt.fsc.util.Xml2JsonUtil;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;


@Slf4j
@Service
public class RecordServiceImpl implements IRecordService {

//    public static ReadWriteMapCache cache = new ReadWriteMapCache();
//    public HCNetSDKSingleton hCNetSDKSingleton = HCNetSDKSingleton.getInstance();

    @Autowired
    private ICameraService cameraServiceImpl;
    @Resource
    private VideoInfoMapper videoInfoMapper;
    @Resource
    private ManualRecordInfoMapper manualRecordInfoMapper;

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    NativeLong lUserID;//????????????
    HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo;//????????????
    HCNetSDK.NET_DVR_IPPARACFG_V40 m_strIpparaCfg;//IP??????????????????
    private boolean[] bChannelEnabled;//????????????????????????????????????????????????
    Integer[] byRecordStatics;

    /*class dev_work_state_cb implements HCNetSDK.DEV_WORK_STATE_CB
    {
        public boolean invoke(Pointer pUserdata, int iUserID, HCNetSDK.NET_DVR_WORKSTATE_V40 lpWorkState)
        {
            lpWorkState.read();
            System.out.println("????????????:" + lpWorkState.dwDeviceStatic);
            for (int i = 0; i < HCNetSDK.MAX_CHANNUM_V40; i++) {
                int channel = i + 1;
                System.out.println("???" + channel + "????????????????????????" + lpWorkState.struChanStatic[i].byRecordStatic);
                byRecordStatics[channel] = lpWorkState.struChanStatic[i].byRecordStatic;
            }
            return true;
        }
    }*/


    //????????????login_V30
    public Boolean loginV30(DeviceLoginInfo deviceLoginInfo){
        if (deviceLoginInfo != null){
            hCNetSDK.NET_DVR_Init();
            //??????????????????
            hCNetSDK.NET_DVR_SetConnectTime(2000,1);
            hCNetSDK.NET_DVR_SetReconnect(100000,true);
            //???????????????????????????
            HCNetSDK.NET_DVR_DEVICEINFO_V30 net_dvr_deviceinfo_v30 = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
            lUserID = hCNetSDK.NET_DVR_Login_V30(deviceLoginInfo.getIp(), Short.valueOf(deviceLoginInfo.getPort().toString()), deviceLoginInfo.getUsername(), deviceLoginInfo.getPassword(), net_dvr_deviceinfo_v30);
            if (lUserID.intValue() == -1) {
                System.out.println("????????????,NET_DVR_Login_V30 Failed,Error Code=" + hCNetSDK.NET_DVR_GetLastError());
                log.info("????????????,NET_DVR_Login_V30 Failed,Error Code=" + hCNetSDK.NET_DVR_GetLastError());
                return false;
            } else {
                System.out.println("????????????,NET_DVR_Login_V30 Succeed???");
                log.info("????????????,NET_DVR_Login_V30 Succeed!");
                return true;
            }
        } else {
            return false;
        }
    }

    //????????????login_V40
    public Boolean loginV40(DeviceLoginInfo deviceLoginInfo) throws Exception {
        if (deviceLoginInfo != null){
            hCNetSDK.NET_DVR_Init();
            //??????????????????
            hCNetSDK.NET_DVR_SetConnectTime(2000,1);
            hCNetSDK.NET_DVR_SetReconnect(100000,true);
            //???????????????????????????
            HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();//??????????????????
            HCNetSDK.NET_DVR_DEVICEINFO_V40 m_deviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();//????????????
            String m_sDeviceIP = deviceLoginInfo.getIp();//??????ip??????
            m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
            System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());
            String m_sUsername = deviceLoginInfo.getUsername();//???????????????
            m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
            System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());
            String m_sPassword = deviceLoginInfo.getPassword();//????????????
            m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
            System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());
            m_strLoginInfo.wPort = Short.valueOf(deviceLoginInfo.getPort().toString());
            m_strLoginInfo.bUseAsynLogin = false; //?????????????????????0- ??????1- ???
            m_strLoginInfo.write();
            lUserID = hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_deviceInfo);
            m_deviceInfo.read();
            m_strDeviceInfo = m_deviceInfo;
            if (lUserID.intValue() == -1) {
                System.out.println("????????????,NET_DVR_Login_V40 Failed,Error Code=" + hCNetSDK.NET_DVR_GetLastError());
                log.info("????????????,NET_DVR_Login_V40 Failed,Error Code=" + hCNetSDK.NET_DVR_GetLastError());
                return false;
            } else {
                System.out.println("????????????,NET_DVR_Login_V40 Succeed???");
                log.info("????????????,NET_DVR_Login_V40 Succeed!");
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     *
     * @description: ?????????
     * @param deviceLoginInfo
     * @return: boolean
     * @author: hegonglei
     * @date: 2021-7-2 19:46
     */
    private Boolean initialize(DeviceLoginInfo deviceLoginInfo) throws Exception {
        Boolean bLoginRet = this.loginV40(deviceLoginInfo);
        if (!bLoginRet) {
            return false;
        } else {
            IntByReference ibrBytesReturned = new IntByReference(0);//??????IP??????????????????
            m_strIpparaCfg = new HCNetSDK.NET_DVR_IPPARACFG_V40();
            m_strIpparaCfg.write();
            //lpIpParaConfig ???????????????????????????
            Pointer lpIpParaConfig = m_strIpparaCfg.getPointer();
            boolean getDVRConfigResult = hCNetSDK.NET_DVR_GetDVRConfig(lUserID, HCNetSDK.NET_DVR_GET_IPPARACFG_V40, new NativeLong(0), lpIpParaConfig, m_strIpparaCfg.size(), ibrBytesReturned);
            m_strIpparaCfg.read();
            bChannelEnabled = new boolean[HCNetSDK.MAX_ANALOG_CHANNUM];
            if (!getDVRConfigResult) {//???????????????,???????????????IP??????
                for (int iChannum = 0; iChannum < m_strIpparaCfg.dwAChanNum; iChannum++) {
                    bChannelEnabled[iChannum] = true;
                }
            } else {//????????????IP??????
                for (int iChannum = 0; iChannum < m_strIpparaCfg.dwAChanNum; iChannum++) {
                    bChannelEnabled[iChannum] = (m_strIpparaCfg.byAnalogChanEnable[iChannum] == 1) ? true : false;
                }
            }
            return true;
        }
    }

    //??????????????????????????????
    private boolean startDVRRecord(int iChannel) {
        boolean bRet = hCNetSDK.NET_DVR_StartDVRRecord(lUserID, new NativeLong(Long.valueOf(iChannel)),new NativeLong(0));
        if (!bRet) {
            log.info("????????????????????????????????????,NET_DVR_StartDVRRecord Failed,????????????" + hCNetSDK.NET_DVR_GetLastError());
        } else {
            log.info("????????????????????????????????????,NET_DVR_StartDVRRecord Succeed.");
        }
        return bRet;
    }

    //??????????????????????????????
    private boolean stopDVRRecord(int iChannel) throws Exception {
        boolean bRet = hCNetSDK.NET_DVR_StopDVRRecord(lUserID, new NativeLong(Long.valueOf(iChannel)));
        if (!bRet) {
            log.info("????????????????????????????????????,NET_DVR_StopDVRRecord Failed,????????????" + hCNetSDK.NET_DVR_GetLastError());
        } else {
            log.info("????????????????????????????????????,NET_DVR_StopDVRRecord Succeed.");
        }
        return bRet;
    }

    //??????????????????????????????????????????CVR
    private boolean startManualRecord(int iChannel) {
        HCNetSDK.NET_DVR_MANUAL_RECORD_PARA manualRecordPara = new HCNetSDK.NET_DVR_MANUAL_RECORD_PARA();
        HCNetSDK.NET_DVR_STREAM_INFO streamInfo = new HCNetSDK.NET_DVR_STREAM_INFO();
        manualRecordPara.lRecordType = new NativeLong(0);
        streamInfo.dwSize = streamInfo.size();
        streamInfo.dwChannel = iChannel;
        streamInfo.write();
        manualRecordPara.struStreamInfo = streamInfo;
        manualRecordPara.write();
        boolean bRet = hCNetSDK.NET_DVR_StartManualRecord(lUserID, manualRecordPara);
        if (!bRet) {
            log.info("????????????????????????????????????,NET_DVR_StartManualRecord Failed,????????????" + hCNetSDK.NET_DVR_GetLastError());
        } else {
            log.info("????????????????????????????????????,NET_DVR_StartManualRecord Succeed.");
        }
        return bRet;
    }

    //??????????????????????????????????????????CVR
    private boolean stopManualRecord(int iChannel) throws Exception {
        HCNetSDK.NET_DVR_STREAM_INFO streamInfo = new HCNetSDK.NET_DVR_STREAM_INFO();
        streamInfo.dwSize = streamInfo.size();
        streamInfo.dwChannel = iChannel;
        streamInfo.write();
        boolean bRet = hCNetSDK.NET_DVR_StopManualRecord(lUserID, streamInfo);
        if (!bRet) {
            log.info("????????????????????????????????????,NET_DVR_StopManualRecord Failed,????????????" + hCNetSDK.NET_DVR_GetLastError());
        } else {
            log.info("????????????????????????????????????,NET_DVR_StopManualRecord Succeed.");
        }
        /*Thread.sleep(2000);
        hCNetSDK.NET_DVR_Logout(lUserID);
        hCNetSDK.NET_DVR_Cleanup();
        lUserID = null;*/
        return bRet;
    }

    //??????????????????
    @Override
    public BaseResponse<Integer> getRecordStatusV30(CameraIdDTO cameraIdDTO) throws Exception {
        //????????????????????????
        if (lUserID == null || lUserID.intValue() == -1) {
            DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(cameraIdDTO.getCameraId());
            if(!initialize(deviceLoginInfo)){
                return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR, -1);
            }
        }
        //??????????????????????????????????????????????????????????????????
        Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(cameraIdDTO.getCameraId());
        if (iChannel==-1 || iChannel>=HCNetSDK.MAX_CHANNUM_V40) {
            return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR, -1);
        }
        HCNetSDK.NET_DVR_WORKSTATE_V30 net_dvr_workstate_v30 = new HCNetSDK.NET_DVR_WORKSTATE_V30();
        boolean state = hCNetSDK.NET_DVR_GetDVRWorkState_V30(lUserID, net_dvr_workstate_v30);
        net_dvr_workstate_v30.read();
        if (!state){
            log.info("??????????????????????????????,NET_DVR_GetDVRWorkState_V30 Failed,?????????:" + hCNetSDK.NET_DVR_GetLastError());
            return new BaseResponse<>(CustomErrorType.DVR_WorkState_ERROR, -1);
        } else {
            log.info("??????????????????????????????,NET_DVR_GetDVRWorkState_V30 Succeed.");
            if (iChannel <= m_strIpparaCfg.dwAChanNum) {
                //????????????????????????
                if (bChannelEnabled[iChannel-1] == true) { //????????????
                    //????????????
                    Integer iByRecordStatic = Integer.valueOf(net_dvr_workstate_v30.struChanStatic[iChannel-1].byRecordStatic);
                    return new BaseResponse<>(CustomErrorType.SUCCESS, iByRecordStatic);
                }
                return new BaseResponse<>(CustomErrorType.BUSSINE_ERROR.getCode(), "????????????????????????????????????", -1);
            } else {
                //??????IP????????????
                //????????????IP??????,??????IP???????????????????????????byEnable?????????,??????????????????,
                //??????????????????????????????????????????????????????,???????????????:0-31,IP?????????.32-64
                if (m_strIpparaCfg.struIPDevInfo[iChannel-33].byEnable == 1) {//????????????IP??????????????????
                    Integer iByRecordStatic = Integer.valueOf(net_dvr_workstate_v30.struChanStatic[iChannel-1].byRecordStatic);
                    return new BaseResponse<>(CustomErrorType.SUCCESS, iByRecordStatic);
                }
                return new BaseResponse<>(CustomErrorType.BUSSINE_ERROR.getCode(), "????????????????????????????????????", -1);
            }
        }
    }

    //??????????????????
    private Integer getRecordStatusV40(int iChannel) {
        HCNetSDK.NET_DVR_GETWORKSTATE_COND getWorktateCond = new HCNetSDK.NET_DVR_GETWORKSTATE_COND();
        getWorktateCond.read();
        getWorktateCond.dwSize = getWorktateCond.size();
        getWorktateCond.byFindChanByCond = 0;
        getWorktateCond.byFindHardByCond = 0;
        getWorktateCond.write();
        Pointer lpGetWorkstateCond = getWorktateCond.getPointer();
        HCNetSDK.NET_DVR_WORKSTATE_V40 workstateV40 = new HCNetSDK.NET_DVR_WORKSTATE_V40();
        workstateV40.read();
        workstateV40.dwSize = workstateV40.size();
        workstateV40.write();
        IntByReference lpBytesReturned = new IntByReference(0);
        Pointer lpWorkstateV40 = workstateV40.getPointer();
        boolean bRet = hCNetSDK.NET_DVR_GetDeviceConfig(lUserID, HCNetSDK.NET_DVR_GET_WORK_STATUS,1,lpGetWorkstateCond,getWorktateCond.size(),lpBytesReturned.getPointer(),lpWorkstateV40,workstateV40.size());
        if (!bRet){
            log.info("??????????????????????????????,NET_DVR_GET_WORK_STATUS Failed,????????????" + hCNetSDK.NET_DVR_GetLastError());
            return -1;
        } else {
            log.info("??????????????????????????????,NET_DVR_GET_WORK_STATUS Succeed.");
            workstateV40.read();
            if (iChannel <= m_strIpparaCfg.dwAChanNum) {
                //????????????????????????
                if (bChannelEnabled[iChannel-1] == true) { //????????????
                    //????????????
                    return Integer.valueOf(workstateV40.struChanStatic[iChannel-1].byRecordStatic);
                }
            } else {
                //??????IP????????????
                if (m_strIpparaCfg.struIPDevInfo[iChannel-33].byEnable == 1) {//????????????IP??????????????????
                    return Integer.valueOf(workstateV40.struChanStatic[iChannel-1].byRecordStatic);
                }
            }
            return -1;
        }
    }

    /**
     *
     * @description: ??????????????????????????????
     * @param recordControlVO
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-7-2 19:47
     */
    @Override
    public BaseResponse<Boolean> startRecord(RecordControlVO recordControlVO) throws Exception {
        if (lUserID == null || lUserID.intValue() == -1) {
            DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(recordControlVO.getCameraId());
            if(!initialize(deviceLoginInfo)){
                return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR, false);
            }
        }
        //???????????????
        Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(recordControlVO.getCameraId());
        if (iChannel==-1 || iChannel>=HCNetSDK.MAX_CHANNUM_V40) {
            return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR, false);
        }
        //??????????????????
        Integer recordStatus = getRecordStatusV30(new CameraIdDTO(recordControlVO.getCameraId())).getData();
        if (recordStatus == 0) { //?????????????????????
            boolean bRet = startDVRRecord(iChannel);
            if (!bRet) {
                return new BaseResponse<>(CustomErrorType.DVR_StartDVRRecord_ERROR, false);
            }
            return new BaseResponse<>(CustomErrorType.SUCCESS, true);
        } else if(recordStatus == 1) { //????????????????????????
            return new BaseResponse<>(CustomErrorType.BUSSINE_ERROR.getCode(), "????????????????????????", false);
        } else {
            return new BaseResponse<>(CustomErrorType.DVR_StartDVRRecord_ERROR, false);
        }
    }

    /**
     *
     * @description: ??????????????????????????????
     * @param recordControlVO
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-7-2 19:48
     */
    @Override
    public BaseResponse<Boolean> stopRecord(RecordControlVO recordControlVO) throws Exception {
        //????????????????????????
        if (lUserID==null || lUserID.intValue() == -1) {
            DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(recordControlVO.getCameraId());
            if(!initialize(deviceLoginInfo)){
                return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR, false);
            }
        }
        //???????????????
        int iChannel = cameraServiceImpl.getChannelNoByCameraId(recordControlVO.getCameraId());
        if (iChannel==-1 || iChannel>=HCNetSDK.MAX_CHANNUM_V40) {
            return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR, false);
        }
        boolean bRet = stopDVRRecord(iChannel);
        if (!bRet) {
            return new BaseResponse<>(CustomErrorType.DVR_StopDVRRecord_ERROR, false);
        }
        return new BaseResponse<>(CustomErrorType.SUCCESS, true);
    }

    /**
     * ????????????????????????
     * @param
     * @param fileParamDTO
     * @return
     */
    @Override
    public List<HCNetSDK.NET_DVR_FINDDATA_V30> getFileNameInfo(FileParamDTO fileParamDTO) throws Exception {
        List<HCNetSDK.NET_DVR_FINDDATA_V30> list = new ArrayList<>();
        HCNetSDK.NET_DVR_FILECOND pFindCond = new HCNetSDK.NET_DVR_FILECOND();
        if (lUserID == null || lUserID.intValue() == -1) {
            DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(fileParamDTO.getCameraId());
            if (!initialize(deviceLoginInfo)) {
                return list;
            }
        }
        Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(fileParamDTO.getCameraId());
        if (iChannel==-1 || iChannel>=HCNetSDK.MAX_CHANNUM_V40) {
            return list;
        }
        pFindCond.lChannel = iChannel;
        pFindCond.dwFileType = 6; //6??????????????????
        pFindCond.dwIsLocked = 0xff;
        pFindCond.dwUseCardNo = 0;
        pFindCond.sCardNumber = new byte[32];
        //??????????????????
        pFindCond.struStartTime = parseDateTimeToDVRTIME(fileParamDTO.getStartTime());
        pFindCond.struStopTime = parseDateTimeToDVRTIME(fileParamDTO.getStopTime());
        pFindCond.write();
        NativeLong lFindHandle = hCNetSDK.NET_DVR_FindFile_V30(lUserID, pFindCond);
        HCNetSDK.NET_DVR_FINDDATA_V30 lpFindData = new HCNetSDK.NET_DVR_FINDDATA_V30();
        int nativeLong = hCNetSDK.NET_DVR_FindNextFile_V30(lFindHandle, lpFindData).intValue();
        list.add(lpFindData);
        while (nativeLong != 1003 && nativeLong != 1001 && nativeLong != 1004) {
            switch (nativeLong) {
                case 1000: //??????????????????
                    lpFindData = new HCNetSDK.NET_DVR_FINDDATA_V30();
                    list.add(lpFindData);
                    nativeLong =  hCNetSDK.NET_DVR_FindNextFile_V30(lFindHandle, lpFindData).intValue();
                    break;
                case 1001:
                    log.info("file???????????????" + hCNetSDK.NET_DVR_FILE_NOFIND);
                    break;
                case 1002:
                    log.info("file???????????????" + hCNetSDK.NET_DVR_ISFINDING);
                    nativeLong =  hCNetSDK.NET_DVR_FindNextFile_V30(lFindHandle, lpFindData).intValue();
                    break;
                case 1003:
                    log.info("file???????????????" + hCNetSDK.NET_DVR_NOMOREFILE);
                    break;
                case 1004:
                    log.info("file???????????????" + hCNetSDK.NET_DVR_FILE_EXCEPTION);
                    break;
                default:
                    break;
            }
        }
        hCNetSDK.NET_DVR_FindClose_V30(lFindHandle);
        return list;
    }

    /**
     * ??????????????????
     * @param
     * @return
     */
    @Override
    public BaseResponse<Object> findEquipmentInfo(FileParamDTO fileParamDTO) throws Exception {
        DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
        if (lUserID==null || lUserID.intValue() == -1) {
            DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(fileParamDTO.getCameraId());
            if (!initialize(deviceLoginInfo)) {
                return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR);
            }
        }
        //????????????????????????
        HCNetSDK.NET_DVR_WORKSTATE_V30 workstateV30 = new HCNetSDK.NET_DVR_WORKSTATE_V30();
        boolean workStateFlag = hCNetSDK.NET_DVR_GetDVRWorkState_V30(lUserID, workstateV30);
        if (!workStateFlag){
            log.info("?????????????????????????????????" + hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError()))));
            return new BaseResponse<>(CustomErrorType.DVR_WorkState_ERROR);
        }
        //???????????????????????????
        IntByReference intByReference = new IntByReference(0); //??????IP???????????????
        HCNetSDK.NET_DVR_IPPARACFG dvrIpparacfg = new HCNetSDK.NET_DVR_IPPARACFG();
        dvrIpparacfg.write();
        Pointer pointer = dvrIpparacfg.getPointer();
        Integer channel = cameraServiceImpl.getChannelNoByCameraId(fileParamDTO.getCameraId());
        if (channel==-1 || channel>=HCNetSDK.MAX_CHANNUM_V40) {
            log.info(CustomErrorType.VIDEO_CHANNEL_ERROR.getMessage());
            return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR);
        }
        boolean stateFlag = hCNetSDK.NET_DVR_GetDVRConfig(lUserID, hCNetSDK.NET_DVR_GET_IPPARACFG,
                new NativeLong(channel), pointer, dvrIpparacfg.size(), intByReference);
        dvrIpparacfg.read();
        if (!stateFlag){
            log.info("??????IP???????????????????????????" + hCNetSDK.NET_DVR_GetErrorMsg(new NativeLongByReference(new NativeLong(hCNetSDK.NET_DVR_GetLastError()))));
            return new BaseResponse<>(CustomErrorType.DVR_GET_IPPARACFG_ERROR);
        }else {
            //??????????????????
            List<HCNetSDK.NET_DVR_CHANNELSTATE_V30> dvrChannelstateV30s = Arrays.asList(workstateV30.struChanStatic);//???????????????
            List<DeviceStateDTO> deviceStateDTOList = new ArrayList<>();
            for (int i=0; i<dvrChannelstateV30s.size(); i++) {
                DeviceStateDTO deviceStateDTO = new DeviceStateDTO();
                deviceStateDTO.setDeviceState(String.valueOf(workstateV30.dwDeviceStatic)); //????????????
                HCNetSDK.NET_DVR_CHANNELSTATE_V30 channelstateV30 = dvrChannelstateV30s.get(i);
                deviceStateDTO.setByRecordStatic(String.valueOf(channelstateV30.byRecordStatic));
                deviceStateDTOList.add(deviceStateDTO);
            }
            deviceStateInfo.setDeviceStateDTOs(deviceStateDTOList);
            return new BaseResponse<>(CustomErrorType.SUCCESS, deviceStateInfo);
        }
    }

    @Override
    public BaseResponse<Object> downloadVideoByFileName(DownloadVideoParamDTO downloadVideoParamDTO) throws Exception {
        Map<String,Object> map = new HashMap<>();
        //1.????????????
        if (lUserID==null || lUserID.intValue()==-1){
            DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(downloadVideoParamDTO.getCameraId());
            if (!loginV30(deviceLoginInfo)) {
                log.info("?????????????????????"+ hCNetSDK.NET_DVR_GetLastError());
                return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR);
            }
        }
        //2.???????????????????????????????????????????????????
        FileParamDTO fileParamDTO = new FileParamDTO();
        fileParamDTO.setCameraId(downloadVideoParamDTO.getCameraId());
        fileParamDTO.setStartTime(downloadVideoParamDTO.getStartTime());
        fileParamDTO.setStopTime(downloadVideoParamDTO.getStopTime());
        List<HCNetSDK.NET_DVR_FINDDATA_V30> fileNameInfoList = getFileNameInfo(fileParamDTO);
        for (int i = 0; i <fileNameInfoList.size(); i++) {
            HCNetSDK.NET_DVR_FINDDATA_V30 finddataV30 = fileNameInfoList.get(i);
            String fileName = null;
            fileName = new String(finddataV30.sFileName, "UTF-8").trim();
            if (fileName.equals(downloadVideoParamDTO.getFileName())) {
                //2.1??????name?????????????????????
                NativeLong lPlayHandle = hCNetSDK.NET_DVR_PlayBackByName(lUserID, fileName, null);
                if (lPlayHandle.intValue() == -1) {
                    return new BaseResponse<>(CustomErrorType.DVR_PlayBackByName_ERROR);
                }
                //????????????
                boolean backControl = hCNetSDK.NET_DVR_PlayBackControl(lPlayHandle, HCNetSDK.NET_DVR_PLAYSTART,0,null);
                if (backControl) {
                        /*synchronized (this) {
                            hCNetSDK.NET_DVR_SetPlayDataCallBack(lPlayHandle, new HCNetSDK.FPlayDataCallBack() {
                                @Override
                                public void invoke(NativeLong lPlayHandle, int dwDataType, ByteByReference pBuffer, int dwBufSize, int dwUser) {
                                    //webSocket ??????
                                    if (dwBufSize > 0){ //???????????????
                                        *//*map.put("msg",true);
                                        map.put("data",dwBufSize);*//*
                                        map.put("lPlayHandle",lPlayHandle.intValue()); //???????????????????????????????????????; //???????????????????????????????????????
                                    }
                                }
                            }, 0);
                        }*/
                    return new BaseResponse<>(CustomErrorType.SUCCESS, lPlayHandle.intValue());
                }else {
                    return new BaseResponse<>(CustomErrorType.DVR_PLAYSTART_ERROR);
                }
            }
        }
        return new BaseResponse<>(CustomErrorType.SUCCESS);
    }

    @Override
    public Map<String, Object> getDownloadProgress(Integer lPlayHandle) throws Exception {
        Map<String,Object> map = new HashMap<>();
        //????????????
        NativeLong playHandle = new NativeLong(lPlayHandle);
        IntByReference lpOutValue = new IntByReference();
        boolean playBackControl = hCNetSDK.NET_DVR_PlayBackControl(playHandle, HCNetSDK.NET_DVR_PLAYGETPOS, 0, lpOutValue);
        if (playBackControl){
            //???????????????????????????
            if (lpOutValue.getValue() == 100) {
                log.info("??????????????????");
                synchronized (this) {
                    hCNetSDK.NET_DVR_SetPlayDataCallBack(playHandle, new HCNetSDK.FPlayDataCallBack() {
                        @Override
                        public void invoke(NativeLong lPlayHandle, int dwDataType, ByteByReference pBuffer, int dwBufSize, int dwUser) {
                            //webSocket ??????
                            log.info("????????????????????????"+ dwBufSize);
                            if (dwBufSize > 0){ //???????????????
                                map.put("msg","???????????????????????????");
                                map.put("data",dwBufSize);
                            }
                        }
                    }, 0);
                }
            } else if (lpOutValue.getValue() < 100){
                map.put("data",lpOutValue.getValue() +"%");
                map.put("msg","??????????????????....");
            }else if (lpOutValue.getValue() > 100){
                map.put("data",lpOutValue.getValue() +"%");
                map.put("msg","????????????????????????....");
            }
        }
        return map;
    }

    /**
     *
     * @description: ??????????????????????????????
     * @param videoInfoParamDTO
     * @return: java.util.List<com.sdt.fsc.entity.record.VideoInfo>
     * @author: hegonglei
     * @date: 2021-7-2 20:34
     */
    @Override
    public List<VideoInfo> findVideoInfo(VideoInfoParamDTO videoInfoParamDTO) throws Exception {
        //???????????????????????????
        //????????????
        QueryWrapper<VideoInfo> wrapper = new QueryWrapper<>();
        wrapper.ge("START_TIME", videoInfoParamDTO.getStartTime());
        wrapper.le("STOP_TIME", videoInfoParamDTO.getStopTime());
        List<VideoInfo> videoInfoList = videoInfoMapper.selectList(wrapper);
        //???????????????
        if (videoInfoList.size() <= 0){
            //??????????????????????????????????????????
            //???????????????
            ThreadPoolExecutor poolExecutor =
                    new ThreadPoolExecutor(1, 10, 20L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
            //??????NVR???????????????
            //?????????????????????????????????????????????
            long start = System.currentTimeMillis();
            List<VideoInfo> list = CompletableFuture.supplyAsync(() -> {
                /*//????????????????????????
                NativeLong lUserID = (NativeLong) cache.get(ip);*/
                //????????????
                FileParamDTO fileParamDTO = new FileParamDTO();
                fileParamDTO.setCameraId(videoInfoParamDTO.getCameraId());
                fileParamDTO.setStartTime(videoInfoParamDTO.getStartTime());
                fileParamDTO.setStopTime(videoInfoParamDTO.getStopTime());
                //???NVR???????????????????????????
                List<HCNetSDK.NET_DVR_FINDDATA_V30> netDvrFinddataV30List = null;
                try {
                    netDvrFinddataV30List = getFileNameInfo(fileParamDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return netDvrFinddataV30List;
            },poolExecutor).thenApply(netDvrFinddataV30List -> {
                //?????????????????????
                List<VideoInfo> videoInfos = new ArrayList<>();
                try {
                    if (netDvrFinddataV30List != null && netDvrFinddataV30List.size() > 0) {
                        for (int i = 0; i < netDvrFinddataV30List.size(); i++) {
                            HCNetSDK.NET_DVR_FINDDATA_V30 finddataV30 = netDvrFinddataV30List.get(i);
                            VideoInfo videoInfo = new VideoInfo();
                            String fileName = new String(finddataV30.sFileName, "UTF-8").trim();
                            if (!"".equals(fileName) && finddataV30.dwFileSize > 0){
                                videoInfo.setVideoId(UUID.randomUUID().toString());
                                videoInfo.setFileName(fileName);
                                videoInfo.setFileSize(String.valueOf(finddataV30.dwFileSize));
                                videoInfo.setStarttime(parseNETDVRTIME(finddataV30.struStartTime));
                                videoInfo.setStoptime(parseNETDVRTIME(finddataV30.struStopTime));
                                videoInfos.add(videoInfo);
                                videoInfoMapper.insert(videoInfo);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return videoInfos;
               /* //????????????
                return this.baseMapper.saveBatch(videoInfos);*/
            }).thenApply(result -> {
                //??????????????????????????????????????????
                QueryWrapper<VideoInfo> queryWrapper = new QueryWrapper<>();
                queryWrapper.ge("START_TIME", videoInfoParamDTO.getStartTime());
                queryWrapper.le("STOP_TIME", videoInfoParamDTO.getStopTime());
                return videoInfoMapper.selectList(queryWrapper);
            }).whenComplete((v, e) -> {
                if (e == null) {
                    log.info("----------- result:" + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                return null;
            }).join();
            long end = System.currentTimeMillis();
            log.info("??????????????????????????????????????????"+(end - start) + "??????");
            //???????????????
            poolExecutor.shutdown();
            return list;
        }else { //??????????????????????????????????????????????????????
            return videoInfoList;
        }
    }

    /**
     * ??????????????????
     * @param videoTagDTO
     */
    @Override
    @Transactional
    public void saveVideoTagInfo(VideoTagDTO videoTagDTO) {
        QueryWrapper<VideoInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FILE_NAME",videoTagDTO.getFileName());
        VideoInfo videoInfo = videoInfoMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(videoTagDTO,videoInfo);
        //??????
        int row = videoInfoMapper.updateById(videoInfo);
        if (row > 0){
            log.info("=================== update tag is ok!");
        }
    }

    /**
     *
     * @description: ????????????????????????
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
            //??????
            boolean bRet = manualRecordInfoMapper.updateById(manualRecordInfo)==1;
            return new BaseResponse<>(CustomErrorType.SUCCESS, bRet);
        } catch (Exception e) {
            return new BaseResponse<>(CustomErrorType.CRUD_UPDATE_ERROR, false);
        }
    }

    //??????????????????????????????????????????
    private void addManualRecordInfo(ManualRecordInfoParamDTO manualRecordInfoParamDTO) throws Exception {
        try {
            //????????????
            FileParamDTO fileParamDTO = new FileParamDTO();
            fileParamDTO.setCameraId(manualRecordInfoParamDTO.getCameraId());
            fileParamDTO.setStartTime(manualRecordInfoParamDTO.getStartTime());
            fileParamDTO.setStopTime(manualRecordInfoParamDTO.getStopTime());
            List<HCNetSDK.NET_DVR_FINDDATA_V30> finddataV30s = getFileNameInfo(fileParamDTO);
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
                //?????????????????????
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
        } catch (Exception e) {
            throw new CustomException(CustomErrorType.CRUD_INSERT_ERROR);
        }
    }

    /**
     *
     * @description: ??????????????????????????????
     * @param manualRecordInfoParamDTO
     * @return: java.util.List<com.sdt.fsc.entity.record.ManualRecordInfo>
     * @author: hegonglei
     * @date: 2021-7-7 9:10
     */
    @Override
    public List<ManualRecordInfo> findManualRecordInfo(ManualRecordInfoParamDTO manualRecordInfoParamDTO) throws Exception {
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
            //????????????????????????????????????
            addManualRecordInfo(manualRecordInfoParamDTO);
            List<ManualRecordInfo> manualRecordInfos = manualRecordInfoMapper.selectList(wrapper);
            return manualRecordInfos;
        } else {
            return manualRecordInfoList;
        }
    }


    //TODO
    @Override
    public Map<String, Object> getDeviceChannelInfo(String channel) {
        Map<String,Object> map = new HashMap<>();
        HCNetSDK.NET_DVR_STREAM_INFO mlpInBuffer = new HCNetSDK.NET_DVR_STREAM_INFO();
        mlpInBuffer.write();
        Pointer inBufferPointer = mlpInBuffer.getPointer();
        mlpInBuffer.dwSize = mlpInBuffer.size(); //?????????????????????
        mlpInBuffer.dwChannel = Integer.parseInt(channel); //????????????
//        NativeLong lUserID = (NativeLong) cache.get(ip);
        HCNetSDK.NET_DVR_GBT28181_CHANINFO_CFG mlpOutBuffer = new HCNetSDK.NET_DVR_GBT28181_CHANINFO_CFG();
        mlpOutBuffer.write();
        Pointer pointer = mlpOutBuffer.getPointer();
        if (lUserID.intValue() == -1) {
            DeviceLoginInfo deviceLoginInfo = null;
            this.loginV30(deviceLoginInfo);
            if (lUserID.intValue() == -1) {
                return map;
            }
        }
        //3251?????????????????????
        boolean result = hCNetSDK.NET_DVR_GetDeviceConfig(lUserID,3251,1,inBufferPointer,mlpInBuffer.size(),inBufferPointer,pointer,mlpOutBuffer.size());
        String videoChannelNumID = "";
        if (result) {
            mlpOutBuffer.read();
            videoChannelNumID = mlpOutBuffer.szVideoChannelNumID.toString();
            map.put("videoChannelNumID",videoChannelNumID);
        }else {
            int code = hCNetSDK.NET_DVR_GetLastError();
            map.put("code",code);
        }

        return map;
    }

    /**
     *
     * @description: ?????????????????????NET_DVR_TIME
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
            //??????????????????
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
            throw new CustomException(CustomErrorType.ERROR.getCode(),e.getLocalizedMessage()==null?String.valueOf(e):e.getLocalizedMessage());
        }
    }

    /**
     *
     * @description: ?????????NET_DVR_TIME????????????
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
     * @description: ISAPI??????????????????
     * @param
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-7-2 19:45
     */
    private JSONObject NET_DVR_STDXMLConfig(DeviceLoginInfo deviceLoginInfo) throws Exception {
        JSONObject resultObj = new JSONObject();
        if (lUserID == null || lUserID.intValue() == -1) {
            if (!initialize(deviceLoginInfo)) {
                return null;
            }
        }
        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struXmlConfigInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struXmlConfigOutput  = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struXmlConfigInput.read();
        struXmlConfigOutput.read();
        struXmlConfigInput.dwSize = struXmlConfigInput.size();
        String requestUrl = "GET /ISAPI/System/Network/SIP/1/SipInfo\r\n";
        HCNetSDK.BYTE_ARRAY ptrUrl = new HCNetSDK.BYTE_ARRAY(requestUrl.length());
        System.arraycopy(requestUrl.getBytes(),0,ptrUrl.byValue,0,requestUrl.length());
        ptrUrl.write();
        struXmlConfigInput.lpRequestUrl = ptrUrl.getPointer();
        struXmlConfigInput.dwRequestUrlLen = requestUrl.length();
        struXmlConfigInput.lpInBuffer = null;
        struXmlConfigInput.dwInBufferSize = 0;
        struXmlConfigInput.write();
        struXmlConfigOutput.dwSize = struXmlConfigOutput.size();
        HCNetSDK.BYTE_ARRAY ptrOutByte = new HCNetSDK.BYTE_ARRAY(10*1024);
        struXmlConfigOutput.lpOutBuffer = ptrOutByte.getPointer();
        struXmlConfigOutput.dwOutBufferSize = 10*1024;
        struXmlConfigOutput.write();
        boolean bRet = hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struXmlConfigInput, struXmlConfigOutput);
        if (!bRet) {
            System.out.println("ISAPI??????????????????SDK????????????,????????????" + hCNetSDK.NET_DVR_GetLastError());
            log.info("ISAPI??????????????????SDK????????????,????????????" + hCNetSDK.NET_DVR_GetLastError());
        } else {
            System.out.println("NET_DVR_STDXMLConfig success");
            struXmlConfigOutput.read();
            ptrOutByte.read();
            System.out.println("lpOutBuffer:" + new String(ptrOutByte.byValue).trim());
            String strLpOutBuffer = new String(ptrOutByte.byValue).trim();
            resultObj = Xml2JsonUtil.parseXmlString2Json(strLpOutBuffer);
        }
        return resultObj;
    }

    /**
     *
     * @description: ??????ISAPI???????????????????????????????????????????????????
     * @return: int
     * @author: hegonglei
     * @date: 2021-7-2 17:47
     */
    public int getChannelByGameraID(String cameraId) {
        int iChannel = -1;
        LinkedList<VideoInputDTO> videoInputDTOLinkedList = new LinkedList<>();
        try {
            if (lUserID == null || lUserID.intValue() == -1){
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(cameraId);
                boolean bRet = initialize(deviceLoginInfo);
                if (!bRet) {
                    return -1;
                }
            }
            if (videoInputDTOLinkedList==null || videoInputDTOLinkedList.size()==0) {
                DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(cameraId);
                JSONObject stdXmlConfigObject = NET_DVR_STDXMLConfig(deviceLoginInfo);
                if (stdXmlConfigObject != null) {
                    JSONObject videoInputList = (JSONObject) ((JSONArray) stdXmlConfigObject.get("VideoInputList")).get(0);
                    videoInputDTOLinkedList = JSON.parseObject(JSONObject.toJSONString(videoInputList.get("VideoInput")), new TypeReference<LinkedList<VideoInputDTO>>() {});
                }
            }
            JSONObject cameraInfoObj = (JSONObject) cameraServiceImpl.getCameraInfo(cameraId).get("data");
            if (cameraInfoObj != null) {
                String gbId = cameraInfoObj.get("GBID").toString();
                for (VideoInputDTO videoInputDTO : videoInputDTOLinkedList) {
                    int videoInputID = Integer.parseInt(videoInputDTO.getId());
                    if (gbId.equals(videoInputDTO.getVideoInputID())) {
                        if (videoInputID <= m_strIpparaCfg.dwAChanNum) {
                            iChannel = videoInputID;
                        } else {
                            iChannel = m_strIpparaCfg.dwStartDChan + videoInputID - m_strIpparaCfg.dwAChanNum - 1;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iChannel;
    }

    /**
     *
     * @description: ?????????????????????????????????
     * @param stopRecordParamDTO
     * @return: boolean
     * @author: hegonglei
     * @date: 2021-7-6 18:22
     */
    @Override
    public BaseResponse<Boolean> StopRecordWithRecordLabel(StopRecordParamDTO stopRecordParamDTO) throws Exception {
        if (lUserID == null || lUserID.intValue() == -1) {
            DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(stopRecordParamDTO.getCameraId());
            if(!initialize(deviceLoginInfo)){
                return new BaseResponse<>(CustomErrorType.DVR_LOGIN_ERROR, false);
            }
        }
        //???????????????
        Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(stopRecordParamDTO.getCameraId());
        if (iChannel==-1 || iChannel>=HCNetSDK.MAX_CHANNUM_V40) {
            log.info(CustomErrorType.VIDEO_CHANNEL_ERROR.getMessage());
            return new BaseResponse<>(CustomErrorType.VIDEO_CHANNEL_ERROR, false);
        }
//        Integer recordStatus = getRecordStatusV30(new CameraIdDTO(stopRecordParamDTO.getCameraId())).getData();
//        if (recordStatus == 1) {
        boolean bRet = stopDVRRecord(iChannel);
        if (!bRet) {
            log.info(CustomErrorType.DVR_StopDVRRecord_ERROR.getMessage());
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
        List<HCNetSDK.NET_DVR_FINDDATA_V30> finddataV30s = getFileNameInfo(fileParamDTO);
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
                    try {
                        manualRecordInfoMapper.insert(manualRecordInfo);
                    } catch (Exception e) {
                        return new BaseResponse<>(CustomErrorType.CRUD_INSERT_ERROR, false);
                    }
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
//        }
//        else {
//            return new BaseResponse<>(CustomErrorType.DVR_StopDVRRecord_ERROR, false);
//        }
    }

    /**
     *
     * @description: ???????????????????????????????????????
     * @param recordTimeSpanParamDTO
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-7-7 9:09
     */
    @Override
    public JSONObject getRecordTimeSpan(RecordTimeSpanParamDTO recordTimeSpanParamDTO) throws Exception {
        JSONObject resultObj = new JSONObject();
        if (lUserID == null || lUserID.intValue() == -1) {
            DeviceLoginInfo deviceLoginInfo = cameraServiceImpl.getDeviceLoginInfoByCameraId(recordTimeSpanParamDTO.getCameraId());
            boolean bResult = initialize(deviceLoginInfo);
            if (!bResult) {
                return null;
            }
        }
        //???????????????
        Integer iChannel = cameraServiceImpl.getChannelNoByCameraId(recordTimeSpanParamDTO.getCameraId());
        if (iChannel==-1 || iChannel>=HCNetSDK.MAX_CHANNUM_V40) {
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
            log.info("????????????????????????????????????,NET_DVR_InquiryRecordTimeSpan Failed,????????????" + hCNetSDK.NET_DVR_GetLastError());
            return null;
        }else {
            resultObj.put("strBeginTime", parseNETDVRTIME(recordTimeSpan.strBeginTime));
            resultObj.put("strEndTime", parseNETDVRTIME(recordTimeSpan.strEndTime));
            return resultObj;
        }
    }

}
