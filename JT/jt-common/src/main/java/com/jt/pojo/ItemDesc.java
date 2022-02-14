package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/7 14:53
 */
@Data
@Accessors(chain = true)
@TableName("tb_item_desc")
public class ItemDesc extends BasePojo{
    @TableId    //标识主键
    private Long itemId;		//商品Id号
    private String itemDesc;	//商品详情
}
