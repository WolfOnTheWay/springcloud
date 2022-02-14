package com.cy.pi.sys.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cy.pi.common.exception.ServiceException;
import com.cy.pi.common.vo.Node;
import com.cy.pi.sys.dao.SysDeptDao;
import com.cy.pi.sys.dao.SysUserDao;
import com.cy.pi.sys.entity.SysDept;
import com.cy.pi.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2021/11/25 16:58
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptDao sysDeptDao;

    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public List<Map<String, Object>> findObjects() {
        return sysDeptDao.findObjects();
    }

    @Override
    public int deleteObject(Integer id) {
        //1.合法性验证
        if(id==null||id<=0)
            throw new ServiceException("数据不合法,id="+id);
        //2.执行删除操作
        //2.1判定此id对应的菜单是否有子元素
        int childCount=sysDeptDao.getChildCount(id);
        if(childCount>0)
            throw new ServiceException("此元素有子元素，不允许删除");
        //2.2判定此部门是否有用户
        int userCount=sysUserDao.getUserCountByDeptId(id);
        if(userCount>0)
            throw new ServiceException("此部门有员工，不允许对部门进行删除");
        //2.2判定此部门是否已经被用户使用,假如有则拒绝删除
        //2.3执行删除操作
        int rows=sysDeptDao.deleteObject(id);
        if(rows==0)
            throw new ServiceException("此信息可能已经不存在");
        return rows;
    }

    @Override
    public List<Node> findZtreeDeptNodes() {
        return sysDeptDao.findZtreeDeptNodes();
    }

    @Override
    public int saveObject(SysDept entity) {
        //1.合法验证
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("部门名不能为空");
        int rows;
        //2.保存数据
        try{
            rows=sysDeptDao.insertObject(entity);
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException("保存失败");
        }
        //3.返回数据
        return rows;
    }

    @Override
    public int updateObject(SysDept entity) {
        //1.合法验证
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new ServiceException("部门名不能为空");

        //2.更新数据
        int rows=sysDeptDao.updateObject(entity);
        if(rows==0)
            throw new ServiceException("记录可能已经不存在");
        //3.返回数据
        return rows;
    }
}

