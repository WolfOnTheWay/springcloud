package com.sdt.fsc.service.impl.weather;

import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.weather.*;
import com.sdt.fsc.mapper.mysqlmapper.WeatherMapper;
import com.sdt.fsc.service.contract.weather.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * @author wangxinhao
 * 气象API接口
 */
@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {


    @Resource
    private WeatherMapper weatherMapper;

    @Resource(name = "hiveDruidTemplate")
    private JdbcTemplate hiveDruidTemplate;



    @Override
    public List<WeatherEntity> queryWeatherCodeName(List<Integer> codeList) {
        List<WeatherEntity> weatherEntities = weatherMapper.queryWeatherCodeName(codeList);
        return weatherEntities;
    }

    /**
     * 根据时间查询气象所有信息
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public WeatherInfo queryWeatherAll(String startTime, String endTime) {
        WeatherInfo weatherInfo = weatherMapper.queryWeatherAll(startTime, endTime);
        return weatherInfo;
    }
    @Override
    public WeatherDrop queryWeatherDrop(String startTime, String endTime) {
        WeatherDrop weatherDrop = weatherMapper.queryWeatherDrop(startTime, endTime);
        return weatherDrop;
    }
    @Override
    public WeatherPositionObsrv queryWeatherObsrv(String startTime, String endTime) {
        WeatherPositionObsrv weatherPositionObsrvs = weatherMapper.queryWeatherObsrv(startTime, endTime);
        return weatherPositionObsrvs;
    }
    @Override
    public WeatherUpper queryWeatherUpper(String startTime, String endTime) {
        WeatherUpper weatherUpper = weatherMapper.queryWeatherUpper(startTime, endTime);
        return weatherUpper;
    }
    @Override
    public WeatherHx queryWeatherHx(String startTime, String endTime) {
        WeatherHx weatherHx = weatherMapper.queryWeatherHx(startTime, endTime);
        return weatherHx;
    }


    @Override
    public BaseResponse<Object> getHumanObserveData1(String beginTime, String endTime) {

        //参数校验
        if (StringUtils.isEmpty(beginTime) && StringUtils.isEmpty(endTime)) {
            return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
        }
        try {
            long startTime = System.currentTimeMillis();
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 10, 20L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
            WeatherInfo weatherInfo2 = CompletableFuture.supplyAsync(() -> {
                String sql = " select observeTime,stationNo,phenomenon,windSpeed,temperature,pressure,relativeHumidity," +
                        "absoluteHumidity,allCloudAmount from t_text_surface_hour_observation where " +
                        "observeTime >= '" + beginTime + "' and observeTime <= '" + endTime + "' ORDER BY observeTime desc limit 1";
                List<WeatherInfo> weatherInfoList = hiveDruidTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<WeatherInfo>(WeatherInfo.class));

                WeatherInfo weatherInfo = null;
                if (weatherInfoList != null && weatherInfoList.size() >0) {
                    weatherInfo = weatherInfoList.get(0);
                }
                return weatherInfo;
            }, poolExecutor).thenApply(weatherInfo1 -> {
                WeatherInfo info = new WeatherInfo();
                BeanUtils.copyProperties(weatherInfo1, info);
                boolean b = hasChineseCharacter(info.getPhenomenon());
                if (b == false) {
                    String hql = "select *  from t_dic_phenomenon_code where code = " + weatherInfo1.getPhenomenon();
                    List<DirCodeEntity> dirCodeEntityList = hiveDruidTemplate.query(hql, new Object[]{}, new BeanPropertyRowMapper<>(DirCodeEntity.class));
                    if (dirCodeEntityList != null && dirCodeEntityList.size() >0) {
                        DirCodeEntity dirCodeEntity = dirCodeEntityList.get(0);
                        if (dirCodeEntity.getDisplayName() != null) {
                            info.setPhenomenon(dirCodeEntity.getDisplayName());
                        }
                    }
                }
                return info;
            }).whenComplete((v, e) -> {
                if (e == null) {
                    log.info("---------------" + v.getPhenomenon());
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                return null;
            }).join();
            long end = System.currentTimeMillis();
            log.info("执行多任务操作所花费的时间：" + (end - startTime) + "毫秒");
            poolExecutor.shutdown();
            return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherInfo2);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse<Object>(CustomErrorType.ERROR.getCode(), CustomErrorType.ERROR.getMessage(), null);
        }
    }

    public BaseResponse<Object> getHumanObserveDataAll(String beginTime, String endTime) {

        //参数校验
        if (StringUtils.isEmpty(beginTime) && StringUtils.isEmpty(endTime)) {
            return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
        }
        WeatherData weatherData = new WeatherData();
        String sql = " select * from inceptor_weather.t_text_surface_hour_observation where " +
                "observeTime >= '" + beginTime + "' and observeTime <= '" + endTime + "' ORDER BY observeTime desc limit 1";
        List<WeatherInfo> weatherInfoList = hiveDruidTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<WeatherInfo>(WeatherInfo.class));
        WeatherInfo weatherInfo = null;
        if (weatherInfoList != null && weatherInfoList.size() > 0) {
            weatherInfo = weatherInfoList.get(0);
            if (weatherInfo != null) {
                weatherData.setWeatherInfo(weatherInfo);
                log.info("查询到的结果为\t" + weatherInfo);
            }
        }

        String sql2 = "select * from inceptor_weather.t_text_position_observation  where " +
                "createtime >= '" + beginTime + "' and createtime <= '" + endTime + "' ORDER BY createtime desc limit 1";
        List<WeatherPositionObsrv> positionObsrvList = hiveDruidTemplate.query(sql2, new Object[]{}, new BeanPropertyRowMapper<WeatherPositionObsrv>(WeatherPositionObsrv.class));
        WeatherPositionObsrv obsrv = null;
        if (positionObsrvList != null && positionObsrvList.size() > 0) {
            obsrv = positionObsrvList.get(0);
            weatherData.setWeatherPositionObsrv(obsrv);
            log.info("查询到的结果为\t" + obsrv);
        }
        //查询WeatherDrop的数据
        String sql3 = "select id,createtime,sitesid,humidity,temperature,pressure,allcloudamount," +
                "visibility,windspeed,winddirect,phenomenon,insert_time,cloudforms,observeman, dewpointtemperature" +
                " from inceptor_weather.t_text_navi_drop where createtime >= '" + beginTime + "' and createtime <= '" + endTime + "' ORDER BY createtime desc limit 1";
        List<WeatherDrop> weatherDropList = hiveDruidTemplate.query(sql3, new Object[]{}, new BeanPropertyRowMapper<WeatherDrop>(WeatherDrop.class));
        WeatherDrop drop = null;
        if (weatherDropList != null && weatherDropList.size() > 0) {
            drop = weatherDropList.get(0);
            weatherData.setWeatherDrop(drop);
        }

        //判断当前code是否是字符串or汉字
        if(weatherInfo != null){
            if (weatherInfo.getPhenomenon() != null){
                boolean infoCharacter = isContainChinese(weatherInfo.getPhenomenon());
                if (infoCharacter == false) {
                    String hql = "select *  from inceptor_weather.t_dic_phenomenon_code where code = " + weatherInfo.getPhenomenon() + "";
                    List<DirCodeEntity> dirCodeEntityList = hiveDruidTemplate.query(hql, new Object[]{}, new BeanPropertyRowMapper<DirCodeEntity>(DirCodeEntity.class));
                    if (dirCodeEntityList != null && dirCodeEntityList.size() > 0) {
                        DirCodeEntity dirCodeEntity = dirCodeEntityList.get(0);
                        if (dirCodeEntity.getDisplayName() != null) {
                            weatherInfo.setPhenomenon(dirCodeEntity.getDisplayName());
                        }
                    }
                }
            }
        }
       if (drop != null){
           if (drop.getPhenomenon() != null || drop.getPhenomenon() != ""){
               boolean b = hasChineseCharacter(drop.getPhenomenon());
               if (b == false) {
                   String hql = "select *  from inceptor_weather.t_dic_phenomenon_code where code = " + drop.getPhenomenon() + "";
                   List<DirCodeEntity> dirCodeEntityList = hiveDruidTemplate.query(hql, new Object[]{}, new BeanPropertyRowMapper<DirCodeEntity>(DirCodeEntity.class));
                   if (dirCodeEntityList != null && dirCodeEntityList.size() > 0) {
                       DirCodeEntity dirCodeEntity = dirCodeEntityList.get(0);
                       if (dirCodeEntity != null) {
                           drop.setPhenomenon(dirCodeEntity.getDisplayName());
                       }
                   }
               }
           }

       }
        if (obsrv != null) {
            if (obsrv.getPhenomenon() != null || obsrv.getPhenomenon() != ""){
                boolean c = hasChineseCharacter(obsrv.getPhenomenon());
                if (c == false) {
                    String hql = "select *  from inceptor_weather.t_dic_phenomenon_code where code =" + obsrv.getPhenomenon() + "";
                    List<DirCodeEntity> dirCodeEntityList = hiveDruidTemplate.query(hql, new Object[]{}, new BeanPropertyRowMapper<DirCodeEntity>(DirCodeEntity.class));
                    if (dirCodeEntityList != null && dirCodeEntityList.size() > 0) {
                        DirCodeEntity dirCodeEntity = dirCodeEntityList.get(0);
                        if (dirCodeEntity.getDisplayName() != null) {
                            obsrv.setPhenomenon(dirCodeEntity.getDisplayName());
                        }
                    }
                }
            }

        }
        if (weatherInfo == null && drop == null && obsrv == null ){
            return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), "查询时间段内没有数据", weatherData);
        }
        return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherData);
    }

    @Override
    public BaseResponse<Object> getHxAndUpperObservationData(String beginTime, String endTime) {

        //参数校验
        if (StringUtils.isEmpty(beginTime) && StringUtils.isEmpty(endTime)) {
            return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
        }
        WeatherHxData weatherData = new WeatherHxData();
        String sql = "select id,stationno, soundtime, height, winddirect, windspeed, pressure, temperature, humidity from inceptor_weather.t_text_upper_observation where " +
                "soundtime >= '" + beginTime + "' and soundtime <= '" + endTime + "' ORDER BY soundtime desc limit 1";
        List<WeatherUpper> weatherUpperList = hiveDruidTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<WeatherUpper>(WeatherUpper.class));
        WeatherUpper weatherUpper = null;
        if (weatherUpperList != null && weatherUpperList.size() > 0) {
            weatherUpper = weatherUpperList.get(0);
            if (weatherUpper != null) {
                log.info("查询到的结果为\t" + weatherUpper);
                weatherData.setWeatherUpper(weatherUpper);
            }
        }
        String sql2 = " select data_id, data_station, data_time, data_layer, data_height,\n" +
                "data_wind_direction, data_wind_speed, data_wind_2av_direction,  \n" +
                "data_wind_2av_speed, data_wind_10av_direction, data_wind_10av_speed from inceptor_weather.t_text_hx_data where " +
                "data_time >= '" + beginTime + "' and data_time <= '" + endTime + "' ORDER BY data_time desc limit 1";
        List<WeatherHx> weatherHxList = hiveDruidTemplate.query(sql2, new Object[]{}, new BeanPropertyRowMapper<WeatherHx>(WeatherHx.class));
        WeatherHx weatherHx = null;
        if (weatherHxList != null && weatherHxList.size() > 0) {
            weatherHx = weatherHxList.get(0);
            if (weatherUpper != null) {
                weatherData.setWeatherHx(weatherHx);
                log.info("查询到的结果为\t" + weatherHx);
            }
        }
        return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherData);
    }


    @Override
    public BaseResponse<Object> getHxData(String beginTime, String endTime) {

        //参数校验
        if (StringUtils.isEmpty(beginTime) && StringUtils.isEmpty(endTime)) {
            return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
        }
        WeatherHx weatherHx = null;
        String sql = " select data_id,data_station, data_time, data_layer, data_height,\n" +
                "data_wind_direction, data_wind_speed, data_wind_2av_direction,  \n" +
                "data_wind_2av_speed, data_wind_10av_direction, data_wind_10av_speed from inceptor_weather.t_text_hx_data where " +
                "data_time >= '" + beginTime + "' and data_time <= '" + endTime + "' ORDER BY data_time desc limit 1";
        List<WeatherHx> weatherHxList = hiveDruidTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<WeatherHx>(WeatherHx.class));
        if (weatherHxList != null && weatherHxList.size() > 0) {
            weatherHx = weatherHxList.get(0);
            if (weatherHx != null) {
                log.info("查询到的结果为\t" + weatherHx);
                return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherHx);
            }
        }
        return null;
    }

    @Override
    public BaseResponse<Object> getUpperObservationData(String beginTime, String endTime) {

        //参数校验
        if (StringUtils.isEmpty(beginTime) && StringUtils.isEmpty(endTime)) {
            return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
        }
        //查询
        String sql = "select id,stationno, soundtime, height, winddirect, windspeed, pressure, temperature, humidity from inceptor_weather.t_text_upper_observation where " +
                "soundtime >= '" + beginTime + "' and soundtime <= '" + endTime + "' ORDER BY soundtime desc limit 1";
        List<WeatherUpper> weatherUpperList = hiveDruidTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<WeatherUpper>(WeatherUpper.class));
        if (weatherUpperList != null && weatherUpperList.size() > 0) {
            WeatherUpper weatherUpper = weatherUpperList.get(0);
            if (weatherUpper != null) {
                log.info("查询到的结果为\t" + weatherUpper);
                return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherUpper);
            }
        }
        return null;
    }

    //判断是否包含中文
    private boolean isContainChinese(String str){
        Pattern pattern = Pattern.compile("[\u4e00 - \u9fa5]");
        if (pattern.matcher(str).find()) {
            return true;
        }
        return false;
    }

    public static boolean isChineseCharacter(char c){
        return c >= 0x4E00 && c<= 0x9FA5;
    }
    public static boolean hasChineseCharacter(String str){
        if (str == null)
            return false;
        for (char c : str.toCharArray()){
            if (isChineseCharacter(c))
                return true;
        }
        return false;
    }

    @Override
    public BaseResponse<Object> getUpperObservationAndHxDataOfStation9(String beginTime, String endTime) {
        //参数校验
        if (StringUtils.isEmpty(beginTime) && StringUtils.isEmpty(endTime)) {
            return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
        }
        WeatherObsrvHxData weatherData = new WeatherObsrvHxData();
        String sql = " select phenomenon,displayName,allCloudAmount\n" +
                "from t_text_surface_hour_observation sur\n" +
                "left join t_dic_phenomenon_code phe\n" +
                "on cast(sur.phenomenon as integer)=cast(phe.code as integer) where " +
                "observeTime >= '" + beginTime + "' and observeTime <= '" + endTime + "' ORDER BY observeTime desc limit 1";
        List<WeatherJoinData> weatherInfoList = hiveDruidTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<WeatherJoinData>(WeatherJoinData.class));
        WeatherJoinData weatherJoinData = null;
        if (weatherInfoList != null && weatherInfoList.size() > 0) {
            weatherJoinData = weatherInfoList.get(0);
            if (weatherJoinData != null) {
                weatherData.setWeatherJoinData(weatherJoinData);
                log.info("查询到的结果为\t" + weatherJoinData);
            }
        }
        String sql2 = " select data_time,\n" +
                "       data_temperature,\n" +
                "       data_humidity,\n" +
                "       data_wind_direction,\n" +
                "       data_wind_speed,\n" +
                "       data_pressure,\n" +
                "       data_visibility from inceptor_weather.t_text_hx_data where " +
                "data_time >= '" + beginTime + "' and data_time <= '" + endTime + "' and data_station = 10 ORDER BY data_time desc limit 1";
        List<WeatherHx> weatherHxList = hiveDruidTemplate.query(sql2, new Object[]{}, new BeanPropertyRowMapper<WeatherHx>(WeatherHx.class));
        WeatherHx weatherHx = null;
        if (weatherHxList != null && weatherHxList.size() > 0) {
            weatherHx = weatherHxList.get(0);
            if (weatherJoinData != null) {
                weatherData.setWeatherHx(weatherHx);
                log.info("查询到的结果为\t" + weatherHx);
            }
        }
        return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherData);
    }

    /**
     * 9号观测站mysql接口
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public WeatherHx queryWeatherRawHxData(String beginTime, String endTime) {
        return  weatherMapper.queryWeatherHxRew(beginTime, endTime);
    }

    @Override
    public WeatherJoinData queryWeatherRawUpperObservationData(String beginTime, String endTime) {
        return weatherMapper.queryWeatherObsrvRew(beginTime,endTime);
    }
}
