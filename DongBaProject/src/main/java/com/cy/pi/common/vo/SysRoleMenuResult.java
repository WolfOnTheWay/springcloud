package com.cy.pi.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/25 16:20
 */
@Data
public class SysRoleMenuResult implements Serializable {
    private static final long serialVersionUID = -8627186128641558882L;
    /**角色id*/
    private Integer id;
    /**角色名称*/
    private String name;
    /**角色备注*/
    private String note;
    /**角色对应的菜单id*/
    private List<Integer> menuIds;
}
