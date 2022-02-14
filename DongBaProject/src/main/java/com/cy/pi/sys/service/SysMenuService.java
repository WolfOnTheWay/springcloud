package com.cy.pi.sys.service;

import com.cy.pi.common.vo.Node;
import com.cy.pi.sys.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService {
    public List<Map<String,Object>> findObjects();
    int deleteObject(Integer id);
    //列出所有菜单的节点信息
    List<Node> findZtreeMenuNodes();

    //插入新的菜单
    int saveObject(SysMenu entity);
    //更新菜单
    public int updateObject(SysMenu entity);
}
