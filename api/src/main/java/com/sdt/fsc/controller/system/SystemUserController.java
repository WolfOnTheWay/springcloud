package com.sdt.fsc.controller.system;


import com.sdt.fsc.common.BusiExpMsgEnum;
import com.sdt.fsc.entity.system.SystemUser;
import com.sdt.fsc.entity.system.SystemUserAuthVO;
import com.sdt.fsc.entity.system.SystemUserLoginDTO;
import com.sdt.fsc.entity.system.SystemUserParam;
import com.sdt.fsc.exception.GeneralFailException;
import com.sdt.fsc.service.contract.system.ISystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/system")
@Api(description = "用户登录、登出")
public class SystemUserController {
    @Resource
    ISystemUserService systemUserService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    SystemUserAuthVO login(
            @RequestBody SystemUserLoginDTO systemUserLoginDTO,
            HttpServletRequest request
    ) throws Exception {
        return systemUserService.shiroLogin(systemUserLoginDTO,request);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "用户登出")
    Boolean logout(
    ) throws Exception {
        return systemUserService.logout();
    }

    @GetMapping("/unauth")
    @ApiOperation(value = "未鉴权回调")
    void unanth() throws Exception{
        throw new GeneralFailException(BusiExpMsgEnum.UNAUTH_ERROR.getCode(),
                BusiExpMsgEnum.UNAUTH_ERROR.getMessage());
    }

    @PostMapping("/check")
    @ApiOperation(value = "校验用户名密码")
    Boolean checkLogin(
            @RequestBody SystemUserLoginDTO systemUserLoginDTO
    )throws Exception{
        return systemUserService.checkLogin(systemUserLoginDTO);
    }
    @PostMapping("addUser")
    @ApiOperation("新增用户")
    public boolean addUser(@RequestBody SystemUserParam systemUserParam){
        return systemUserService.addUser(systemUserParam);
    }
}
