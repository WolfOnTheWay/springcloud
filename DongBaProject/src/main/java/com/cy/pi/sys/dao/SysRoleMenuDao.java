package com.cy.pi.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMenuDao {
    /**
     * @Description:基于菜单id删除关系数据
     * @Author:LZJ
     * @Date:2021/11/16
     */
    int deleteObjectByMenuId(Integer menuId);
    //删除角色
    int deleteObjectsByRoleId(Integer roleId);

    //添加角色
    int insertObjects(
            @Param("roleId")Integer roleId,
            @Param("menuIds")Integer[] menuIds);

    int findMenuIdsByRoleId(Integer id);

    List<Integer> findMenuIdsByRoleIds(
            @Param("roleIds")Integer[] roleIds);

}
