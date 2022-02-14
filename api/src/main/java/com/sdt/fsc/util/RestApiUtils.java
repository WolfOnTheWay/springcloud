package com.sdt.fsc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @description: Rest访问视频配置中心管理系统
 * @author: hegonglei
 * @date: 2021-6-9 14:32
 */
@Service
public class RestApiUtils {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${dcm.username}")
    private String username;
    @Value("${dcm.password}")
    private String password;

    /**
     *
     * @description: restTemplate通过GET方式请求数据
     * @param url
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 14:32
     */
    public JSONObject doHttpsGetRequest(String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        try{
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            Object object = JSON.parse(responseEntity.getBody());
            JSONObject jsonObject = new JSONObject();
            if (object instanceof JSONObject) {
                jsonObject = JSONObject.parseObject(responseEntity.getBody(), Feature.OrderedField);
            }
            else if (object instanceof JSONArray) {
                jsonObject.put("data", ((JSONArray) object).toArray());
            }
            return jsonObject;
        }catch (Exception e){
            throw new CustomException(CustomErrorType.SUBSYSTEM_DCM_UNCONNECT);
        }
    }

    /**
     *
     * @description: restTemplate通过POST方式请求数据
     * @param url
     * @param object
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 14:31
     */
    public JSONObject doHttpsPostRequest(String url, Object object) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,object,String.class);
            return JSONObject.parseObject(responseEntity.getBody(), Feature.OrderedField);
        }catch (Exception e){
            throw new CustomException(CustomErrorType.SUBSYSTEM_DCM_UNCONNECT);
        }
    }

    /**
     *
     * @description: restTemplate通过GET方式请求数据
     * @param url
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 14:31
     */
    public JSONObject doGetRequest(String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        try{
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            Object object = JSON.parse(responseEntity.getBody());
            JSONObject jsonObject = new JSONObject();
            if (object instanceof JSONObject) {
                jsonObject = JSONObject.parseObject(responseEntity.getBody(), Feature.OrderedField);
            }
            else if (object instanceof JSONArray) {
                jsonObject.put("data", ((JSONArray) object).toArray());
            }
            return jsonObject;
        }catch (Exception e){
            throw new CustomException(CustomErrorType.SUBSYSTEM_QZ_UNCONNECT);
        }
    }

    /**
     *
     * @description: restTemplate通过POST方式请求数据
     * @param url
     * @param object
     * @return: com.alibaba.fastjson.JSONObject
     * @author: hegonglei
     * @date: 2021-6-9 14:31
     */
    public JSONObject doPostRequest(String url, Object object) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try{
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,object,String.class);
            return JSONObject.parseObject(responseEntity.getBody(), Feature.OrderedField);
        }catch (Exception e){
            throw new CustomException(CustomErrorType.SUBSYSTEM_QZ_UNCONNECT);
        }
    }

}
