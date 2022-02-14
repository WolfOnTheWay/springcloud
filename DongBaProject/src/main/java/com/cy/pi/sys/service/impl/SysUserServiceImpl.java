package com.cy.pi.sys.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cy.pi.common.exception.ServiceException;
import com.cy.pi.common.vo.PageObject;
import com.cy.pi.common.vo.SysUserDeptVo;
import com.cy.pi.sys.dao.SysUserDao;
import com.cy.pi.sys.dao.SysUserRoleDao;
import com.cy.pi.sys.entity.SysUser;
import com.cy.pi.sys.service.SysUserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/26 16:40
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {

        //1.数据合法性验证
        if (pageCurrent == null || pageCurrent <= 0)
            throw new ServiceException("参数不合法");
//2.依据条件获取总记录数
        int rowCount = sysUserDao.getRowCount(username);
        if (rowCount == 0)
            throw new ServiceException("记录不存在");
        //3.计算startIndex的值
        int pageSize = 3;
        int startIndex = (pageCurrent - 1) * pageSize;
        //4.依据条件获取当前页数据
        List<SysUserDeptVo> records = sysUserDao.findPageObjects(
                username, startIndex, pageSize);
        //5.封装数据
        PageObject<SysUserDeptVo> pageObject = new PageObject<>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setRecords(records);
        pageObject.setPageCount((rowCount - 1) / pageSize + 1);
        return pageObject;
    }

    @Override
    public int validById(Integer id, Integer valid, String modifiedUser) {
        //1.合法性验证
        if(id==null||id<=0)
            throw new ServiceException("参数不合法,id="+id);
        if(valid!=1&&valid!=0)
            throw new ServiceException("参数不合法,valie="+valid);
        if(StringUtils.isEmpty(modifiedUser))
            throw new ServiceException("修改用户不能为空");
        //2.执行禁用或启用操作
        int rows=0;
        try{
            rows=sysUserDao.validById(id, valid, modifiedUser);
        }catch(Throwable e){
            e.printStackTrace();
            //报警,给维护人员发短信
            throw new ServiceException("底层正在维护");
        }
        //3.判定结果,并返回
        if(rows==0)
            throw new ServiceException("此记录可能已经不存在");
        return rows;
    }

    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
        //1.验证数据合法性
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getUsername()))
            throw new ServiceException("用户名不能为空");
        if(StringUtils.isEmpty(entity.getPassword()))
            throw new ServiceException("密码不能为空");
        if(roleIds==null || roleIds.length==0)
            throw new ServiceException("至少要为用户分配角色");

        //2.将数据写入数据库
        String salt= UUID.randomUUID().toString();
        entity.setSalt(salt);
        //加密(先了解,讲shiro时再说)
        SimpleHash sHash=
                new SimpleHash("MD5",entity.getPassword(), salt);
        entity.setPassword(sHash.toHex());

        int rows=sysUserDao.insertObject(entity);
        sysUserRoleDao.insertObjects(
                entity.getId(),
                roleIds);//"1,2,3,4";
        //3.返回结果
        return rows;
    }
}
