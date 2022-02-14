package com.sdt.fsc.entity.record;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aubin
 * @since 2021-06-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("VIDEO_INFO")
@ApiModel(value = "视频详情信息")
public class VideoInfo extends Model {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "视频ID")
    @TableId("VIDEO_ID")
    private String videoId;
    @ApiModelProperty(value = "视频名字")
    @TableField("FILE_NAME")
    private String fileName;
    @ApiModelProperty(value = "视频大小")
    @TableField("FILE_SIZE")
    private String fileSize;
    @ApiModelProperty(value = "视频操作人")
    @TableField("OPTIONAL_USER_NAME")
    private String optionalUserName;
    @ApiModelProperty(value = "视频标记类型")
    @TableField("SIGN_TYPE")
    private String signtype;
    @ApiModelProperty(value = "视频标记详情内容")
    @TableField("SIGN_CONTENT")
    private String signcontent;
    @ApiModelProperty(value = "视频手动录像开始时间")
    @TableField("START_TIME")
    private String starttime;
    @ApiModelProperty(value = "视频手动录像结束时间")
    @TableField("STOP_TIME")
    private String stoptime;
    @TableField("SIGN_OBJECT")
    @ApiModelProperty(value = "视频标签对象")
    private String signObject;

}
