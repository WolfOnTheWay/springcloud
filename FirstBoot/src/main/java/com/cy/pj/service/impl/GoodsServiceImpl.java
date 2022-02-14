package com.cy.pj.service.impl;

import com.cy.pj.dao.GoodsDao;
import com.cy.pj.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public int deleteObjects(Integer... ids) {
        return goodsDao.deleteObjects(ids);
    }
}
