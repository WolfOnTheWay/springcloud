package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import java.util.List;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/3 17:58
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference(check = false)
    private DubboCartService dubboCartService;

    @Reference(check = false)
    private DubboOrderService orderService;
    //跳转到订单的确认页面
    @RequestMapping("/create")
    public String create(Model model){
        //前提是拦截器必须生效，必须编辑配置文件
        Long userId = UserThreadLocal.get().getId();
        List<Cart> list = dubboCartService.findCartByUserid(userId);
        model.addAttribute("carts",list);
        return "order-cart";
    }

    @RequestMapping("/submit")
    @ResponseBody
    public SysResult saveOrder(Order order){
        String orderId = orderService.saveOrder(order);
        if(StringUtils.isEmpty(orderId)) {
            return SysResult.fail();
        }
        return SysResult.success(orderId);
    }

    @RequestMapping("/success")
    public String findOrderById(String id, Model model){
        Order order = orderService.findOrderById(id);
        model.addAttribute("order",order);
        return "success";
    }
}
