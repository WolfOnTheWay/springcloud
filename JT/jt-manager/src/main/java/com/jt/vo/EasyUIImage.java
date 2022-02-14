package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/10 10:53
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUIImage {
    private Integer error = 0; //默认正常  1表示失败
    private String url; //网络访问的虚拟地址
    private Integer width;
    private Integer height;
}
