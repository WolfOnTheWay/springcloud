package com.sdt.fsc.entity.camera;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("BIGDATA.VIDEO_GBDEVICE_INFO")
@ApiModel(description = "国标接入资源设备登录信息实体")
public class GBDeviceInfo extends Model {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "国标编号",position = 1)
    @TableId("GB_ID")
    private String gbId;
    @ApiModelProperty(value = "IP",position = 2)
    @TableField("IP")
    private String ip;
    @ApiModelProperty(value = "端口",position = 3)
    @TableField("PORT")
    private Integer port;
    @ApiModelProperty(value = "用户名",position = 4)
    @TableField("USERNAME")
    private String username;
    @ApiModelProperty(value = "密码",position = 5)
    @TableField("PASSWORD")
    private String password;
}
