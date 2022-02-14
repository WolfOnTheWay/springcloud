package com.sdt.fsc.entity.search;

import java.util.List;

public class OrcTableResultData {
    List<OrcTable> orcTableList;
    private Integer totalPages;
    private Integer totalSizes;
    private Integer pageSize;
    private Integer page;

    public List<OrcTable> getOrcTableList() {
        return orcTableList;
    }

    public void setOrcTableList(List<OrcTable> orcTableList) {
        this.orcTableList = orcTableList;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalSizes() {
        return totalSizes;
    }

    public void setTotalSizes(Integer totalSizes) {
        this.totalSizes = totalSizes;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void paging(Integer page) {
        if(page>(this.totalPages-1))
        {
            page = this.totalPages-1;
        }
        this.page = page;

        Integer start = this.pageSize*page;
        if(start<0)
            start = 0;
        Integer end = this.pageSize*page+this.pageSize;

        if(end> this.totalSizes)
            end =  this.totalSizes;
        this.orcTableList =  this.orcTableList.subList(start,end);
    }
}
