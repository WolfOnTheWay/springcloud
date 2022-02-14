package com.cy.pi.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author:LZJ
 * @Date:2021/11/25
 */
@Mapper
public interface SysUserRoleDao {
    int deleteObjectsByRoleId(Integer roleId);

    int insertObjects(
            @Param("userId")Integer userId,
            @Param("roleIds")Integer[]roleIds);
    List<Integer> findRoleIdsByUserId(
            Integer id);
}
