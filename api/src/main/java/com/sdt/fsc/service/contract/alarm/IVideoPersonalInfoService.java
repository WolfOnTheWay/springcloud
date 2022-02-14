package com.sdt.fsc.service.contract.alarm;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdt.fsc.entity.alarm.VideoPersonalInfo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aubin
 * @since 2021-06-21
 */
public interface IVideoPersonalInfoService extends IService<VideoPersonalInfo> {

    Map<String, Object> getAllInfo() throws Exception;

    Boolean savePersonInfo(VideoPersonalInfo videoPersonalInfo) throws Exception;
}
