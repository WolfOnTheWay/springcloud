package com.sdt.fsc.service.contract.system;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sdt.fsc.entity.system.SystemUser;
import com.sdt.fsc.entity.system.SystemUserAuthVO;
import com.sdt.fsc.entity.system.SystemUserLoginDTO;
import com.sdt.fsc.entity.system.SystemUserParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aubin
 * @since 2021-07-05
 */
public interface ISystemUserService extends IService<SystemUser> {

    SystemUserAuthVO shiroLogin(SystemUserLoginDTO systemUserLoginDTO, HttpServletRequest request);

    Boolean logout();

    Boolean checkLogin(SystemUserLoginDTO systemUserLoginDTO);

    boolean addUser(SystemUserParam systemUserParam);
}
