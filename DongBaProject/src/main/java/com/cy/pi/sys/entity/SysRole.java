package com.cy.pi.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/24 10:19
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = -2547123391076103173L;
    private Integer id;
    private String name;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}
