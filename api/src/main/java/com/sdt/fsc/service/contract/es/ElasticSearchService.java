package com.sdt.fsc.service.contract.es;

import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.entity.KbsResultData;
import com.sdt.fsc.entity.SearchEntity;
public interface ElasticSearchService {


    /**
     * 时序数据检索服务
     * @param searchEntity
     * @return
     */
    public KbsResultData ElasticSearchGetAll(SearchEntity searchEntity,Integer pageSize, Integer page,String key);

    /**
     * 时序数据检索服务聚类分页
     * @param searchEntity
     * @return
     */
    public KbsResultData ElasticSearchGetAllPaging(SearchEntity searchEntity,Integer pageSize, Integer page,String key);



    public BaseResponse searchQuery(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order);

    public BaseResponse<Object> queryEsDataList(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order);
    /**
     * 时间大于两个月时 去ORC 表查询数据
     * @param key
     * @param beginTime
     * @param endTime
     * @param interval
     * @param page
     * @param pageSize
     * @param order
     * @return
     */
    public BaseResponse<Object> queryOrcDataList(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order);
}
