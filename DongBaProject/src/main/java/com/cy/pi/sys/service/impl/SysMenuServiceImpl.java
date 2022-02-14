package com.cy.pi.sys.service.impl;

import com.cy.pi.common.exception.ServiceException;
import com.cy.pi.common.vo.Node;
import com.cy.pi.sys.dao.SysMenuDao;
import com.cy.pi.sys.dao.SysRoleMenuDao;
import com.cy.pi.sys.entity.SysMenu;
import com.cy.pi.sys.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 李志杰
 * @version 1.0
 * @description:菜单模块业务层
 * @date 2021/11/16 15:16
 */
@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> list = sysMenuDao.findObjects();
        if(list==null||list.size()==0){
            throw new ServiceException("没有对象对应的菜单信息");
        }
        return list;
    }

    @Override
    public int deleteObject(Integer id) {
       if(id ==null||id<=0){
           throw new IllegalArgumentException("请先进行选则!");
       }
       //子元素判断
       int count=sysMenuDao.getChildCount(id);
       if(count>0){
           log.info("子菜单未进行删除！");
           throw new ServiceException("请先删除菜单");
       }

       //菜单删除
       int rows = sysMenuDao.deleteObject(id);
       if(rows==0){
           throw new ServiceException("此菜单可能已经不存在");
       }
       //关联删除
       sysRoleMenuDao.deleteObjectByMenuId(id);
       return rows;
    }

    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }

    @Override
    public int saveObject(SysMenu entity) {
        if(entity==null){
            throw new ServiceException("保存的对象不能为空");
        }
        if(StringUtils.isEmpty(entity.getName())){
            throw new ServiceException("菜单名称不能为空");
        }
        int rows;
        try {
            rows = sysMenuDao.insertObject(entity);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("保存失败");
        }
        return rows;
    }

    @Override
    public int updateObject(SysMenu entity) {
        if(entity==null){
            throw  new ServiceException("保存的对象不能为空");
        }
        if(StringUtils.isEmpty(entity.getName())){
            throw new ServiceException("菜单名不能为空");
        }
        //update data
        int rows = sysMenuDao.updateObject(entity);
        if(rows==0){
            throw new ServiceException("记录可能不存在");
        }
        return rows;
    }
}
