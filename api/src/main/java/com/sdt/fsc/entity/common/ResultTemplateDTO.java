package com.sdt.fsc.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 统一返回模板
 * @Datetime 2020/8/10 10:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultTemplateDTO<V> {
    /**
     * 返回码，0 – 成功，其他- 失败
     */
    private String code;
    /**
     * 接口执行情况说明信息
     */
    private String msg;
    /**
     * 区域信息结构体
     */
    private V data;
}
