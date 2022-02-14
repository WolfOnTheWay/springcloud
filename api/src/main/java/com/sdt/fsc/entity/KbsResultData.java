package com.sdt.fsc.entity;

import com.sdt.fsc.entity.response.Content;

import java.io.Serializable;
import java.util.List;

/**
 * 知识库 查询 返回结果
 */
public class KbsResultData implements Serializable {
    private static final long serialVersionUID = -101091479362028214L;
    private List<Content> content;
    private Integer totalPages;
    private Integer totalSizes;
    private Integer pageSize;
    private Integer page;

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
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
        this.content =  this.content.subList(start,end);
    }

}