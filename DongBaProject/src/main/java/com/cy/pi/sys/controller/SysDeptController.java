package com.cy.pi.sys.controller;

import com.cy.pi.common.vo.JsonResult;
import com.cy.pi.sys.entity.SysDept;
import com.cy.pi.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/25 17:00
 */
@RestController
@RequestMapping("/dept/")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;
    @RequestMapping("doFindObjects")
    public JsonResult doFindObjects(){
        return new JsonResult(sysDeptService.findObjects());
    }

    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysDeptService.deleteObject(id);
        return new JsonResult("delete OK");
    }

    @RequestMapping("doFindZTreeNodes")
    public JsonResult doFindZtreeDeptNodes(){
        return new JsonResult(
                sysDeptService.findZtreeDeptNodes());
    }

    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysDept entity){
        sysDeptService.saveObject(entity);
        return new JsonResult("save ok");
    }

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysDept entity){
        sysDeptService.updateObject(entity);
        return new JsonResult("update ok");
    }

}
