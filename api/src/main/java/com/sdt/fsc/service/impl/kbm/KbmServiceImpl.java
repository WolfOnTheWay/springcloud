package com.sdt.fsc.service.impl.kbm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.KbsResultData;
import com.sdt.fsc.entity.SearchEntity;
import com.sdt.fsc.entity.kbm.ADThresholdEntity;
import com.sdt.fsc.entity.response.Content;
import com.sdt.fsc.entity.response.ContentStrTime;
import com.sdt.fsc.factory.DataAssemBly;
import com.sdt.fsc.service.contract.es.ElasticSearchService;
import com.sdt.fsc.service.contract.kbm.IKbmService;
import com.sdt.fsc.util.http.HttpClientUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class KbmServiceImpl implements IKbmService {

    @Autowired
    private ElasticSearchService elasticSearchService;
    @Value("${kbm.driver}")
    private String kbmDriver;

    @Value("${kbm.host}")
    private String kbmHost;

    @Override
    public BaseResponse AnomalyDetectionModelApi(String key, Long beginTime, Long endTime, Integer page, Integer pageSize, String order, Integer upperLimit, Integer lowerLimit) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            //数据组装
            if (key==null || beginTime==null || endTime==null || upperLimit==null || lowerLimit==null || upperLimit<=lowerLimit) {
                return new BaseResponse<Object>(CustomErrorType.PARAM_INVALID.getCode(),CustomErrorType.PARAM_INVALID.getMessage(),null);
            }
            SearchEntity searchEntity = DataAssemBly.kbmDataAssembly(key, beginTime, endTime, order);
            if (null == page || 0 == page) {
                searchEntity.setFrom(1);
            } else {
                searchEntity.setFrom(page * pageSize);
            }
            if (null == pageSize || 0 == pageSize) {
                searchEntity.setSize(10);
            } else {
                searchEntity.setSize(pageSize);
            }

            KbsResultData searchData = elasticSearchService.ElasticSearchGetAll(searchEntity, pageSize, page, key);

            if (searchData == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(),CustomErrorType.DATA_IS_NULL.getMessage(),null);
            }
            List<ContentStrTime> contentStrTimeList = new ArrayList<>();
            ADThresholdEntity kbmEntity = new ADThresholdEntity();
            kbmEntity.setLowerLimit(lowerLimit);
            kbmEntity.setUpperLimit(upperLimit);
            List<Content> content = searchData.getContent();

            for (Content content1 : content) {
                ContentStrTime contentStrTime = new ContentStrTime();
                long ts = content1.getTs();
                String format = simpleDateFormat.format(ts);
                contentStrTime.setTs(format);
                contentStrTime.setValue(content1.getValue());
                contentStrTimeList.add(contentStrTime);
            }

            kbmEntity.setContentStrTime(contentStrTimeList);
            kbmEntity.setCount(searchData.getContent().size());
            String kbmEntityStr = JSON.toJSONString(kbmEntity);


            //python 接口调用
            String sendJsonStr = HttpClientUtil.sendJsonStr(kbmDriver+":"+kbmHost+"/AD_threshold", kbmEntityStr);

            //去除转义符
            String jsonStr = StringEscapeUtils.unescapeJava(sendJsonStr);
            ADThresholdEntity kbmEntity1 = new ADThresholdEntity();
            //数据抽取 组装
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

            if (kbmEntity1 == null){
                return  new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(),null);
            }
            if (kbmEntity1 != null){
                return  new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(),kbmEntity);
            }

        } catch (Exception e) {
            return new BaseResponse<Object>(CustomErrorType.ERROR.getCode(), CustomErrorType.ERROR.getMessage(),null);
        }
        return null;
    }
}
