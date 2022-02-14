package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangtiexiang
 * @Description 获取监控点在线状态输入参数
 * @Datetime 2020/10/29 14:03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraStatusParamDTO {
    /**
     * 区域编号，可从[查询区域列表v2]@[软件产品-综合安防管理平台-API列表-资源目录-区域信息接口#查询区域列表v2]接口获取返回参数indexCode
     */
    private String regionId;
    /**
     * 是否包含下级，1包含，0不包含（若regionId为空，则该参数不起作用）
     */
    private String includeSubNode;
    /**
     * 监控点编码集，最大500，[查询监控点列表]@[软件产品-综合安防管理平台-API列表-视频应用服务-视频资源#查询监控点列表]接口获取返回参数cameraIndexCode
     */
    private List<String> indexCodes;
    /**
     * 状态，1-在线，0-离线，-1-未检测
     */
    private String status;
    /**
     * 页码，范围 ( 0 , ~ )
     */
    private Integer pageNo;
    /**
     * 页大小，范围 ( 0 , 1000 ]
     */
    private Integer pageSize;
}
