package com.sdt.fsc.entity.alarm;

import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 9:12
 */
public class QueryControlTaskByPageDTO implements Serializable {
    private String id;
    private String type;
    private String status;
    private String name;
    private Integer pageIndex;
    private Integer pageSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
