package com.cy.pi.sys.controller;

import com.cy.pi.common.vo.JsonResult;
import com.cy.pi.sys.entity.SysMenu;
import com.cy.pi.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/16 11:14
 */
@RestController
@RequestMapping("/menu/")
public class SysMenuController {
    @Autowired
    private SysMenuService service;
    @RequestMapping("doFindObjects")
    public JsonResult doFindObjects(){
        return new JsonResult(service.findObjects());
    }

    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        service.deleteObject(id);
        return new JsonResult("delete ok");
    }
    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult findZtreeMenuNodes()
    {
        return new JsonResult(service.findZtreeMenuNodes());
    }

    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysMenu entity){
        service.saveObject(entity);
        return new JsonResult("save ok");
    }

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysMenu entity){
        service.updateObject(entity);
        return new JsonResult("update ok");
    }

}
