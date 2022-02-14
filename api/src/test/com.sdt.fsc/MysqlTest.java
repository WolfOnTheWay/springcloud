package com.sdt.fsc;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.entity.*;
import com.sdt.fsc.entity.response.ContentStrTime;
import com.sdt.fsc.entity.search.*;
import com.sdt.fsc.service.ElasticSearchService;
import com.sdt.fsc.service.impl.weather.WeatherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;


public class MysqlTest extends TmallApplicationTests {




    @Autowired
    private ElasticSearchService searchService;
    @Autowired
    private WeatherService weatherService;

    @Test
    public void testQueryCode() {
//        String startTime = "2021-02-09 20:00:00";
//        String endTime = "2021-02-09 21:00:00";
//        //查询时间段内的数据
//        List<WeatherInfo> weatherInfos = weatherService.queryWeatherAll(startTime, endTime);
//        //只需要一条数据
//        WeatherInfo weatherInfo = weatherInfos.get(0);
//        //获取code
//        String phenomenon = weatherInfo.getPhenomenon();
//        if (StringUtils.isEmpty(phenomenon)) {
//            Result.Err(Renum.CODE_ERROR.getCode(), Renum.CODE_ERROR.getMsg());
//        }
//        List<Integer> codeList = new ArrayList<>();
//        codeList.add(Integer.valueOf(phenomenon));
//        //查询code 对应的名称
//        List<WeatherEntity> weatherEntities = weatherService.queryWeatherCodeName(codeList);
//        if (CollectionUtils.isEmpty(weatherEntities)) {
//            Result.Err(Renum.DATA_IS_NULL.getCode(), Renum.DATA_IS_NULL.getMsg());
//        }
//        WeatherEntity weatherEntity = weatherEntities.get(0);
//        weatherInfo.setPhenomenon(weatherEntity.getDisplayName());
//        if (weatherInfo != null) {
//            Result.success(weatherInfo);
//        }
//        System.out.println(weatherInfo.toString());
    }

    @Test
    public void test() throws Exception {
        String jsonStr = "{\n" +
                " \"data\": {\n" +
                "  \"count\": \"1000\",\n" +
                "  \"list\": [\n" +
                "   {\n" +
                "    \"ts\": \"2021-02-05 06:30:00\",\n" +
                "    \"value\": 85.39848086547852,\n" +
                "    \"judge\": \"True\"\n" +
                "   },\n" +
                "   {\n" +
                "    \"ts\": \"2021-02-05 06:31:00\",\n" +
                "    \"value\": 86.39848086547852,\n" +
                "    \"judge\": \"True\"\n" +
                "   }\n" +
                "  ]\n" +
                " }" +
                "}";
        KbmEntity kbmEntity1 = new KbmEntity();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray list1 = data.getJSONArray("list");
        List<ContentStrTime> contentStrTimes = new ArrayList<>();
        ContentStrTime contentStrTime = new ContentStrTime();
        for (int i = 0; i < list1.size(); i++) {
            JSONObject jsonObject1 = list1.getJSONObject(i);
            contentStrTime.setTs(jsonObject1.getString("ts"));
            contentStrTime.setValue(jsonObject1.getFloat("value"));
            contentStrTime.setJudge(jsonObject1.getString("judge"));
            contentStrTimes.add(contentStrTime);
        }
        Object count = data.get("count");
        String s = String.valueOf(count);
        kbmEntity1.setCount(Integer.valueOf(s));

        kbmEntity1.setContentStrTime(contentStrTimes);
        System.out.println(kbmEntity1.toString());
    }

