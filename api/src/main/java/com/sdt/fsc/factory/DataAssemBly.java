package com.sdt.fsc.factory;

import com.sdt.fsc.entity.SearchEntity;
import com.sdt.fsc.entity.search.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据组装
 */
public class DataAssemBly {
    /**
     *  物联数据组装
     * @param key
     * @param beginTime
     * @param endTime
     * @param order
     * @return
     */
    public static SearchEntity kbmDataAssembly(String key, Long beginTime, Long endTime, String order){

        List<Must> must = new ArrayList<>();
        SearchEntity searchEntity = new SearchEntity();
        //query 数据
        Term term = new Term();
        term.setKey(key);
        Timestamps timestamps1 = new Timestamps();
        timestamps1.setGte(beginTime);
        timestamps1.setLte(endTime);
        Range range = new Range();
        range.setTimestamps(timestamps1);

        Must must1 = new Must();
        must1.setTerm(term);
        Must must2 = new Must();
        must2.setRange(range);
        must.add(must1);
        must.add(must2);
        Bool bool = new Bool();
        bool.setMust(must);
        Query query = new Query();
        query.setBool(bool);

        //        sort 数据
        Timestamps timestamps = new Timestamps();
        if (StringUtils.isBlank(order)) {
            timestamps.setOrder("asc");
        } else {
            timestamps.setOrder(order);
        }

        List<Sort> sort = new ArrayList<>();
        Sort sort1 = new Sort();
        sort1.setTimestamps(timestamps);
        sort.add(sort1);

        searchEntity.setQuery(query);
        searchEntity.setSort(sort);
        return searchEntity;
    }
}
