package com.cy.pi.common.vo;

import com.cy.pi.sys.entity.SysDept;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/26 16:28
 */
@Data
public class SysUserDeptVo implements Serializable {
    private static final long serialVersionUID = 5506970528097076646L;
    private Integer id;
    private String username;
    private String password;//md5
    private String salt;
    private String email;
    private String mobile;
    private Integer valid=1;
    private SysDept sysDept; //private Integer deptId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
