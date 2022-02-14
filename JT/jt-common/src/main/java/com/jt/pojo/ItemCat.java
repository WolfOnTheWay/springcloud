package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/7 14:23
 */
@Data
@Accessors(chain = true)
@TableName("tb_item_cat")
public class ItemCat extends BasePojo{
    private static final long serialVersionUID = 5006550716849464495L;
    @TableId(type = IdType.AUTO)
    private Long id;		//id号
    private Long parentId;	//父级Id号
    private String name;	//节点名称
    private Integer status;	//状态
    private Integer sortOrder;	//排序号
    private Boolean isParent;   //是否为父级
}
