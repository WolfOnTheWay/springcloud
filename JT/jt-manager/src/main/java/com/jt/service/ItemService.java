package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {
    public EasyUITable findItemByPage(Integer page, Integer rows);

    void deleteItmes(Long[] ids);

    void saveItem(Item item, ItemDesc itemDesc);

    void updateItem(Item item, ItemDesc itemDesc);

    void updateItemStatus(int status, Long[] ids);

    ItemDesc findItemDescById(Long id);

    Item findItemById(Long id);
}
