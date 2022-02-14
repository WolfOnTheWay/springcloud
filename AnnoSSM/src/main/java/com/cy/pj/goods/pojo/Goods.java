package com.cy.pj.goods.pojo;


import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {
    private static final long serialVersionUID = -450636776831296467L;
    private Long id;
    private String name;
    private String remark;
    private Date CreatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(Date createdTime) {
        CreatedTime = createdTime;
    }
}
