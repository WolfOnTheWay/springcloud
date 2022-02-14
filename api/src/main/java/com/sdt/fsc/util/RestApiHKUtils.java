package com.sdt.fsc.util;


import com.alibaba.fastjson.JSONObject;

import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.common.ResultTemplateDTO;
import com.sdt.fsc.exception.GeneralFailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author wangtiexiang
 * @Description 接入各子系统，通过restTemplate访问各子系统
 * @Datetime 2020/7/14 15:26
 */
@Service
public class RestApiHKUtils {
    @Resource
    private RestTemplate restTemplate;
    @Value("${hk.protocal}")
    private String protocal;
    @Value("${hk.host}")
    private String host;
    @Value("${hk.appKey}")
    private String appKey;
    @Value("${hk.appSecret}")
    private String appSecret;

    /**
     * 接入海康子系统，包括视频监控子系统
     *
     * @param object
     * @param url
     * @param T      返回泛型
     * @param <T>
     * @return
     * @throws Exception
     */
//    public <T> T accessToHK(Object object, String url, Class T) throws Exception {
//        ArtemisConfig.appKey = appKey;
//        ArtemisConfig.appSecret = appSecret;
//        ArtemisConfig.host = host;
//        Map<String, String> path = new HashMap<String, String>() {
//            {
//                put(protocal, url);
//            }
//        };
//        String param = JSONObject.toJSONString(object);
//        String result = ArtemisHttpUtil.doPostStringArtemis(path, param, null, null, "application/json");
//        Optional.ofNullable(result)
//                .orElseThrow(() -> new GeneralFailException(CustomErrorType.SUBSYSTEM_HK_UNCONNECT.getCode(), CustomErrorType.SUBSYSTEM_HK_UNCONNECT.getMessage()));
//        T t = (T) JSONObject.parseObject(result, T);
//        return t;
//    }

    /**
     * 验证海康子系统返回是否正常
     *
     * @param resultCode 200为正常，其他为异常
     * @param resultMsg  异常描述
     */
    public void resultCodeCheck(String resultCode, String resultMsg) {
        if ((resultCode.length() > 1) || (Integer.parseInt(resultCode) != 0)) {
            throw new GeneralFailException(CustomErrorType.ERROR.getCode(), resultMsg);
        }
    }

    /**
     * 从海康子系统获取信息
     * @param T 返回类型
     * @param object 输入参数
     * @param url 对应海康子系统的接口地址
     * @param <T> 泛型
     * @return
     * @throws Exception
     */
//    public <T> T getInfoFromHK(Class T,Object object,String url) throws Exception{
//        ResultTemplateDTO<T> result = accessToHK(object,url,ResultTemplateDTO.class);
//        resultCodeCheck(result.getCode(), result.getMsg());
//        T t = (T) JSONObject.parseObject(JSONObject.toJSONString(result.getData()), T);
//        return t;
//    }
}
