package com.sdt.fsc.service.impl.es;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.KbsResultData;
import com.sdt.fsc.entity.search.OrcTable;
import com.sdt.fsc.entity.search.OrcTableResultData;
import com.sdt.fsc.entity.SearchEntity;
import com.sdt.fsc.entity.response.Content;
import com.sdt.fsc.entity.search.*;
import com.sdt.fsc.service.contract.es.ElasticSearchService;
import com.sdt.fsc.util.http.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangxinhao
 */
@Service
@Slf4j
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Value("${search.driver}")
    private String searchDriver;
    @Value("${search.host}")
    private String searchHost;
    @Resource(name = "hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 时序数据检索服务
     *
     * @param searchEntity
     * @return
     */
    @Override
    public KbsResultData ElasticSearchGetAll(SearchEntity searchEntity, Integer pageSize, Integer page, String key) {
        KbsResultData kbsResultData = new KbsResultData();
        List<Content> contents = new ArrayList<>();

        String esSearchJson = JSONObject.toJSONString(searchEntity);
        String sendJsonStr = JsonStr(key, esSearchJson);
        JSONObject jsonObject = JSONObject.parseObject(sendJsonStr);
        JSONObject jsonObject1 = jsonObject.getJSONObject("hits");
        Integer totalSize = Integer.valueOf(jsonObject1.getInteger("total"));
        kbsResultData.setTotalSizes(totalSize);
        searchEntity.setSize(totalSize);
        sendJsonStr = JsonStr(key, JSONObject.toJSONString(searchEntity));

        //System.out.println(hits);
        jsonObject = JSONObject.parseObject(sendJsonStr);
        jsonObject1 = jsonObject.getJSONObject("hits");
        JSONArray hits1 = jsonObject1.getJSONArray("hits");

        for (int i = 0; i < hits1.size(); i++) {
            JSONObject obj = (JSONObject) hits1.getJSONObject(i).get("_source");
            Content content = new Content();
            content.setTs(obj.getLong("timestamps"));
            content.setValue(obj.getFloat("value"));
            contents.add(content);
        }
        kbsResultData.setContent(contents);
        //总记录数

        if (null != pageSize && null != page) {
            //当前页
            kbsResultData.setPage(page);
            kbsResultData.setPageSize(pageSize);
            int totalPages = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
            //总页数
            kbsResultData.setTotalPages(totalPages);
        } else {
            kbsResultData.setPage(0);
            kbsResultData.setPageSize(totalSize);
            kbsResultData.setTotalPages(1);
        }
        return kbsResultData;
    }


    /**
     * 时序数据检索服务聚类分页
     *
     * @param searchEntity
     * @return
     */
    @Override
    public KbsResultData ElasticSearchGetAllPaging(SearchEntity searchEntity, Integer pageSize, Integer page, String key) {
        KbsResultData kbsResultData = new KbsResultData();
        List<Content> contents = new ArrayList<>();

        String esSearchJson = JSONObject.toJSONString(searchEntity);
        String sendJsonStr = JsonStr(key, esSearchJson);
        JSONObject jsonObject = JSONObject.parseObject(sendJsonStr);
        JSONObject jsonObject1 = jsonObject.getJSONObject("hits");
        Integer totalSize = Integer.valueOf(jsonObject1.getInteger("total"));
        //kbsResultData.setTotalSizes(totalSize);
        searchEntity.setSize(totalSize);
        sendJsonStr = JsonStr(key, JSONObject.toJSONString(searchEntity));
        jsonObject = JSONObject.parseObject(sendJsonStr);
        jsonObject1 = jsonObject.getJSONObject("aggregations");
        JSONObject jsonObject2 = jsonObject1.getJSONObject("groupDate");
        JSONArray buckets1 = jsonObject2.getJSONArray("buckets");


        for (int i = 0; i < buckets1.size(); i++) {
            JSONObject obj = buckets1.getJSONObject(i);
            JSONObject averageValue = (JSONObject) obj.get("average_of_value");
            Content content = new Content();
            content.setTs(obj.getLong("key"));
            content.setValue(averageValue.getFloatValue("value"));
            contents.add(content);
        }
        kbsResultData.setContent(contents);
        totalSize = contents.size();
        kbsResultData.setTotalSizes(totalSize);
        //总记录数

        if (null != pageSize && null != page) {
            //当前页
            kbsResultData.setPageSize(pageSize);
            int totalPages = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
            //总页数
            kbsResultData.setTotalPages(totalPages);
            kbsResultData.paging(page);
        } else {
            kbsResultData.setPageSize(totalSize);
            kbsResultData.setTotalPages(1);
            kbsResultData.paging(0);
        }
        return kbsResultData;
    }

    @Override
    public BaseResponse searchQuery(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order) {
        try {
            List<Must> must = new ArrayList<>();
            SearchEntity searchEntity = new SearchEntity();

            if (null != pageSize && 0 != pageSize) {
                searchEntity.setSize(pageSize);
            }

            if (null != page && 0 <= page) {
                searchEntity.setFrom(page * pageSize);
            }

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
            if (org.apache.commons.lang3.StringUtils.isBlank(order)) {
                timestamps.setOrder("asc");
            } else {
                timestamps.setOrder(order);
            }

            List<Sort> sort = new ArrayList<>();
            Sort sort1 = new Sort();
            sort1.setTimestamps(timestamps);
            sort.add(sort1);

            searchEntity.setQuery(query);

            if (null != interval && 0 < interval) {
                Avg avg = new Avg();
                avg.setField("value");
                Average_of_value average_of_value = new Average_of_value();
                average_of_value.setAvg(avg);
                Date_histogram date_histogram = new Date_histogram();
                date_histogram.setField("timestamps");
                date_histogram.setInterval(interval + "ms");
                Aggs aggs1 = new Aggs();
                aggs1.setAverage_of_value(average_of_value);
                Aggs aggs = new Aggs();
                GroupDate groupDate = new GroupDate();
                groupDate.setDate_histogram(date_histogram);
                groupDate.setAggs(aggs1);
                aggs.setGroupDate(groupDate);
                searchEntity.setAggs(aggs);
            }

            searchEntity.setSort(sort);

            KbsResultData searchData;

            if (null != interval && 0 < interval) {         //判断是否聚类
                searchData = this.ElasticSearchGetAllPaging(searchEntity, pageSize, page, key);
            } else
                searchData = this.ElasticSearchGetAll(searchEntity, pageSize, page, key);

            if (searchData == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), searchData);
            }
            if (searchData != null) {
                return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), searchData);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), null);
        }
        return null;
    }

    @Override
    public BaseResponse<Object> queryEsDataList(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order) {
        KbsResultData kbsResultData = new KbsResultData();
        //参数检验
        if (key == null || "".equals(key)) {
            return new BaseResponse(CustomErrorType.PARAM_IS_NULL.getCode(), CustomErrorType.PARAM_IS_NULL.getMessage(), null);
        }
        if (beginTime == null || beginTime < 0) {
            return new BaseResponse(CustomErrorType.PARAM_IS_NULL.getCode(), CustomErrorType.PARAM_IS_NULL.getMessage(), null);
        }
        if (endTime == null || endTime < 0) {
            return new BaseResponse(CustomErrorType.PARAM_IS_NULL.getCode(), CustomErrorType.PARAM_IS_NULL.getMessage(), null);
        }
        //1.参数封装
        SearchEntity searchEntity = getParam(key, beginTime, endTime, interval, page, pageSize, order);
        //2.将searchEntity转为json串
        String jsonString = JSONObject.toJSONString(searchEntity);
        String jsonStr = JsonStr(key, jsonString);
        //获取总的页数
        Integer totalSize = Integer.valueOf(JSONObject.parseObject(jsonStr).getJSONObject("hits").getInteger("total"));
        List<Content> contentList = new ArrayList<>();
        //区分聚合操作还是全查
        if (StringUtils.isEmpty(searchEntity.getAggs())) {
            //全查
            JSONArray array = JSONObject.parseObject(jsonStr).getJSONObject("hits").getJSONArray("hits");
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.getJSONObject(i).get("_source");
                Content data = new Content();
                data.setTs(obj.getLong("timestamps"));
                data.setValue(obj.getFloat("value"));
                contentList.add(data);
            }
        } else {
            JSONArray array = JSONObject.parseObject(jsonStr).getJSONObject("aggregations").getJSONObject("groupDate").getJSONArray("buckets");
            for (int i = 0; i < array.size(); i++) {
                Content data = new Content();
                JSONObject obj = array.getJSONObject(i);
                JSONObject averageValue = (JSONObject) obj.get("average_of_value");
                data.setTs(obj.getLong("key"));
                data.setValue(averageValue.getFloatValue("value"));
                contentList.add(data);
            }

        }
        //分页
        kbsResultData.setContent(contentList);
        totalSize = contentList.size();
        kbsResultData.setTotalSizes(totalSize);
        //总记录数
        if (pageSize != null && page != null) {
            //当前页
            kbsResultData.setPageSize(pageSize);
            int totalPages = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
            //总页数
            kbsResultData.setTotalPages(totalPages);
            kbsResultData.paging(page);
        } else {
            kbsResultData.setPage(0);
            kbsResultData.setPageSize(totalSize);
            kbsResultData.setTotalPages(1);
        }

        return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), kbsResultData);
    }

    private SearchEntity getParam(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order) {
        //参数分装
        SearchEntity searchEntity = new SearchEntity();
        //size不传默认设置为10000
        if (pageSize == null) {
            searchEntity.setSize(10000);
        }
        List<Must> list = new ArrayList<>();
        Must must = new Must();
        list.add(must.setTerm(new Term().setKey(key)));
        list.add(new Must().setRange(new Range().
                setTimestamps(new Timestamps().setLte(endTime).setGte(beginTime))));

        List<Sort> sort = new ArrayList<>();
        sort.add(new Sort().setTimestamps(new Timestamps().setOrder("ASC")));
        searchEntity.setQuery(new Query().setBool(new Bool().setMust(list))).setSort(sort);

        //聚合操作
        if (interval != null && interval > 0) {
            Aggs aggs = new Aggs();
            GroupDate groupDate = new GroupDate();
            groupDate.setAggs(new Aggs().setAverage_of_value(new Average_of_value().setAvg(new Avg().setField("value"))));
            groupDate.setDate_histogram(new Date_histogram()
                    .setField("timestamps")
                    .setInterval(interval + "ms")
                    .setMin_doc_count(0));
            aggs.setGroupDate(groupDate);
            searchEntity.setAggs(aggs);
        }
        return searchEntity;
    }

    /**
     * 根据KEY 中的关键词 查询不同的表
     *
     * @param key
     * @return
     */
    private String JsonStr(String key, String searchJson) {
        String regex = "(?!=te\\\": )[0-9]{13}";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(searchJson);
        List<Long> timeStamp = new ArrayList<>();
        while (matcher.find()) {
            matcher.start();
            matcher.end();
            timeStamp.add(Long.parseLong(matcher.group(0)));
        }
        Collections.sort(timeStamp);
        String substring1 = key.substring(6, 8);
        String sendJsonStr;
        String url = "/_search";
        String searchTable;
        switch (substring1) {
            case "MY"://ok
                searchTable = "inceptor_iot_standard_t_es_r_raise";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_y_raise/_search", searchJson);
                break;
            case "BJ"://ok
                searchTable = "inceptor_iot_standard_t_es_alarm";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_r_raise/_search", searchJson);
                break;
            case "DY"://ok
                searchTable = "inceptor_iot_standard_t_es_y_raise";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_gas_supply/_search", searchJson);
                break;
            case "XF"://ok
                searchTable = "inceptor_iot_standard_t_es_fire";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_fire/_search", searchJson);
                break;
            case "PT": //PT
                searchTable = "inceptor_iot_standard_t_es_platform";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_platform/_search", searchJson);
                break;
            case "GQ"://ok
                searchTable = "inceptor_iot_standard_t_es_gas_supply";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_pendulum/_search", searchJson);
                break;
            case "BG":
                searchTable = "inceptor_iot_standard_t_es_pendulum";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_air_conditioning/_search", searchJson);
                break;
            case "PJ"://ok
                searchTable = "inceptor_iot_standard_t_es_noise_reduction";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_noise_reduction/_search", searchJson);
                break;
            case "TD"://ok
                searchTable = "inceptor_iot_standard_t_es_tower_crane";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_tower_crane/_search", searchJson);
                break;
            case "FS"://ok
                searchTable = "inceptor_iot_standard_t_es_launch_pad";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_launch_pad/_search", searchJson);
                break;
            case "KT":
                searchTable = "inceptor_iot_standard_t_es_air_conditioning";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_alert/_search", searchJson);
                break;
            default:
                searchTable = "inceptor_iot_standard_t_es_other";
                //sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver+ ":"+ searchHost+"/default.t_other/_search", searchJson);
        }
        url = searchTable + "_dataset" + "*" + url;
        ;
        sendJsonStr = HttpClientUtil.sendJsonStr(searchDriver + ":" + searchHost + "/" + url, searchJson);
        if (sendJsonStr == null || sendJsonStr.equals("")) {
            throw new NullPointerException("查询数据为空");
        }
        return sendJsonStr;
    }

    /**
     * 当时间超过两个月时 查询 orc 表
     *
     * @return
     */
    public String orcTable(String key) {
        String substring1 = key.substring(6, 8);
        String searchTable;
        switch (substring1) {
            case "MY":
                searchTable = "inceptor_iot_standard.t_orc_r_raise";
                break;
            case "BJ":
                searchTable = "inceptor_iot_standard.t_orc_alarm";
                break;
            case "DY":
                searchTable = "inceptor_iot_standard.t_orc_y_raise";
                break;
            case "XF":
                searchTable = "inceptor_iot_standard.t_orc_fire";
                break;
            case "PT":
                searchTable = "inceptor_iot_standard.t_orc_platform";
                break;
            case "GQ":
                searchTable = "inceptor_iot_standard.t_orc_gas_supply";
                break;
            case "BG":
                searchTable = "inceptor_iot_standard.t_orc_pendulum";
                break;
            case "PJ":
                searchTable = "inceptor_iot_standard.t_orc_noise_reduction";
                break;
            case "TD":
                searchTable = "inceptor_iot_standard.t_orc_tower_crane";
                break;
            case "FS":
                searchTable = "inceptor_iot_standard.t_orc_launch_pad";
                break;
            case "KT":
                searchTable = "inceptor_iot_standard.t_orc_air_conditioning";
                break;
            default:
                searchTable = "inceptor_iot_standard.t_orc_other";
        }
        return searchTable;
    }

    @Override
    public BaseResponse<Object> queryOrcDataList(String key, Long beginTime, Long endTime, Long interval, Integer page, Integer pageSize, String order) {
        String sql = null;
        //参数检验
        if (key == null || "".equals(key)) {
            return new BaseResponse(CustomErrorType.PARAM_IS_NULL.getCode(), CustomErrorType.PARAM_IS_NULL.getMessage(), null);
        }
        if (beginTime == null || beginTime < 0) {
            return new BaseResponse(CustomErrorType.PARAM_IS_NULL.getCode(), CustomErrorType.PARAM_IS_NULL.getMessage(), null);
        }
        if (endTime == null || endTime < 0) {
            return new BaseResponse(CustomErrorType.PARAM_IS_NULL.getCode(), CustomErrorType.PARAM_IS_NULL.getMessage(), null);
        }
        String beginFormat = simpleDateFormat.format(beginTime);
        String endFormat = simpleDateFormat.format(endTime);
//        try {
            //获取当前系统时间
            long startTime = System.currentTimeMillis();
            // 通过key 筛选orc表
            String searchTable = orcTable(key);
            OrcTableResultData orcTableResultData = new OrcTableResultData();
            //size不传默认设置为10000
            if (pageSize == null) {
                orcTableResultData.setPageSize(10000);
            }
            if (page == null){
                orcTableResultData.setPage(1);
            }
            //查询
            if (interval != null) {
                //聚合sql
//                sql = "select  floor(timestamps/5000)*" + interval + " AS timestamps1,avg(value) AS value " +
//                        "FROM inceptor_iot_standard." + searchTable + " where  KEY = '" + key + "' AND timestamps >= '" + beginTime + "' and timestamps <= '" + endTime + "' and dates >= '" + beginFormat + "' and dates <= " +
//                        " '" + endFormat + "' GROUP BY timestamps1  ORDER BY timestamps1 desc limit " + orcTableResultData.getPage() + "," + orcTableResultData.getPageSize() + " ";
                sql = "SELECT timestamps1 AS timestamps, avg(value) AS value FROM (SELECT floor(timestamps/%d)*%d AS timestamps1, value FROM %s WHERE key='%s' AND timestamps>=%s AND timestamps<=%s AND dates>='%s' AND dates<='%s') GROUP BY timestamps1 " +
                        "ORDER BY timestamps1 DESC LIMIT %d, %d;";
                sql = String.format(sql, interval, interval, searchTable, key, beginTime, endTime, beginFormat, endFormat, orcTableResultData.getPage(), orcTableResultData.getPageSize());
            } else {
                //非聚合sql
                sql = "SELECT timestamps, value FROM " + searchTable + " WHERE key='" + key + "' AND timestamps>='" + beginTime + "' AND timestamps<='" + endTime + "' AND dates>='" + beginFormat + "' AND dates<=" +
                        " '" + endFormat + "' ORDER BY timestamps DESC LIMIT " + orcTableResultData.getPage() + "," + orcTableResultData.getPageSize() + ";";
            }
            log.info("获取的sql 为  ----- " + sql);
            // 获取返回结果
            List<OrcTable> orcTableList = hiveDruidTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<OrcTable>(OrcTable.class));

            if (orcTableList != null && orcTableList.size() > 0) {
                orcTableResultData.setOrcTableList(orcTableList);
                Integer totalSize = orcTableList.size();
                orcTableResultData.setTotalSizes(totalSize);
                //总记录数
                if (pageSize != null && page != null) {
                    //当前页
                    orcTableResultData.setPageSize(pageSize);
                    int totalPages = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
                    //总页数
                    orcTableResultData.setTotalPages(totalPages);
                    orcTableResultData.paging(page);
                } else {
                    orcTableResultData.setPage(0);
                    orcTableResultData.setPageSize(totalSize);
                    orcTableResultData.setTotalPages(1);
                }
                long end = System.currentTimeMillis();
                log.info("执行多任务操作所花费的时间：" + (end - startTime) + "毫秒");
                if (orcTableResultData != null) {
                    log.info("查询到的结果为\t" + orcTableResultData);
                    return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), orcTableResultData);
                }

            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

}

