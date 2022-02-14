package com.sdt.fsc.service.impl.alarm;

import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.alarm.*;
import com.sdt.fsc.service.contract.alarm.IAlarmService;
import com.sdt.fsc.util.RestApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 9:50
 */
@Service
public class IAlarmServiceImpl implements IAlarmService {

    @Autowired
    private RestApiUtils restApiUtils;
    @Value("${qz.preurl}")
    private String preUrl;

    @Override
    public BaseResponse<Object> queryControlTaskByPage(QueryControlTaskByPageDTO queryControlTaskByPageDTO) throws Exception {
        String url = preUrl + "alarm/controlTask/queryByPage";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, queryControlTaskByPageDTO);
        return new BaseResponse<>(CustomErrorType.SUCCESS, jsonObject);
    }

    @Override
    public BaseResponse<Object> queryControlTaskById(IdDTO idDTO) throws Exception {
        String url = preUrl + "alarm/controlTask/queryById";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, idDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Boolean> setControlTaskStatus(ControlTaskStatusDTO controlTaskStatusDTO) throws Exception {
        String url = preUrl + "alarm/controlTask/status";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, controlTaskStatusDTO);
        Boolean bRet = jsonObject.get("data") == null ? false : true;
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), bRet);
    }

    @Override
    public BaseResponse<Boolean> setAllControlTaskStatus(ControlTaskStatusAllDTO controlTaskStatusAllDTO) throws Exception {
        String url = preUrl + "alarm/controlTask/status/all";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, controlTaskStatusAllDTO);
        Boolean bRet = jsonObject.get("data") == null ? false : true;
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), bRet);
    }

    @Override
    public BaseResponse<Object> deleteControlTaskById(IdDTO idDTO) throws Exception {
        String url = preUrl + "alarm/controlTask/del";
        JSONObject jsonObject =  restApiUtils.doPostRequest(url, idDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Boolean> addPersonGroup(NameDTO nameDTO) throws Exception {
        String url = preUrl + "alarm/personGroup/add";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, nameDTO);
        Boolean bRet = jsonObject.get("data") == null ? false : true;
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), bRet);
    }

    @Override
    public BaseResponse<Boolean> editPersonGroup(EditPersonGroupDTO editPersonGroupDTO) throws Exception {
        String url = preUrl + "alarm/personGroup/edit";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, editPersonGroupDTO);
        Boolean bRet = jsonObject.get("data") == null ? false : true;
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), bRet);
    }

    @Override
    public BaseResponse<Boolean> deletePersonGroups(IdsDTO idsDTO) throws Exception {
        String url = preUrl + "alarm/personGroup/delete";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, idsDTO);
        Boolean bRet = jsonObject.get("data") == null ? false : true;
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), bRet);
    }

    @Override
    public BaseResponse<Object> queryPersonGroupByPage(QueryPersonGroupByPageDTO queryPersonGroupByPageDTO) throws Exception {
        String url = preUrl + "alarm/personGroup/page";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, queryPersonGroupByPageDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Object> querySyncPersonByPage(QuerySyncPersonByPageDTO querySyncPersonByPageDTO) throws Exception {
        String url = preUrl + "alarm/component/person/page";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, querySyncPersonByPageDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Object> deletePersons(IdsDTO idsDTO) throws Exception {
        String url = preUrl + "alarm/personGroup/person/delete";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, idsDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Object> queryPersonByPage(QueryPersonByPageDTO queryPersonByPageDTO) throws Exception {
        String url = preUrl + "alarm/personGroup/person/page";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, queryPersonByPageDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Object> queryAlarmRecordByPage(QueryAlarmRecordByPageDTO queryAlarmRecordByPageDTO) throws Exception {
        String url = preUrl + "alarm/alarmRecord/page";
        JSONObject jsonObject = restApiUtils.doPostRequest(url, queryAlarmRecordByPageDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Object> queryDeviceByPage(DeviceDTO deviceDTO) throws Exception {
        String url = preUrl + "alarm/device/page";
        JSONObject jsonObject = restApiUtils.doPostRequest(url,deviceDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Object> queryDeviceDetailsById(IdDTO idDTO) throws Exception {
        String url = preUrl + "alarm/device/detail";
        JSONObject jsonObject = restApiUtils.doPostRequest(url,idDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Object> controlCaptureSave(ControlCaptureVO controlCaptureVO) throws Exception {
        String url = preUrl + "/xingtang/config/update";
        ControlSaveCaptureDTO controlSaveCaptureDTO = new ControlSaveCaptureDTO();
        controlSaveCaptureDTO.setCode("save_img");
        if (controlCaptureVO.getStatus() == true) {
            controlSaveCaptureDTO.setValue("1");
        } else {
            controlSaveCaptureDTO.setValue("0");
        }
        JSONObject jsonObject = restApiUtils.doPostRequest(url,controlSaveCaptureDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }

    @Override
    public BaseResponse<Object> queryPeoplesByControlTaskName(ControlTaskNameDTO controlTaskNameDTO) throws Exception {
        String url = preUrl + "alarm/alarmRecord/num";
        JSONObject jsonObject = restApiUtils.doPostRequest(url,controlTaskNameDTO);
        return new BaseResponse<>(jsonObject.get("code").toString(), jsonObject.get("message").toString(), jsonObject.get("data"));
    }
}
