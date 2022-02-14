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
public class DevAccessGb extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 国标编号
     */
    private String gbId;

    /**
     * 国标设备密码
     */
    private String pass;

    private LocalDateTime modifyTime;


}
