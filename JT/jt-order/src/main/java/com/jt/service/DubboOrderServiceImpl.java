package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/3 19:22
 */
@Service
public class DubboOrderServiceImpl implements DubboOrderService{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;

    @Override
    @Transactional
    public String saveOrder(Order order) {
        String uuid = UUID.randomUUID().toString()
                .replace("-", "");
        Date date = new Date();
        //1.入库订单
        order.setOrderId(uuid)
                .setStatus(1)
                .setCreated(date)
                .setUpdated(date);
        orderMapper.insert(order);
        System.out.println("订单数据入库成功!!!!");

        //2.入库订单物流
        OrderShipping shipping = order.getOrderShipping();
        shipping.setOrderId(uuid);
        shipping.setCreated(date);
        shipping.setUpdated(date);
        orderShippingMapper.insert(shipping);
        System.out.println("订单物流入库成功!!!!");

        //3.入库订单商品
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(uuid)
                    .setCreated(date)
                    .setUpdated(date);
            orderItemMapper.insert(orderItem);
        }
        System.out.println("订单商品入库成功!!!");
        return uuid;
    }

    @Override
    public Order findOrderById(String id) {
        Order order = orderMapper.selectById(id);
        OrderShipping orderShipping = orderShippingMapper.selectById(id);
        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<OrderItem>();
        queryWrapper.eq("order_id", id);
        List<OrderItem> orderItems = orderItemMapper.selectList(queryWrapper);
        order.setOrderShipping(orderShipping).setOrderItems(orderItems);
        return order;
    }
}
