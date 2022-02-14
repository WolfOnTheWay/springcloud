package com.cy.pi.sys.controller;

import com.cy.pi.common.vo.JsonResult;
import com.cy.pi.common.vo.PageObject;
import com.cy.pi.sys.entity.SysLog;
import com.cy.pi.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/log/")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(String username,Integer pageCurrent){
        PageObject<SysLog> po = sysLogService.findPageObjects(username,pageCurrent);
        return new JsonResult(po);
    }
    @PostMapping("doDeleteObjects")
    @ResponseBody
    public JsonResult doDeleteObjects(Integer...ids){
        sysLogService.deleteObjects(ids);
        return new JsonResult("delte ok");
    }
}
