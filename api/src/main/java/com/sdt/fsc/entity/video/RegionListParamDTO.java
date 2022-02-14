package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionListParamDTO {
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
}
