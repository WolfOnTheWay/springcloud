package com.cy.pi.sys.controller;

import com.cy.pi.common.vo.JsonResult;
import com.cy.pi.common.vo.PageObject;
import com.cy.pi.common.vo.SysUserDeptVo;
import com.cy.pi.sys.entity.SysUser;
import com.cy.pi.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/26 16:42
 */
@RestController
@RequestMapping("/user/")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(
            String username,Integer pageCurrent){
        PageObject<SysUserDeptVo> pageObject=
                sysUserService.findPageObjects(username,
                        pageCurrent);
        return new JsonResult(pageObject);
    }
    @RequestMapping("doValidById")
    public JsonResult doValidById(
            Integer id,
            Integer valid){
        SysUser user = (SysUser)
        SecurityUtils.getSubject().getPrincipal();
        sysUserService.validById(
                id,
                valid,
                user.getUsername());//"admin"用户将来是登陆用户
        return new JsonResult("update ok");
    }

    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(
            SysUser entity,
            Integer[] roleIds){
        sysUserService.saveObject(entity,roleIds);
        return new JsonResult("save ok");
    }

    @RequestMapping("doLogin")
    public JsonResult doLogin(String username,String password){
        //1.获取subject对象(主体对象，负责提交用户信息)
        Subject subject = SecurityUtils.getSubject();

        //提交用户信息(提交给SecutityManager)
        UsernamePasswordToken tocken = new UsernamePasswordToken(username,password);
        //记住我
//        tocken.setRememberMe();
        subject.login(tocken);

        return new JsonResult("登录成功");
    }
}
