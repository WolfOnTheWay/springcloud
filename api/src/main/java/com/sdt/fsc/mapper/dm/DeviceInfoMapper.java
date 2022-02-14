package com.sdt.fsc.mapper.dm;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdt.fsc.entity.camera.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @description: 设备信息mapper
 * @author: hegonglei
 * @date: 2021-7-7 12:48
 */
@Mapper
public interface DeviceInfoMapper extends BaseMapper<DeviceInfo> {
    @Select("SELECT * FROM BIGDATA.VIDEO_DEVICE_INFO")
    List<DeviceInfo> getList();

    @Select("SELECT * FROM BIGDATA.VIDEO_DEVICE_INFO WHERE GB_ID=#{gbId}")
    DeviceInfo selByGBID(@Param("gbId") String gbId);
}
