package com.jt.service.impl;

import com.jt.anno.Cache_Find;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.util.HttpClientService;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/20 14:50
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private HttpClientService httpClient;

    @Override  //SDK.jar 调用API
    @Cache_Find//实现缓存处理
    public Item findItemById(Long id) {
        String url = "http://manage.jt.com/web/item/findItemById/"+id;
        String itemJSON = httpClient.doGet(url);
        return ObjectMapperUtil.toObject(itemJSON, Item.class);
    }

    @Override
    @Cache_Find//实现缓存处理
    public ItemDesc findItemDescById(Long id) {
        String url = "http://manage.jt.com/web/item/findItemDescById/"+id;
        String itemDescJSON = httpClient.doGet(url);
        return ObjectMapperUtil.toObject(itemDescJSON, ItemDesc.class);
    }

}
