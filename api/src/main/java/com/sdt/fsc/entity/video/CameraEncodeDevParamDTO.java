package com.sdt.fsc.entity.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangtiexiang
 * @Description 获取视频编码设备列表输入参数
 * @Datetime 2020/9/23 10:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CameraEncodeDevParamDTO {
    /**
     *区域编号,可以为空; String[]，支持根据区域批量查询； 区域编号个数 <=1000个； 单个长度<=64Byte；，
     * 可从[查询区域列表v2]@[软件产品-综合安防管理平台-API列表-资源目录-区域信息接口#查询区域列表v2]接口获取返回参数indexCode
     */
    private String[] regionIndexCodes;
    /**
     * 当前页码，0 < pageNo
     */
    private Integer pageNo;
    /**
     * 分页大小，0<pageSize≤1000
     */
    private Integer pageSize;
    /**
     * 名称，模糊搜索 若包含中文，最大长度指不超过按照指定编码的字节长度，即getBytes(“utf-8”).length 最大长度：32
     */
    private String name;
    /**
     * 是否包含下级区域，注： 1、参数containSubRegion和regionIndexCodes必须同时存在，且regionIndexCodes只能指定一个区域编码；
     * 2、参数containSubRegion和exactCondition不能同时存在
     */
    private Boolean containSubRegion;
    /**
     * 权限码集合，详见[附录A.3 资源类型/资源权限码]@[软件产品-综合安防管理平台-附录-附录A 数据字典#附录A.3 资源类型/资源权限码]
     */
    private String[] authCodes;
    /**
     * 指定字段条件精确查询; Map<String, String[]>，资源编号可以通过资源类型对应的对象去获取，
     * 如：根据【分页获取监控点资源】接口获取返回参数cameraIndexCode字段的值，此处resourceIndexCodes可填多个cameraIndexCode的值，
     * 即 “exactCondition”: { “resourceIndexCodes”: [“aaa”, “bbb”]} 注：参数containSubRegion和exactCondition不能同时存在
     */
    private Object exactCondition;
    /**
     * 查询字段值不为空的字段名
     */
    private String fieldValueNotNull;
}
