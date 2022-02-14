package com.cy.pi.sys.service;

import com.cy.pi.common.vo.PageObject;
import com.cy.pi.common.vo.SysUserDeptVo;
import com.cy.pi.sys.entity.SysUser;

public interface SysUserService {
    PageObject<SysUserDeptVo> findPageObjects(
            String username,
            Integer pageCurrent);

    int validById(Integer id,
                  Integer valid,
                  String modifiedUser);
    int saveObject(SysUser entity, Integer[] roleIds);
}
