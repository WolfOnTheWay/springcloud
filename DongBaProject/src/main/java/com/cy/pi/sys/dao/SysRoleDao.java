package com.cy.pi.sys.dao;

import com.cy.pi.common.vo.CheckBox;
import com.cy.pi.common.vo.SysRoleMenuResult;
import com.cy.pi.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysRoleDao {
    List<SysRole> findPageObjects(
            @Param("name") String name,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);
    //查询总记录数
    int getRowCount(@Param("name") String name);
    //删除角色
    int deleteObject(Integer id);
    //添加角色
    int insertObject(SysRole entity);
    //基于角色来查询
    SysRoleMenuResult findObjectById(Integer id);
    //角色修改
    int updateObject(SysRole entity);

    List<CheckBox> findObjects();

}
