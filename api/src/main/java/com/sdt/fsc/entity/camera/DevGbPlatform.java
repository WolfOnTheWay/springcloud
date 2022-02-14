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
public class DevGbPlatform extends Model {

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

    private Long sort;

    /**
     * directoryType，0代表列表目录,1代表父设备id，2代表行政区划
     */
    private Integer directoryType;

    private LocalDateTime modifyTime;

    /**
     * 平台属性：0-实时+录像，1-仅获取录像
     */
    private Integer property;

    /**
     * 国标网关服务组件
     */
    private String sipGatewayComponent;


}
