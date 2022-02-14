package com.jt.controller;

import com.jt.anno.Cache_Find;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/7 14:31
 */
@RestController
@RequestMapping("/item/cat/")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /*根据商品信息查询商品类别*/
    @RequestMapping("queryItemName")
    public String findItemNameById(Long itemCatId){
        ItemCat itemCat =
                itemCatService.findItemCatNameById(itemCatId);
        return itemCat.getName();
    }

    //商品分类树形结构展示
    @RequestMapping("/list")
    @Cache_Find
    public List<EasyUITree> findItemCatAll(
            @RequestParam(name="id",
                    defaultValue = "0",
                    required = true)
                    Long parentId){
        //查询数据库!!!
        return itemCatService.findItemCatAll(parentId);
//        return itemCatService.findItemCatCache(parentId);
    }
}
