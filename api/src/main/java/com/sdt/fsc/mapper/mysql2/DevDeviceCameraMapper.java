package com.sdt.fsc.mapper.mysql2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdt.fsc.entity.camera.DevDeviceCamera;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aubin
 * @since 2021-07-05
 */
public interface DevDeviceCameraMapper extends BaseMapper<DevDeviceCamera> {
    @Select("select chan_no from dev_device_camera where id=#{id}")
    String getChannelNoByCameraId(@Param("id") String id);

    @Select("select * from dev_device_camera where 1=1")
    List<DevDeviceCamera> getAllDevDeviceCameraList();
}
