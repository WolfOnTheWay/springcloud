package com.cy.pi.common.vo;

import java.io.Serializable;
import java.util.List;

/*封装分页数据*/
public class PageObject<T> implements Serializable {
    private static final long serialVersionUID = -1075764169445853247L;
    //总记录
    private Integer rowCount;
    private List<T> records;
    //总页数
    private Integer pageCount;
    private Integer pageCurrent;
    //每页大小
    private Integer pageSize;

    public PageObject(){}
    public PageObject(Integer rowCount, List<T> records, Integer pageCurrent, Integer pageSize) {
        this.rowCount = rowCount;
        this.records = records;
        this.pageCount = (rowCount-1)/pageSize+1;
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(Integer pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
