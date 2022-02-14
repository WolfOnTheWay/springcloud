package com.sdt.fsc.service.impl.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdt.fsc.common.BusiExpMsgEnum;
import com.sdt.fsc.entity.system.*;
import com.sdt.fsc.exception.GeneralFailException;
import com.sdt.fsc.exception.LoginFailException;
import com.sdt.fsc.mapper.dm.SystemUserMapper;
import com.sdt.fsc.service.contract.system.ISystemUserService;
import com.sdt.fsc.util.system.DESUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Service
@Slf4j
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {



    @Override
    public Boolean checkLogin(SystemUserLoginDTO systemUserLoginDTO){
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_NAME",systemUserLoginDTO.getUsername());
    SystemUser systemUser = this.baseMapper.selectOne(queryWrapper);
    String password = systemUser.getPassword();
        if(!password.equals(new DESUtil().getEnc(systemUserLoginDTO.getPassword()))){
        throw new GeneralFailException(BusiExpMsgEnum.USER_PWD_ERROR.getCode(),BusiExpMsgEnum.USER_PWD_ERROR.getMessage());
    }
        return true;
}

    @Override
    @Transactional
    public boolean addUser(SystemUserParam systemUserParam) {
        SystemUser systemUser = new SystemUser();
        //新增之前先删除
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_NAME",systemUserParam.getUsername());
        SystemUser one = this.baseMapper.selectOne(queryWrapper);
        //表示已经有了
        if (one != null && new DESUtil().getDec(one.getPassword()).equals(systemUserParam.getPassword())){
            this.baseMapper.deleteById(one.getUserId());
            //添加
            systemUser.setUserId(UUID.randomUUID().toString());
            systemUser.setUserName(systemUserParam.getUsername());
            systemUser.setPassword(new DESUtil().getEnc(systemUserParam.getPassword())); //加密
            this.baseMapper.insert(systemUser);
        }else {
            //添加
            systemUser.setUserId(UUID.randomUUID().toString());
            systemUser.setUserName(systemUserParam.getUsername());
            systemUser.setPassword(new DESUtil().getEnc(systemUserParam.getPassword())); //加密
            this.baseMapper.insert(systemUser);
        }

        return true;
    }


    @Override
    public Boolean logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return true;
    }

    @Override
    public SystemUserAuthVO shiroLogin(SystemUserLoginDTO systemUserLoginDTO, HttpServletRequest request) {

        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_NAME",systemUserLoginDTO.getUsername());
        SystemUser systemUser = this.baseMapper.selectOne(queryWrapper);
        String password = systemUser.getPassword();
        //需要做用户是否被删除判断
        if(password==null){
            throw new GeneralFailException(BusiExpMsgEnum.USER_NOT_FOUND.getCode(),BusiExpMsgEnum.USER_NOT_FOUND.getMessage());
        }

        UsernamePasswordToken token = new UsernamePasswordToken(systemUserLoginDTO.getUsername(),systemUserLoginDTO.getPassword());
        try {
            SecurityUtils.getSubject().login(token);
        }catch (LoginFailException e){
            e.printStackTrace();
        }catch (Exception e){
            throw new GeneralFailException(BusiExpMsgEnum.USER_PWD_ERROR.getCode(),BusiExpMsgEnum.USER_PWD_ERROR.getMessage());
        }
        SystemUserAuthVO systemUserAuthVO = new SystemUserAuthVO();
        BeanUtils.copyProperties(systemUser,systemUserAuthVO);
        systemUserAuthVO.setToken(SecurityUtils.getSubject().getSession().getId());
        return systemUserAuthVO;
    }


}
