package com.sdt.fsc.entity.alarm;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aubin
 * @since 2021-06-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("BIGDATA.VIDEO_PERSONAL_INFO")
public class VideoPersonalInfo extends Model {

    private static final long serialVersionUID = 1L;

    @TableId("USER_ID")
    private String userId;

    @TableField("USER_NAME")
    private String userName;

    @TableField("USER_SEX")
    private String userSex;

    @TableField("USER_PHOTO_URL")
    private String userPhotoUrl;

    @TableField("DEPARTMENT")
    private String department;

    @TableField("POST_NAME")
    private String postName;

    @TableField("POST_ID")
    private String postId;


}
