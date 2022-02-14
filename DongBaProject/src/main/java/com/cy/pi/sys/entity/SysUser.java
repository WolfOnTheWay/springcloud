package com.cy.pi.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/26 17:03
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -804929410307861120L;
    private Integer id;
    private String username;
    private String password;
    private String salt;//盐值
    private String email;
    private String mobile;
    private Integer valid=1;
    private Integer deptId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
