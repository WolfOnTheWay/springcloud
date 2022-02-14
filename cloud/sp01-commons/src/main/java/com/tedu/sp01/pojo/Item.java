package com.tedu.sp01.pojo;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/6 16:01
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Integer id;
    private String name;
    private Integer number;
}