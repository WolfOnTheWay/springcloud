package com.cy.pi.sys.dao;

import com.cy.pi.common.vo.Node;
import com.cy.pi.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDeptDao {
    List<Map<String,Object>> findObjects();
    //查询部门下面子员工
    int getChildCount(Integer id);
    //删除部门
    int deleteObject(Integer id);

    //查询节点关系
    List<Node> findZtreeDeptNodes();
    //新增部门
    int insertObject(SysDept entity);
    //修改部门
    int updateObject(SysDept entity);

    SysDept findById(Integer id);
}
