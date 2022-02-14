package com.sdt.fsc.entity.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value = "手动录像文件信息实体")
@Data
public class ManualRecordInfoParamDTO implements Serializable {

    @NotNull(message = "摄像头ID不能为空")
    @ApiModelProperty(value = "摄像头ID")
    private String cameraId;
    @NotNull(message = "录像开始时间不能为空")
    @ApiModelProperty(value = "录像开始时间")
    private String startTime;
    @NotNull(message = "录像结束时间不能为空")
    @ApiModelProperty(value = "录像结束时间")
    private String stopTime;
    @ApiModelProperty(value = "录像标签名称",notes = "可选参数")
    private String recordLabelName;
    @ApiModelProperty(value = "录像标签标记时间",notes = "可选参数")
    private Long recordLabelTime;
    @ApiModelProperty(value = "操作用户",notes = "可选参数")
    private String operator;
}
