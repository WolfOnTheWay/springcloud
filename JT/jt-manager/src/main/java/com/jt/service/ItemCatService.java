package com.jt.service;

import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;

import java.util.List;

public interface ItemCatService {
    ItemCat findItemCatNameById(Long itemCatId);

    List<EasyUITree> findItemCatAll(Long parentId);

    List<EasyUITree> findItemCatCache(Long parentId);
}
