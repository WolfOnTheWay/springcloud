package com.cy.pi.sys.dao;

import com.cy.pi.common.vo.Node;
import com.cy.pi.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuDao {
    //查找所有数据
    List<Map<String,Object>> findObjects();
    //查询子节点个数
    int getChildCount(Integer id);
    //根据id删除菜单
    int deleteObject(Integer id);
    //基于请求获取数据库对应菜单表中的所有菜单信息
    List<Node> findZtreeMenuNodes();
    //插入菜单
    int insertObject(SysMenu entity);
    //更新菜单
    int updateObject(SysMenu entity);

    List<String> findPermissions(
            @Param("menuIds")
                    Integer[] menuIds);
}
