package com.sdt.fsc.entity.record;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.sql.Timestamp;

/**
 *
 * @description:
 * @author: hegonglei
 * @date: 2021-7-6 13:25
 */
@Data
@TableName("BIGDATA.VIDEO_MANUALRECORD_INFO")
@JsonPropertyOrder(value = {"fileId","cameraId","fileName","fileSize","startTime","stopTime","recordLabelName","recordLabelTime","operator","createTime"})
public class ManualRecordInfo {

    @JsonProperty(value = "fileId")
    @TableId(value = "FILE_ID")
    private String fileId;
    @JsonProperty(value = "cameraId")
    @TableField(value = "CAMERA_ID")
    private String cameraId;
    @JsonProperty(value = "fileName")
    @TableField("FILE_NAME")
    private String fileName;
    @JsonProperty(value = "fileSize")
    @TableField("FILE_SIZE")
    private String fileSize;
    @JsonProperty(value = "startTime")
    @TableField("START_TIME")
    private String startTime;
    @JsonProperty(value = "stopTime")
    @TableField("STOP_TIME")
    private String stopTime;
    @JsonProperty(value = "recordLabelName")
    @TableField("RECORD_LABEL_NAME")
    private String recordLabelName;
    @JsonProperty(value = "recordLabelTime")
    @TableField("RECORD_LABEL_TIME")
    private Timestamp recordLabelTime;
    @JsonProperty(value = "operator")
    @TableField("OPERATOR")
    private String operator;
    @TableField("CREATE_TIME")
    @JsonProperty("createTime")
    private Timestamp createTime;

}
