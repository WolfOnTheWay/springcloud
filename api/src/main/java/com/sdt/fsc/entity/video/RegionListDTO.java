package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtiexiang
 * @Description 分页获取区域列表
 * @Datetime 2020/8/10 11:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionListDTO {

    private Integer total;

    private Integer pageNo;

    private Integer pageSize;

    private RegionDTO[] list;
}
