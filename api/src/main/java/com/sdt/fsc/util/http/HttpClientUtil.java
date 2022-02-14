package com.sdt.fsc.util.http;

import io.transwarp.guardian.federation.org.apache.http.HttpResponse;
import io.transwarp.guardian.federation.org.apache.http.HttpStatus;
import io.transwarp.guardian.federation.org.apache.http.NameValuePair;
import io.transwarp.guardian.federation.org.apache.http.auth.AuthScope;
import io.transwarp.guardian.federation.org.apache.http.auth.Credentials;
import io.transwarp.guardian.federation.org.apache.http.auth.UsernamePasswordCredentials;
import io.transwarp.guardian.federation.org.apache.http.client.entity.UrlEncodedFormEntity;
import io.transwarp.guardian.federation.org.apache.http.client.methods.CloseableHttpResponse;
import io.transwarp.guardian.federation.org.apache.http.client.methods.HttpGet;
import io.transwarp.guardian.federation.org.apache.http.client.methods.HttpPost;
import io.transwarp.guardian.federation.org.apache.http.client.utils.URIBuilder;
import io.transwarp.guardian.federation.org.apache.http.entity.StringEntity;
import io.transwarp.guardian.federation.org.apache.http.impl.client.BasicCredentialsProvider;
import io.transwarp.guardian.federation.org.apache.http.impl.client.CloseableHttpClient;
import io.transwarp.guardian.federation.org.apache.http.impl.client.HttpClientBuilder;
import io.transwarp.guardian.federation.org.apache.http.impl.client.HttpClients;
import io.transwarp.guardian.federation.org.apache.http.message.BasicNameValuePair;
import io.transwarp.guardian.federation.org.apache.http.util.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * @param url    请求路径
     * @param params
     * @Title: doGet
     * @Description: get方式
     * @author Mundo
     */
    public static String doGet(String url, Map<String, String> params) {

        // 返回结果
        String result = "";
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = null;
        try {
            // 拼接参数,可以用URIBuilder,也可以直接拼接在？传值，拼在url后面，如下--httpGet = new
            // HttpGet(uri+"?id=123");
            URIBuilder uriBuilder = new URIBuilder(url);
            if (null != params && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), entry.getValue());
                    // 或者用
                    // 顺便说一下不同(setParameter会覆盖同名参数的值，addParameter则不会)
                    // uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
            // 创建get请求
            httpGet = new HttpGet(uri);
            logger.info("访问路径：" + uri);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// 返回200，请求成功
                // 结果返回
                result = EntityUtils.toString(response.getEntity());
                logger.info("请求成功！，返回数据：" + result);
            } else {
                logger.info("请求失败！");
            }
        } catch (Exception e) {
            logger.info("请求失败!");
            logger.error(ExceptionUtils.getStackTrace(e));
        } finally {
            // 释放连接
            if (null != httpGet) {
                httpGet.releaseConnection();
            }
        }
        return result;
    }

    /**
     * @param url
     * @param params
     * @return
     * @Title: doPost
     * @Description: post请求
     * @author Mundo
     */
    public static String doPost(String url, Map<String, String> params) {
        String result = "";
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try { // 参数键值对
            if (null != params && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                NameValuePair pair = null;
                for (String key : params.keySet()) {
                    pair = new BasicNameValuePair(key, params.get(key));
                    pairs.add(pair);
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
                logger.info("返回数据：>>>" + result);
            } else {
                logger.info("请求失败！，url:" + url);
            }

        } catch (Exception e) {
            logger.error("请求失败");
            logger.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        } finally {
            if (null != httpPost) {
                // 释放连接
                httpPost.releaseConnection();

            }

        }
        return result;
    }

    /**
     * @param url
     * @param params
     * @return 返回数据
     * @Title: sendJsonStr
     * @Description: post发送json字符串
     * @author Mundo
     */

    public static String sendJsonStr(String url, String params) {
        String result = "";
        io.transwarp.guardian.federation.org.apache.http.client.CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        Credentials credentials = new UsernamePasswordCredentials("admin", "admin");
        credentialsProvider.setCredentials(AuthScope.ANY, credentials);
        CloseableHttpClient build = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();

        HttpPost httpPost = new HttpPost("http://"+ url);

        try {

            httpPost.addHeader("Content-type", "application/json; charset=utf-8");

            httpPost.setHeader("Accept", "application/json");

            if (StringUtils.isNotBlank(params)) {
                httpPost.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
            }
            CloseableHttpResponse response = build.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
                logger.info("返回数据：" + result);

            } else {
                logger.info("请求失败");
            }

        } catch (IOException e) {
            logger.error("请求异常");

            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return result;

    }

    public static void main(String[] args) {
        String json = sendJsonStr("http://10.26.8.161:9200/default.t_y_raise/_search", "{\n" +
                "    \"query\": {\n" +
                "        \"bool\": {\n" +
                "            \"must\": [\n" +
                "                {\n" +
                "                    \"term\": {\n" +
                "                        \"key\": \"C20102MJ01SB26WD\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"range\": {\n" +
                "                        \"timestamps\": {\n" +
                "                            \"gte\": 1619858575000,\n" +
                "                            \"lte\": 1619869655000\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    },\n" +
                "    \"sort\": [\n" +
                "        {\n" +
                "            \"timestamps\": {\n" +
                "                \"order\": \"asc\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}");

        System.out.println("json发送成功，返回数据是：" + json);

    }


}
