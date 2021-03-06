package com.jt.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/5 15:16
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public EasyUITable findItemByPage(Integer page, Integer rows) {
        //1.获取记录总数
        int total = itemMapper.selectCount(null);
        //2.进行分页查询
        int start = (page - 1) * rows;
        List<Item> itemList =
                itemMapper.findItemByPage(start,rows);
        return new EasyUITable(total, itemList);
    }

    @Override
    public void deleteItmes(Long[] ids) {
        //数组转化为集合
        List<Long> list = Arrays.asList(ids);
        itemMapper.deleteBatchIds(list);
        itemDescMapper.deleteBatchIds(list);
    }

    @Override
    public void saveItem(Item item, ItemDesc itemDesc) {
        item.setStatus(1);//上架
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        itemMapper.insert(item); //入库商品
        //Mybatis-plus在新增数据时,会自动回传主键信息.
        //保证主键有值.
        //入库商品描述信息
        itemDesc.setItemId(item.getId());
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getCreated());
        itemDescMapper.insert(itemDesc);
    }

    @Override
    public void updateItem(Item item, ItemDesc itemDesc) {
        item.setUpdated(new Date());
        itemMapper.updateById(item);
        itemDesc.setUpdated(item.getUpdated());
        itemDesc.setItemId(item.getId());
        itemDescMapper.updateById(itemDesc);
    }


    @Override
    public void updateItemStatus(int status, Long[] ids) {
        //方式1:循环
        /*
         * for (Long id : ids) { Item item = new Item(); item.setId(id);
         * item.setStatus(status); item.setUpdated(new Date()); //根据对象中不为null的属性进行set操作
         * //id当做where条件 itemMapper.updateById(item); }
         */

        //2.利用批量更新
        //sql:update set status=1/2,updated = now()
        //where id in (id,id,id)
        List<Long> idList = Arrays.asList(ids);
        Item item = new Item();
        item.setStatus(status)
                .setUpdated(new Date());
        UpdateWrapper<Item> updateWrapper = new UpdateWrapper<Item>();
        updateWrapper.in("id", idList);
        itemMapper.update(item, updateWrapper);
    }


    @Override
    public ItemDesc findItemDescById(Long id) {
        ItemDesc itemDesc = itemDescMapper.selectById(id);
        return itemDesc;
    }

    @Override
    public Item findItemById(Long id) {
        return itemMapper.selectById(id);
    }
}
