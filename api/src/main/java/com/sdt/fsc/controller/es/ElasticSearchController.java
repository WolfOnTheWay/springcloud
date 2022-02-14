package com.sdt.fsc.controller.es;


import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.service.contract.es.ElasticSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/iot/data")
@Api(value = "IoT-API",description = "物联网设备态势感知API")
public class ElasticSearchController {
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);

    @Autowired
    private ElasticSearchService elasticSearchService;

    /**
     *
     * @param key 传感器信号点位
     * @param beginTime 起始时间
     * @param endTime 结束时间
     * @param order 排序
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "传感器信号点位数据查询")
    public BaseResponse queryEsDataList(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order){
        return elasticSearchService.queryEsDataList(key, beginTime, endTime, interval, page, pageSize, order);
    }
    /**
     *
     * @param key 传感器信号点位
     * @param beginTime 起始时间
     * @param endTime 结束时间
     * @param order 排序
     * @return
     */
    @GetMapping("listOrc")
    @ApiOperation(value = "传感器信号点位数据查询")
    public BaseResponse queryOrcDataList(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order){
        try{
            String format1 = new SimpleDateFormat("yyyy-MM-dd").format(beginTime);
            String format2 = new SimpleDateFormat("yyyy-MM-dd").format(endTime);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = format.parse(format1);
            Date parse1 = format.parse(format2);
            int different = different(parse, parse1);

            if (different>1){
                return elasticSearchService.queryOrcDataList(key, beginTime, endTime, interval, page, pageSize, order);
            } else {
                return elasticSearchService.queryEsDataList(key, beginTime, endTime, interval, page, pageSize, order);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static int different(Date date1, Date date2){
        int days = (int) ((date2.getTime() -date1.getTime()) / (1000*3600*24));
        return days;
    }
}
