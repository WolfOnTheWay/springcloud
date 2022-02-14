package com.tedu.sp01.pojo;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/6 16:00
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
}
