package com.sdt.fsc.service.contract.alarm;

import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.entity.alarm.*;

/**
 *
 * @description: 视频分析服务接口
 * @author: hegonglei
 * @date: 2021-6-9 14:54
 */
public interface IAlarmService {

    /**
     *
     * @description: 获取所有分析任务列表
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:53
     */
    BaseResponse<Object> queryControlTaskByPage(QueryControlTaskByPageDTO queryControlTaskByPageDTO) throws Exception;

    /**
     *
     * @description: 查询重点人员属性布控任务/查询区域人数统计布控任务详情
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:53
     */
    BaseResponse<Object> queryControlTaskById(IdDTO idDTO) throws Exception;

    /**
     *
     * @description: 开始/停止特定分析任务
     * @param
     * @return: BaseResponse<Boolean>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Boolean> setControlTaskStatus(ControlTaskStatusDTO controlTaskStatusDTO) throws Exception;

    /**
     *
     * @description: 开始/停止全部分析任务
     * @param
     * @return: BaseResponse<Boolean>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Boolean> setAllControlTaskStatus(ControlTaskStatusAllDTO controlTaskStatusAllDTO) throws Exception;

    /**
     *
     * @description: 删除特定分析任务
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> deleteControlTaskById(IdDTO idDTO) throws Exception;

    /**
     *
     * @description: 新增人员库
     * @param
     * @return: BaseResponse<Boolean>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Boolean> addPersonGroup(NameDTO nameDTO) throws Exception;

    /**
     *
     * @description: 编辑人员库
     * @param
     * @return: BaseResponse<Boolean>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Boolean> editPersonGroup(EditPersonGroupDTO editPersonGroupDTO) throws Exception;

    /**
     *
     * @description: 批量删除人员库
     * @param
     * @return: BaseResponse<Boolean>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Boolean> deletePersonGroups(IdsDTO idsDTO) throws Exception;

    /**
     *
     * @description: 分页查询人员库
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> queryPersonGroupByPage(QueryPersonGroupByPageDTO queryPersonGroupByPageDTO) throws Exception;

    /**
     *
     * @description: 分页查询同步人员
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> querySyncPersonByPage(QuerySyncPersonByPageDTO querySyncPersonByPageDTO) throws Exception;

    /**
     *
     * @description: 批量删除人员
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> deletePersons(IdsDTO idsDTO) throws Exception;

    /**
     *
     * @description: 分页查询人员库中的人员
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> queryPersonByPage(QueryPersonByPageDTO queryPersonByPageDTO) throws Exception;

    /**
     *
     * @description: 分页查询报警记录
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> queryAlarmRecordByPage(QueryAlarmRecordByPageDTO queryAlarmRecordByPageDTO) throws Exception;

    /**
     *
     * @description: 分页查询设备信息
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> queryDeviceByPage(DeviceDTO deviceDTO) throws Exception;

    /**
     *
     * @description: 查询设备信息详情
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> queryDeviceDetailsById(IdDTO idDTO) throws Exception;

    /**
     *
     * @description: 事件图片存储开启/关闭（抓拍照）
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> controlCaptureSave(ControlCaptureVO controlCaptureVO) throws Exception;

    /**
     *
     * @description: 根据任务名查询人数信息
     * @param
     * @return: BaseResponse<Object>
     * @author: hegonglei
     * @date: 2021-6-18 13:56
     */
    BaseResponse<Object> queryPeoplesByControlTaskName(ControlTaskNameDTO controlTaskNameDTO) throws Exception;

}
