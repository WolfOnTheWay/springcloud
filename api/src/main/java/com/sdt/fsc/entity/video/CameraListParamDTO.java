package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 分页获取监控点资源输入参数
 * @Datetime 2020/8/10 11:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraListParamDTO {
    /**
     * 范围 ( 0 , ~ )，若是范围内数字超过实际最大页码值，返回最后一页记录 注：0 < pageNo
     * 必填
     */
    private Integer pageNo;
    /**
     * 范围 ( 0 , 1000 ] 注：0 < pageSize≤1000
     * 必填
     */
    private Integer pageSize;
    /**
     * 树编号。综合安防平台当前未使用该字段。该字段预留
     */
    private String treeCode;
}
