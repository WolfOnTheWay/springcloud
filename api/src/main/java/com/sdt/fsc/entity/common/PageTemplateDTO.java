package com.sdt.fsc.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 分页模板 类Mybatis-plus分页插件
 * @Datetime 2020/8/10 10:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageTemplateDTO<V> {
    /**
     * 查询数据记录总数
     */
    private Integer total;
    /**
     * 当前页码
     */
    private Integer pageNo;
    /**
     * 每页记录总数
     */
    private Integer pageSize;

    /**
     * 数据列表
     */
    V[] list;
}
