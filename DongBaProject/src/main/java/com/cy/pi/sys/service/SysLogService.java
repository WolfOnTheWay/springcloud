package com.cy.pi.sys.service;

import com.cy.pi.common.vo.PageObject;
import com.cy.pi.sys.entity.SysLog;

public interface SysLogService {
    PageObject<SysLog> findPageObjects(String username,Integer pageCurrent);
    int deleteObjects(Integer...ids);
    void insertObject(SysLog entity);
}
