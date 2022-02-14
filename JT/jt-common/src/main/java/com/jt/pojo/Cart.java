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
 * @date 2022/1/30 16:01
 */
@TableName("tb_cart")
@Data
@Accessors(chain = true)
public class Cart extends BasePojo{
    private static final long serialVersionUID = 8209689274704358116L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long itemId;
    private String itemTitle;
    private String itemImage;
    private Long itemPrice;
    private Integer num;
}
