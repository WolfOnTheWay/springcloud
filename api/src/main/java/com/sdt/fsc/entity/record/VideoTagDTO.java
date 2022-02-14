package com.sdt.fsc.entity.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "videoTagDTO",description = "视频DTO实体")
@Data
public class VideoTagDTO {

    @ApiModelProperty(value = "视频名字")
    private String fileName;
    @ApiModelProperty(value = "视频标签")
    private String signtype;
    @ApiModelProperty(value = "视频标签详情")
    private String signcontent;
    @ApiModelProperty(value = "视频标签对象")
    private String signObject;
}
