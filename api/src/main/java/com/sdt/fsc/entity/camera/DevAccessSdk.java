package com.sdt.fsc.entity.camera;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aubin
 * @since 2021-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DevAccessSdk extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 对接协议
     */
    private String protocol;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String gbId;

    private LocalDateTime modifyTime;


}
