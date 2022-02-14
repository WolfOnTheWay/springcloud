package com.sdt.fsc.util;

import lombok.Data;

import java.util.List;

/**
 * 分页的工具类
 * @author 陈斌
 * @param <T>
 */
@Data
public class Page<T> {
    //当前第几页
    private int pageIndex;
    //每页的记录数
    private int pageSize;
    //查询的记录数
    private int totalRecords;
    //结果集
    private List<T> data;

    /**
     * 总的页数
     * @return
     */
    public int getTotalPages() {
        return (totalRecords + pageSize -1) /pageSize;
    }

    /**
     * 上一页
     * @return
     */
    public int getPreviousPage(){
        if (pageIndex <=1){
            return 1;
        }
        return pageIndex -1;
    }

    public int getNextPageIndex(){
        if (pageIndex >= getTotalPages()){
            return getTotalPages();
        }
        return pageIndex +1;
    }
}
