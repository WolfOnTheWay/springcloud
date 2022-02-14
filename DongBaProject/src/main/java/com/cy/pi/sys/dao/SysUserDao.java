package com.cy.pi.sys.dao;

import com.cy.pi.common.vo.SysUserDeptVo;
import com.cy.pi.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserDao {
    int getUserCountByDeptId(Integer id);

    List<SysUserDeptVo> findPageObjects(
            @Param("username") String username,
            @Param("startIndex")Integer startIndex,
            @Param("pageSize")Integer pageSize);

    int getRowCount(@Param("username") String username);

    //禁用与启用
    int validById(
            @Param("id")Integer id,
            @Param("valid")Integer valid,
            @Param("modifiedUser")String modifiedUser);
    int insertObject(SysUser entity);

    SysUser findUserByUserName(String username);

}
