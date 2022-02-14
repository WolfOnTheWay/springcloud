package com.cy.pi.sys.service.impl;

import com.cy.pi.common.exception.ServiceException;
import com.cy.pi.common.vo.PageObject;
import com.cy.pi.sys.dao.SysLogDao;
import com.cy.pi.sys.entity.SysLog;
import com.cy.pi.sys.service.SysLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
        //验证当前页码值是否合法
        if(pageCurrent==null || pageCurrent <1)
        {
            throw new IllegalArgumentException("当前页码值不正常");
        }
        //基于用户名查询总记录数并进行校验
        int rowCount = sysLogDao.getRowCount(username);
        if(rowCount==0)
        {
            throw new ServiceException("记录不存在");
        }
        //查询当前页记录
        Integer pageSize = 18;
        Integer startIndex = (pageCurrent -1)*pageSize;
        List<SysLog> records = sysLogDao.findPageObjects(username, startIndex, pageSize);
        //封装查询结果并返回
        return  new PageObject<SysLog>(rowCount,records,pageCurrent,pageSize);
    }


    /**
     * @Descrition:RequiresPermissions告诉底层系统访问此方法需要什么样的权限
     * @Author:李志杰
     * @Date:2022/1/3
    * @Param:a
     */
    @RequiresPermissions("sys:log:delete")
    @Override
    public int deleteObjects(Integer...ids) {
        if(ids==null||ids.length==0){
            throw new IllegalArgumentException("必须传入id");
        }
        int rows = sysLogDao.deleteObjects(ids);
        if(rows==0)
        {
            throw new ServiceException("记录已经被删除");
        }
        return rows;
    }

    @Override
    @Transactional
    @Async("threadPoolExecutor")
    public void insertObject(SysLog entity) {
        System.out.println("日志存储");
        sysLogDao.insertObject(entity);
    }
}
