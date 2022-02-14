package com.cy.pi.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/*
* POJO普通的java对象
* PO:持久化对象，与表中字段有一一对应关系
*
* */
@Data
public class SysLog implements Serializable {

    private static final long serialVersionUID = -8616075785821479685L;
    private Integer id;
    private String username;
    private String operation;
    /**执行的方法*/
    private String method;
    /**执行方法时传入的参数*/
    private String params;
    private Long  time;
    private String ip;
    private Date createdTime;
}
