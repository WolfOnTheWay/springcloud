package com.jt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 李志杰
 * @version 1.0
 * @description:TODO
 * @date 2022/1/7 14:27
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

//    @Autowired
//    private Jedis jedis;
//    @Autowired
//    private ShardedJedis jedis;
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public ItemCat findItemCatNameById(Long itemCatId) {
        return itemCatMapper.selectById(itemCatId);
    }

    private List<ItemCat> findItemCatList(Long parentId) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<ItemCat>();
        queryWrapper.eq("parent_id", parentId);
        return itemCatMapper.selectList(queryWrapper);
    }

    @Override
    public List<EasyUITree> findItemCatAll(Long parentId) {
        //0.定义返回值数据
        List<EasyUITree> treeList = new ArrayList<EasyUITree>();

        //1.获取数据库数据!!
        List<ItemCat> catList = findItemCatList(parentId);

        //2.实现数据转化  一级/二级 closed 三级 open
        for (ItemCat itemCat : catList) {
            EasyUITree easyUITree = new EasyUITree();
            String state =
                    itemCat.getIsParent()?"closed":"open";
            easyUITree.setId(itemCat.getId())
                    .setText(itemCat.getName())
                    .setState(state);
            treeList.add(easyUITree);
        }
        return treeList;
    }

    @Override
    public List<EasyUITree> findItemCatCache(Long parentId) {
        List<EasyUITree> treeList = new ArrayList<>();
        //根据key查询缓存
        String key = "ITEM_CAT_" + parentId;
        String result = jedisCluster.get(key);
        //检查数据结果
        if (StringUtils.isEmpty(result)) {
            treeList = findItemCatAll(parentId);
            String dataJson = ObjectMapperUtil.toJSON(treeList);
            jedisCluster.set(key, dataJson);
            System.out.println("查询数据库");
        } else {
            treeList = ObjectMapperUtil.toObject(result, treeList.getClass());
            System.out.println("查询缓存");
        }
        return treeList;
    }
}
