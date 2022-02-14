package com.cy.pi.sys.service.relam;

import com.alibaba.druid.util.StringUtils;
import com.cy.pi.sys.dao.SysMenuDao;
import com.cy.pi.sys.dao.SysRoleMenuDao;
import com.cy.pi.sys.dao.SysUserDao;
import com.cy.pi.sys.dao.SysUserRoleDao;
import com.cy.pi.sys.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/12/21 14:42
 */
//AuthorizingRealm完成认证和授权

@Service
public class ShiroUserRealm extends AuthorizingRealm {


    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysMenuDao sysMenuDao;

    //获取授权信息并封装返回
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户id
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        //根据用户id获取对应的角色id并判断
        List<Integer> roleIds =
        sysUserRoleDao.findRoleIdsByUserId(user.getId());
        if(roleIds == null || roleIds.size()==0){
            throw new AuthenticationException();
        }
        //基于角色id获取对应的菜单id并判断
        Integer[] array = {};
        List<Integer> menuIds =
        sysRoleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
        if(menuIds == null || menuIds.size()==0){
            throw new AuthenticationException();
        }
        //基于菜单id回去对应的权限标识并判断
        List<String> permissions =
        sysMenuDao.findPermissions(menuIds.toArray(array));
        if(permissions == null || permissions.size()==0){
            throw new AuthenticationException();
        }
        //封装结果并返回
        Set<String> stringPermissions = new HashSet<>();
        for(String permission:permissions){
            if(!StringUtils.isEmpty(permission)){
                stringPermissions.add(permission);
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(stringPermissions);
        return info;//返回给sm
    }

    /*设定加密算法对密码进行加密*/
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
            //构建凭证匹配对象
            HashedCredentialsMatcher cMatcher=
                    new HashedCredentialsMatcher();
            //设置加密算法
            cMatcher.setHashAlgorithmName("MD5");
            //设置加密次数(默认就是一次)
//            cMatcher.setHashIterations(1);
            super.setCredentialsMatcher(cMatcher);
    }

    //获取用户认证信息并封装返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        //获取用户提交的身份信心
        String username = upToken.getUsername();
        //基于身份信息查询数据库用户
        SysUser user = sysUserDao.findUserByUserName(username);
        //判定用户是否存在
        if(user ==null)
        {
            throw new UnknownAccountException("账户不存在");
        }
        //判定用户是否被禁用
        if(user.getValid()==0)
        {
            throw new LockedAccountException("账户被禁用");
        }
        //封装用户信息并返回
        ByteSource slat = ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo result = new  SimpleAuthenticationInfo(
                 user//身份
                , user.getPassword() //加密的密码
                , slat//盐值，用于加密算法
                , this.getName());
        return result;//返回给SecurityManager
    }
}
