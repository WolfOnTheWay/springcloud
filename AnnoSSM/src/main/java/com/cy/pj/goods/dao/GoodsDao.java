package com.cy.pj.goods.dao;

import com.cy.pj.goods.pojo.Goods;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GoodsDao {
    @Select("select * from tb_goods")
    List<Goods> findGoods();
}
