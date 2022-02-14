package com.cy.pi.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/25 16:51
 */
@Data
public class SysDept implements Serializable {
    private static final long serialVersionUID = -4597582439516724966L;
    private int id;
    private String name;
    private int parentId;
    private int sort;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
