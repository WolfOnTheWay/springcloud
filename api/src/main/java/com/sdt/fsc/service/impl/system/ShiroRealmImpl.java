package com.sdt.fsc.service.impl.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sdt.fsc.entity.system.SystemUser;
import com.sdt.fsc.mapper.dm.SystemUserMapper;
import com.sdt.fsc.util.system.DESUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @author wangtiexiang
 * @Description
 * @Datetime 2020/10/13 12:29
 */

public class ShiroRealmImpl extends AuthorizingRealm {
    //[bu ji] will be managed by Spring
    @Resource
    private SystemUserMapper systemUserMapper;

    /**
     * 用户认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 加这一步的目的是在Post请求的时候会先进认证，然后再到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        // 获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("USER_NAME",name);
        SystemUser systemUser = systemUserMapper.selectOne(queryWrapper);

        if (systemUser.getPassword() == null) {
            // 这里返回后会报出对应异常
            return null;
        } else {
            // 这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,
                    new DESUtil().getDec(systemUser.getPassword()), getName());
            return simpleAuthenticationInfo;
        }
    }

    /**
     * 角色权限和对应权限添加
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取登录用户名
        String nickName = (String) principalCollection.getPrimaryPrincipal();
        // 查询用户名称
//        int userId = systemUserMapper.findIdByNickName(nickName);
//        // 添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        List<String> authByNickName = systemUserAuthMapper.findAuthCodeByNickName(nickName);
//        Collection collection = authByNickName;
//        simpleAuthorizationInfo.addStringPermissions(collection);
//
        return simpleAuthorizationInfo;
    }

}
