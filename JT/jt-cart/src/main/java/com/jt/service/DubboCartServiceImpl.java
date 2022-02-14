package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/30 19:55
 */
@Service
public class DubboCartServiceImpl implements DubboCartService{
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> findCartByUserid(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        QueryWrapper<Cart> wrapper = new QueryWrapper<>(cart);
//        wrapper.eq("user_id",userId);
        List<Cart> list= cartMapper.selectList(wrapper);
        System.out.println("数据库查询购物车清单");
        return list;
    }

    @Override
    public void updateCartNum(Cart cart) {
        Cart cartTemp = new Cart();
        cart.setNum(cart.getNum()).setUpdated(new Date());
        UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("item_id",cart.getItemId()).eq("user_id",cart.getUserId());
        cartMapper.update(cartTemp,updateWrapper);
    }

    /*
    * 要求对象中值不为空的参数当作查询条件
    * */
    @Override
    public void deleteCart(Cart cart) {
        QueryWrapper<Cart> queryMapper = new QueryWrapper<>(cart);
//        queryMapper.eq("user_id",cart.getUserId()).eq("item_id",cart.getItemId());
        cartMapper.delete(queryMapper);
    }

    @Override
    public void saveCart(Cart cart) {
        //如果有数据则做数据的修改，没有则新增
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cart.getUserId()).eq("item_id",cart.getItemId());
        Cart cartDB = cartMapper.selectOne(queryWrapper);
        if (cartDB==null){
            //没有则新增
            cart.setCreated(new Date());
            cart.setUpdated(new Date());
            cartMapper.insert(cart);
        }
        else {
            cartDB.setNum(cart.getNum()+cartDB.getNum());
            cartDB.setUpdated(new Date());
            //主键充当where条件，其他属性充当要修改的数据
            cartMapper.updateById(cartDB);
        }
    }
}
