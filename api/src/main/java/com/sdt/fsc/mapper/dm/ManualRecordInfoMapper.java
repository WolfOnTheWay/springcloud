package com.sdt.fsc.mapper.dm;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdt.fsc.entity.record.ManualRecordInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aubin
 * @since 2021-06-08
 */
@Mapper
public interface ManualRecordInfoMapper extends BaseMapper<ManualRecordInfo> {
    @Select("<script>"+
            "   select file_id,cameraId,file_name,file_size,start_time,stop_time,record_label_name,record_label_time,operator,create_time" +
            "   from bigdata.video_manualrecord_info where 1=1 " +
            "   <if test='startTime!=null'>" +
            "      and start_time>=#{startTime} "+
            "   </if>" +
            "   <if test='stopTime!=null'>" +
            "      <![CDATA[ and stop_time<=#{stopTime} ]]>" +
            "   </if>" +
            "   <if test='recordLabelName!=null'>" +
            "      and record_label_name=#{recordLabelName} "+
            "   </if>" +
            "   <if test='recordLabelTime!=null'>" +
            "      and record_label_time=#{recordLabelTime} "+
            "   </if>" +
            "   order by create_time desc" +
            "</script>")
    @Results({
            @Result(column = "file_id",property = "fileId"),
            @Result(column = "camera_id",property = "cameraId"),
            @Result(column = "file_name",property = "fileName"),
            @Result(column = "file_size",property = "fileSize"),
            @Result(column = "start_time",property = "startTime"),
            @Result(column = "stop_time",property = "stopTime"),
            @Result(column = "record_label_name",property = "recordLabelName"),
            @Result(column = "record_label_time",property = "recordLabelTime"),
            @Result(column = "operator",property = "operator"),
            @Result(column = "create_time",property = "createTime")
    })
    Page<ManualRecordInfo> findAll(
            Page page,
            @Param("startTime") String startTime,
            @Param("stopTime") String stopTime,
            @Param("recordLabelName") String recordLabelName,
            @Param("recordLabelTime") String recordLabelTime
    );

    @Select("select * from bigdata.video_manualrecord_info where camera_id=#{cameraId} and start_time>=#{startTime} and stop_time<=#{stopTime}")
    List<ManualRecordInfo> getList(
            @Param("cameraId") String cameraId,
            @Param("startTime") String startTime,
            @Param("stopTime") String stopTime,
            @Param("recordLabelName") String recordLabelName,
            @Param("recordLabelTime") String recordLabelTime
    );
}
