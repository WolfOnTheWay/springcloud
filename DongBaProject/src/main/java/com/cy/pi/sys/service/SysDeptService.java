package com.cy.pi.sys.service;

import com.cy.pi.common.vo.Node;
import com.cy.pi.sys.entity.SysDept;

import java.util.List;
import java.util.Map;

public interface SysDeptService {
    List<Map<String,Object>> findObjects();
    int deleteObject(Integer id);
    List<Node> findZtreeDeptNodes();
    int saveObject(SysDept entity);
    int updateObject(SysDept entity);
}
