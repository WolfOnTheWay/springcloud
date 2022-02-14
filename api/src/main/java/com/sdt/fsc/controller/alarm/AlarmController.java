package com.sdt.fsc.controller.alarm;

import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.entity.alarm.*;
import com.sdt.fsc.service.impl.alarm.IAlarmServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 视频分析服务控制器
 * @author: hedonglei
 * @date: 2021-6-22 20:08
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/video")
@Api(description = "视频分析服务API")
public class AlarmController {

    @Autowired
    private IAlarmServiceImpl iAlarmServiceImpl;

    @PostMapping("/alarm/controlTask/queryControlTaskByPage")
    @ResponseBody
    @ApiOperation(value = "获取所有视频分析任务列表")
    public BaseResponse<Object> queryControlTaskByPage(@RequestBody QueryControlTaskByPageDTO queryControlTaskByPageDTO)throws Exception{
        return iAlarmServiceImpl.queryControlTaskByPage(queryControlTaskByPageDTO);
    }

    @PostMapping("/alarm/controlTask/queryControlTaskById")
    @ResponseBody
    @ApiOperation(value = "获取指定视频分析任务")
    public BaseResponse<Object> queryControlTaskById(@RequestBody IdDTO idDTO)throws Exception{
        return iAlarmServiceImpl.queryControlTaskById(idDTO);
    }

    @PostMapping("/alarm/controlTask/setControlTaskStatus")
    @ResponseBody
    @ApiOperation(value = "开始/停止指定视频分析任务")
    public BaseResponse<Boolean> setControlTaskStatus(@RequestBody ControlTaskStatusDTO controlTaskStatusDTO)throws Exception{
        return iAlarmServiceImpl.setControlTaskStatus(controlTaskStatusDTO);
    }

    @PostMapping("/alarm/controlTask/setAllControlTaskStatus")
    @ResponseBody
    @ApiOperation(value = "开始/停止全部视频分析任务")
    public BaseResponse<Boolean> setAllControlTaskStatus(@RequestBody ControlTaskStatusAllDTO controlTaskStatusAllDTO)throws Exception{
        return iAlarmServiceImpl.setAllControlTaskStatus(controlTaskStatusAllDTO);
    }

    @PostMapping("/alarm/controlTask/deleteControlTaskById")
    @ResponseBody
    @ApiOperation(value = "删除视频分析任务")
    public BaseResponse<Object> deleteControlTaskById(@RequestBody IdDTO idDTO)throws Exception{
        return iAlarmServiceImpl.deleteControlTaskById(idDTO);
    }

    @PostMapping("/alarm/controlTask/addPersonGroup")
    @ResponseBody
    @ApiOperation(value = "新增人员库")
    public BaseResponse<Boolean> addPersonGroup(@RequestBody NameDTO nameDTO)throws Exception{
        return iAlarmServiceImpl.addPersonGroup(nameDTO);
    }

    @PostMapping("/alarm/controlTask/editPersonGroup")
    @ResponseBody
    @ApiOperation(value = "编辑人员库")
    public BaseResponse<Boolean> editPersonGroup(@RequestBody EditPersonGroupDTO editPersonGroupDTO)throws Exception{
        return iAlarmServiceImpl.editPersonGroup(editPersonGroupDTO);
    }

    @PostMapping("/alarm/controlTask/deletePersonGroups")
    @ResponseBody
    @ApiOperation(value = "批量删除人员库")
    public BaseResponse<Boolean> deletePersonGroups(@RequestBody IdsDTO idsDTO)throws Exception{
        return iAlarmServiceImpl.deletePersonGroups(idsDTO);
    }

    @PostMapping("/alarm/controlTask/queryPersonGroupByPage")
    @ResponseBody
    @ApiOperation(value = "分页查询人员库")
    public BaseResponse<Object> deletePersonGroups(@RequestBody QueryPersonGroupByPageDTO queryPersonGroupByPageDTO)throws Exception{
        return iAlarmServiceImpl.queryPersonGroupByPage(queryPersonGroupByPageDTO);
    }

    @PostMapping("/alarm/controlTask/querySyncPersonByPage")
    @ResponseBody
    @ApiOperation(value = "分页查询同步人员")
    public BaseResponse<Object> querySyncPersonByPage(@RequestBody QuerySyncPersonByPageDTO querySyncPersonByPageDTO)throws Exception{
        return iAlarmServiceImpl.querySyncPersonByPage(querySyncPersonByPageDTO);
    }

    @PostMapping("/alarm/controlTask/queryPersonByPage")
    @ResponseBody
    @ApiOperation(value = "分页查询人员库中的人员")
    public BaseResponse<Object> queryPersonByPage(@RequestBody QueryPersonByPageDTO queryPersonByPageDTO)throws Exception{
        return iAlarmServiceImpl.queryPersonByPage(queryPersonByPageDTO);
    }

    @PostMapping("/alarm/controlTask/deletePersons")
    @ResponseBody
    @ApiOperation(value = "批量删除人员库中的人员")
    public BaseResponse<Object> deletePersons(@RequestBody IdsDTO idsDTO)throws Exception{
        return iAlarmServiceImpl.deletePersons(idsDTO);
    }

    @PostMapping("/alarm/controlTask/queryAlarmRecordByPage")
    @ResponseBody
    @ApiOperation(value = "分页查询报警记录")
    public BaseResponse<Object> queryAlarmRecordByPage(@RequestBody QueryAlarmRecordByPageDTO queryAlarmRecordByPageDTO)throws Exception{
        return iAlarmServiceImpl.queryAlarmRecordByPage(queryAlarmRecordByPageDTO);
    }

    @PostMapping("/alarm/controlTask/queryDeviceByPage")
    @ResponseBody
    @ApiOperation(value = "分页查询设备信息")
    public BaseResponse<Object> queryDeviceByPage(@RequestBody DeviceDTO deviceDTO)throws Exception{
        return iAlarmServiceImpl.queryDeviceByPage(deviceDTO);
    }

    @PostMapping("/alarm/controlTask/queryDeviceDetailsById")
    @ResponseBody
    @ApiOperation(value = "查询设备信息详情")
    public BaseResponse<Object> queryDeviceDetailsById(@RequestBody IdDTO idDTO)throws Exception{
        return iAlarmServiceImpl.queryDeviceDetailsById(idDTO);
    }

    @PostMapping("/alarm/controlTask/controlSaveCapture")
    @ResponseBody
    @ApiOperation(value = "抓拍照存储开启")
    public BaseResponse<Object> controlSaveCapture(@RequestBody ControlCaptureVO controlCaptureVO)throws Exception{
        return iAlarmServiceImpl.controlCaptureSave(controlCaptureVO);
    }

    @PostMapping("/alarm/controlTask/queryPeoplesByControlTaskName")
    @ResponseBody
    @ApiOperation(value = "根据任务名查询人数信息")
    public BaseResponse<Object> queryPeoplesByControlTaskName(@RequestBody ControlTaskNameDTO controlTaskNameDTO)throws Exception{
        return iAlarmServiceImpl.queryPeoplesByControlTaskName(controlTaskNameDTO);
    }

}
