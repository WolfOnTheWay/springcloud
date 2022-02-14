package com.cy.pi.sys.service;

import com.cy.pi.common.vo.CheckBox;
import com.cy.pi.common.vo.PageObject;
import com.cy.pi.common.vo.SysRoleMenuResult;
import com.cy.pi.sys.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
    int deleteObject(Integer id);
    //添加角色
    int saveObject(SysRole entity,Integer[] menuIds);

    SysRoleMenuResult findObjectById(Integer id);
    //角色修改
    int updateObject(SysRole entity,Integer[] menuIds);
    List<CheckBox> findObjects();
}
