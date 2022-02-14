package com.tedu.sp09.service;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/2/9 15:51
 */

import java.util.List;

import org.springframework.stereotype.Component;
import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;

@Component
public class ItemFeignServiceFB implements ItemFeignService {
    @Override
    public JsonResult<List<Item>> getItems(String orderId) {
        return JsonResult.err("无法获取订单商品列表");
    }

    @Override
    public JsonResult decreaseNumber(List<Item> items) {
        return JsonResult.err("无法修改商品库存");
    }
}
