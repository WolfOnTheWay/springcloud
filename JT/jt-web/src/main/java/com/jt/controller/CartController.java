package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.User;
import com.jt.service.DubboCartService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/30 20:07
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Reference(check = false)
    private DubboCartService service;
    //要求回显购物车中的信息
    @RequestMapping("/show")
    public String show(Model model){
        //User user = (User)request.getAttribute("JT_USER");
        //Long userId = user.getId();//暂定用于id为7，后续动态获取
        Long userId = UserThreadLocal.get().getId();
        List<Cart> cartList = service.findCartByUserid(userId);
        model.addAttribute("cartList",cartList);
        return "cart";
    }

    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public SysResult updateCartNum(Cart cart){
        Long userId = 7L;
        cart.setUserId(userId);
        service.updateCartNum(cart);
        return SysResult.success();
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCart(Cart cart){
        Long userId = UserThreadLocal.get().getId();
        cart.setUserId(userId);
        service.deleteCart(cart);
        return "redirect:/cart/show.html";
    }

    @RequestMapping("/add/{itemId}")
    public String saveCart(Cart cart){
        Long userId = UserThreadLocal.get().getId();
        cart.setUserId(userId);
        service.saveCart(cart);
        return "redirect:/cart/show.html";
    }
}
