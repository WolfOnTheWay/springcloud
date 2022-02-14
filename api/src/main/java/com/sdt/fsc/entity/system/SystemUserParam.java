package com.sdt.fsc.entity.system;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class SystemUserParam {

    private String username;
    private String password;
}
