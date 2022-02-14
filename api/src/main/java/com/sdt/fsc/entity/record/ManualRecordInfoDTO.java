package com.sdt.fsc.entity.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "视频文件信息查找实体")
@Data
public class ManualRecordInfoDTO implements Serializable {

    @ApiModelProperty(value = "通道")
    private String lChannel;
    @ApiModelProperty(value = "录像开始时间")
    private String startTime;
    @ApiModelProperty(value = "录像结束时间")
    private String stopTime;
    @ApiModelProperty(value = "录像标签",notes = "可选参数")
    private String videoTag;

}
