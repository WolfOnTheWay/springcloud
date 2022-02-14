package com.sdt.fsc.entity.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/8/28 10:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserLoginDTO {

    private String username;

    private String password;
}