    /**
     * search接口 数据组装
     * 测试结果为 aggs json 复杂 凌乱 实体 没设计明白
     */
    @Test
    public void searchTest() {
        List<Must> must = new ArrayList<>();
        SearchEntity searchEntity = new SearchEntity();

        //query 数据
        Term term = new Term();
        term.setKey("C20102DL01SB26WD");


        Timestamps timestamps1 = new Timestamps();
        timestamps1.setGte(1619869451000L);
        timestamps1.setLte(1619869661000L);
        Range range = new Range();
        range.setTimestamps(timestamps1);
        Must must1 = new Must();
        must1.setTerm(term);
        Must must2 = new Must();
        must2.setRange(range);
        must.add(must1);
        must.add(must2);
        Bool bool = new Bool();
//        bool.setMust(must);
        Query query = new Query();
        query.setBool(bool);

        /* aggs 实体数据 */
        Avg avg = new Avg();
        avg.setField("value");
        Average_of_value average_of_value = new Average_of_value();
        average_of_value.setAvg(avg);
        Date_histogram date_histogram = new Date_histogram();
        date_histogram.setField("ts");
        date_histogram.setInterval("100ms");
        date_histogram.setOffset("-8h");
        date_histogram.setMin_doc_count(0);
        Aggs aggs = new Aggs();
        GroupDate groupDate = new GroupDate();
        groupDate.setDate_histogram(date_histogram);
        aggs.setGroupDate(groupDate);
        Aggs aggs1 = new Aggs();
        aggs1.setAverage_of_value(average_of_value);
        aggs.setAggs(aggs1);


//        sort 数据
        Timestamps timestamps = new Timestamps();
        timestamps.setOrder("asc");
        List<Sort> sort = new ArrayList<>();
        Sort sort1 = new Sort();
        sort1.setTimestamps(timestamps);
        sort.add(sort1);

        searchEntity.setQuery(query);
        searchEntity.setAggs(aggs);
        searchEntity.setSort(sort);

        KbsResultData resultDateEntity = searchService.ElasticSearchGetAll(searchEntity,100,0,"C20102DL01SB26WD");
        System.out.println(resultDateEntity.toString());
    }

    @Test
    public void searchTest1() {

        List<Must> must = new ArrayList<>();
        SearchEntity searchEntity = new SearchEntity();


        //query 数据
        Term term = new Term();
        term.setKey("C20102DL01SB26WD");
        Timestamps timestamps1 = new Timestamps();
        timestamps1.setGte(1619869451000L);
        timestamps1.setLte(1619869661000L);
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

//        /* aggs 实体数据 */
//        Avg avg = new Avg();
//        avg.setField("value");
//        Average_of_value average_of_value = new Average_of_value();
//        average_of_value.setAvg(avg);
//        Date_histogram date_histogram = new Date_histogram();
//        date_histogram.setField("timestamps");
//        date_histogram.setInterval("100ms");
//        date_histogram.setOffset("-8h");
//        date_histogram.setMin_doc_count(0);
//
//        Aggs aggs1 = new Aggs();
//        aggs1.setAverage_of_value(average_of_value);
//        Aggs aggs = new Aggs();
//        GroupDate groupDate = new GroupDate();
//        groupDate.setDate_histogram(date_histogram);
//        groupDate.setAggs(aggs1);
//        aggs.setGroupDate(groupDate);

//        sort 数据
        Timestamps timestamps = new Timestamps();
        timestamps.setOrder("asc");
        List<Sort> sort = new ArrayList<>();
        Sort sort1 = new Sort();
        sort1.setTimestamps(timestamps);
        sort.add(sort1);

        searchEntity.setQuery(query);
//        searchEntity.setAggs(aggs);
        searchEntity.setSort(sort);
        searchEntity.setFrom(0);

        KbsResultData kbsResultData = searchService.ElasticSearchGetAll(searchEntity, null, null,null);
        System.out.println(kbsResultData.toString());
    }

    @Test
    public void subString() {
        String str = "C20102HZ01SB26WD";
        String substring1 = str.substring(6, 8);
        switch (substring1){
            case "MJ":
                System.out.println("t_y_raise 表");
                break;
            case "DY" :
                System.out.println("t_r_raise 表");
                break;
            case "GQ" :
                System.out.println("t_gas_supply 表");
                break;
            case "XF" :
                System.out.println("t_fire 表");
                break;
            case "HZ" :
                System.out.println("t_platform 表");
                break;
            case "BG" :
                System.out.println("t_pendulum 表");
                break;
            case "KT" :
                System.out.println("t_air_conditioning 表");
                break;
            case "PS" :
                System.out.println(" t_noise_reduction 表");
                break;
            case "TD" :
                System.out.println("t_tower_crane 表");
                break;
            case "FS" :
                System.out.println("t_launch_pad 表");
                break;
            case "ZK" :
                System.out.println(" t_alert 表");
                break;
            default:
                System.out.println(" t_other 表");
        }

    }

}
