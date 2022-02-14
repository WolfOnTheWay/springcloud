package com.sdt.fsc.entity.alarm;

import java.io.Serializable;

/**
 * @description:
 * @author: hedonglei
 * @date: 2021-6-23 9:23
 */
public class QuerySyncPersonByPageDTO implements Serializable {
    private String name;
    private Integer currentPage;
    private Integer rowsOfPage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRowsOfPage() {
        return rowsOfPage;
    }

    public void setRowsOfPage(Integer rowsOfPage) {
        this.rowsOfPage = rowsOfPage;
    }
}
