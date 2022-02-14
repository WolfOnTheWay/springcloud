package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/20 14:49
 */
@Controller
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * http://www.jt.com/items/562379.html
     * ${item.title }
     * item.jsp 中 237行
     * <div class="detail-content">
     ${itemDesc.itemDesc }
     </div>
     * @return
     */
    @RequestMapping("/{id}")
    public String findItemById(@PathVariable Long id, Model model) {
        Item item = itemService.findItemById(id);
        ItemDesc itemDesc = itemService.findItemDescById(id);
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", itemDesc);
        return "item";  //item.jsp
    }
}
