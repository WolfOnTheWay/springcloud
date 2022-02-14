package com.tedu.sp01.pojo;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/6 16:01
 */

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private User user;
    private List<Item> items;
}