package com.sdt.fsc.entity.alarm;

import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 9:39
 */
public class QueryPersonByPageDTO implements Serializable {
    private String personGroupId;
    private String name;
    private Integer pageNo;
    private Integer pageSize;

    public String getPersonGroupId() {
        return personGroupId;
    }

    public void setPersonGroupId(String personGroupId) {
        this.personGroupId = personGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
