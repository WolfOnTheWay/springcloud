package com.cy.pi.sys.dao;

import com.cy.pi.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//告诉mabits此接口的实现类由底层创建
public interface SysLogDao {
    int getRowCount(@Param("username") String username);


    List<SysLog> findPageObjects(@Param("username") String username,
                                 @Param("startIndex") Integer startIndex,
                                 @Param("pageSize") Integer pageSize);
    //基于id执行之日删除操作
    int deleteObjects(Integer...ids);
    int insertObject(SysLog entity);
}
