package com.jt.service;

import com.jt.pojo.Cart;

import java.util.List;

public interface DubboCartService {
    List<Cart> findCartByUserid(Long userId);

    void updateCartNum(Cart cart);

    void deleteCart(Cart cart);

    void saveCart(Cart cart);
}
