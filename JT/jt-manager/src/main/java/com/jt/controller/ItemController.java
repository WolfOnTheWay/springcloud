package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/5 15:22
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    //展示商品信息
    @RequestMapping("/query")
    public EasyUITable findItemByPage(Integer page, Integer rows) {
        return itemService.findItemByPage(page,rows);
    }
    @RequestMapping("/delete")
    public SysResult deleteItems(Long[] ids) {

        itemService.deleteItmes(ids);
        return SysResult.success();
    }

    @RequestMapping("/save")
    public SysResult saveItem(Item item, ItemDesc itemDesc) {

        itemService.saveItem(item,itemDesc);
        return SysResult.success();
    }

    @RequestMapping("/update")
    public SysResult updateItem(Item item,ItemDesc itemDesc) {

        itemService.updateItem(item,itemDesc);
        return SysResult.success();
    }

    /**
     * 实现商品下架
     * localhost:8091/item/instock
     */
    @RequestMapping("/instock")
    public SysResult instock(Long[] ids) {
        int status = 2;
        itemService.updateItemStatus(status,ids);
        return SysResult.success();
    }

    @RequestMapping("/reshelf")
    public SysResult reshelf(Long[] ids) {
        int status = 1;
        itemService.updateItemStatus(status,ids);
        return SysResult.success();
    }

    @RequestMapping("/query/item/desc/{id}")
    public SysResult findItemDescById(@PathVariable Long id) {

        ItemDesc itemDesc = itemService.findItemDescById(id);
        return SysResult.success(itemDesc);
        //Sysresult.data=itemDesc数据
    }
}
