package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/5 15:12
 */
@Repository
public interface ItemMapper extends BaseMapper<Item> {
    List<Item> findItemByPage(@Param("start") Integer start, @Param("rows") Integer rows);
}
