package com.cy.pi.sys.controller;

import com.cy.pi.common.anno.RequestLog;
import com.cy.pi.common.vo.JsonResult;
import com.cy.pi.common.vo.PageObject;
import com.cy.pi.sys.entity.SysRole;
import com.cy.pi.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/24 10:18
 */
@RestController
@RequestMapping("/role/")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;


    @RequestMapping("doFindPageObjects")
    @RequestLog("访问角色")
    public JsonResult doFindPageObjects(
            String name,Integer pageCurrent){
        PageObject<SysRole> pageObject=
                sysRoleService.findPageObjects(name,pageCurrent);
        return new JsonResult(pageObject);
    }

    @RequestMapping("doDeleteObject")
    @RequestLog("删除角色")
    public JsonResult doDeleteObject(Integer id){
        sysRoleService.deleteObject(id);
        return new JsonResult("delete ok");
    }
    @RequestLog("添加角色")
    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(
            SysRole entity,Integer[] menuIds){
        sysRoleService.saveObject(entity,menuIds);
        return new JsonResult("save ok");
    }

    @RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Integer id){
        return new JsonResult(sysRoleService.findObjectById(id));
    }

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysRole entity,
                                     Integer[] menuIds){
        sysRoleService.updateObject(entity,menuIds);
        return new JsonResult("update ok");
    }
    @RequestMapping("doFindRoles")
    public JsonResult doFindObjects(){
        return new JsonResult(sysRoleService.findObjects());
    }

}
