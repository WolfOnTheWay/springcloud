package com.sdt.fsc.controller.weather;


import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.common.CustomErrorType;
import com.sdt.fsc.entity.weather.*;
import com.sdt.fsc.service.contract.weather.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxinhao
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/weather")
@Api(value = "Weather-API", description = "气象数据获取API")
@Slf4j
public class WeatherController {

    @Autowired
    private WeatherService weatherServiceImpl;


//    @GetMapping("/getHumanObserveData1")
//    @ApiOperation(value = "按时间范围检索气象数据")
    public BaseResponse<Object> searchQuery(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                            @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {

        return weatherServiceImpl.getHumanObserveData1(beginTime, endTime);

    }

    @GetMapping("/getHumanObserveDataAll")
    @ApiOperation(value = "按时间范围检索所有气象数据")
    public BaseResponse<Object> searchQueryAll(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                               @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> getHumanObserveDataAll");
        return weatherServiceImpl.getHumanObserveDataAll(beginTime, endTime);

    }

    @GetMapping("/getHxAndUpperObservationData")
    @ApiOperation(value = "按时间范围检索浅层风与高空探测数据")
    public BaseResponse<Object> searchQueryWeather(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                                   @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {

        return weatherServiceImpl.getHxAndUpperObservationData(beginTime, endTime);

    }

    @GetMapping("/getHxData")
    @ApiOperation(value = "按时间范围检索浅层风数据")
    public BaseResponse<Object> searchQueryWeatherHx(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                                   @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {

        return weatherServiceImpl.getHxData(beginTime, endTime);

    }

    @GetMapping("/getUpperObservationData")
    @ApiOperation(value = "按时间范围检索高空探测数据")
    public BaseResponse<Object> searchQueryWeatherUpperObser(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                                     @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {

        return weatherServiceImpl.getUpperObservationData(beginTime, endTime);

    }

    @GetMapping("/getRawHumanObserveDataAll")
    @ApiOperation(value = "按时间范围检索所有原始气象数据")
    public BaseResponse<Object> searchQueryRawDataAll(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                                      @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {
        log.info("==========================================> getHumanObserveRawDataAll");
        try {
            if (StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)) {
                return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
            }
            //查询时间段内的数据
            WeatherInfo weatherInfo = weatherServiceImpl.queryWeatherAll(beginTime, endTime);
            WeatherDrop drop = weatherServiceImpl.queryWeatherDrop(beginTime, endTime);
            WeatherPositionObsrv obsrv = weatherServiceImpl.queryWeatherObsrv(beginTime, endTime);
            if (weatherInfo == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), weatherInfo);
            }
            if (drop == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), drop);
            }
            if (obsrv == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), obsrv);
            }
            //获取code
            String phenomenon = weatherInfo.getPhenomenon();
            String obsrvPhenomenon = obsrv.getPhenomenon();
            String dropPhenomenon = drop.getPhenomenon();
            boolean info = hasChineseCharacter(phenomenon);
            boolean obsrvCharacter = hasChineseCharacter(obsrvPhenomenon);
            boolean dropCharacter = hasChineseCharacter(dropPhenomenon);
            if (StringUtils.isEmpty(phenomenon)) {
                weatherInfo.setPhenomenon("无");
            }
            if (info == false){
                List<Integer> codeList = new ArrayList<>();
                codeList.add(Integer.valueOf(phenomenon));
                List<WeatherEntity> weatherEntities = weatherServiceImpl.queryWeatherCodeName(codeList);
                WeatherEntity weatherEntity = weatherEntities.get(0);
                weatherInfo.setPhenomenon(weatherEntity.getDisplayName());
            }
           //查询 obsrv表
            if (obsrvCharacter == false){
                List<Integer> codeList = new ArrayList<>();
                codeList.add(Integer.valueOf(obsrvPhenomenon));
                List<WeatherEntity> weatherEntities = weatherServiceImpl.queryWeatherCodeName(codeList);
                WeatherEntity weatherEntity = weatherEntities.get(0);
                obsrv.setPhenomenon(weatherEntity.getDisplayName());
            }
            if (dropCharacter == false){
                List<Integer> codeList = new ArrayList<>();
                codeList.add(Integer.valueOf(dropPhenomenon));
                List<WeatherEntity> weatherEntities = weatherServiceImpl.queryWeatherCodeName(codeList);
                WeatherEntity weatherEntity = weatherEntities.get(0);
                drop.setPhenomenon(weatherEntity.getDisplayName());
            }
            WeatherData weatherData = new WeatherData();
            weatherData.setWeatherDrop(drop);
            weatherData.setWeatherInfo(weatherInfo);
            weatherData.setWeatherPositionObsrv(obsrv);
            if (weatherData != null) {
                return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherData);
            }

        } catch (Exception e) {
            log.error("数据异常,errorMsg: {}", e);
            return new BaseResponse<Object>(CustomErrorType.ERROR.getCode(), CustomErrorType.ERROR.getMessage(), null);
        }
        return null;
    }

//    @GetMapping("/getRawHumanObserveData")
//    @ApiOperation(value = "按时间范围检索原始气象数据")
    public BaseResponse<Object> searchQueryRawData(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                                   @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {
        try {
            if (StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)) {
                return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
            }
            //查询时间段内的数据
            WeatherInfo weatherInfo = weatherServiceImpl.queryWeatherAll(beginTime, endTime);
            if (weatherInfo == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), weatherInfo);
            }
            //获取code
            String phenomenon = weatherInfo.getPhenomenon();
            if (StringUtils.isEmpty(phenomenon)) {
                weatherInfo.setPhenomenon("无");
            }
            boolean info = hasChineseCharacter(phenomenon);
            if (info == false){
                List<Integer> codeList = new ArrayList<>();
                codeList.add(Integer.valueOf(phenomenon));
                //查询code 对应的名称
                List<WeatherEntity> weatherEntities = weatherServiceImpl.queryWeatherCodeName(codeList);
                if (CollectionUtils.isEmpty(weatherEntities)) {
                    return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), weatherEntities);
                }
                WeatherEntity weatherEntity = weatherEntities.get(0);
                weatherInfo.setPhenomenon(weatherEntity.getDisplayName());
            }
            if (weatherInfo != null) {
                return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherInfo);
            }

        } catch (Exception e) {
            log.error("数据异常,errorMsg: {}", e);
            return new BaseResponse<Object>(CustomErrorType.ERROR.getCode(), CustomErrorType.ERROR.getMessage(), null);
        }
        return null;
    }

    @GetMapping("/getRawHxAndUpperObservationData")
    @ApiOperation(value = "按时间范围检索原始浅层风与高空探测数据")
    public BaseResponse<Object> searchQueryRawDataUpper(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                                        @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {
        try {
            if (StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)) {
                return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
            }
            //查询时间段内的数据
            WeatherUpper weatherUpper = weatherServiceImpl.queryWeatherUpper(beginTime, endTime);
            WeatherHx weatherHx = weatherServiceImpl.queryWeatherHx(beginTime, endTime);
            if (weatherUpper == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), weatherUpper);
            }
            if (weatherHx == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), weatherHx);
            }
            WeatherHxData weatherData = new WeatherHxData();
            weatherData.setWeatherUpper(weatherUpper);
            weatherData.setWeatherHx(weatherHx);
            if (weatherData != null) {
                return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherData);
            }

        } catch (Exception e) {
            log.error("数据异常,errorMsg: {}", e);
            return new BaseResponse<Object>(CustomErrorType.ERROR.getCode(), CustomErrorType.ERROR.getMessage(), null);
        }
        return null;
    }

    @GetMapping("/getHumanObserveDataOfStation9")
    @ApiOperation(value = "获取9号观测站数据")
    public BaseResponse<Object> searchQueryUpperAndHxOfStation9(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                                            @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {
        return weatherServiceImpl.getUpperObservationAndHxDataOfStation9(beginTime, endTime);

    }

    public static boolean isChineseCharacter(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    public static boolean hasChineseCharacter(String str) {
        if (str == null)
            return false;
        for (char c : str.toCharArray()) {
            if (isChineseCharacter(c))
                return true;
        }
        return false;
    }
    @GetMapping("/getRawHumanObserveDataOfStation9")
    @ApiOperation(value = "按时间范围检索原始9号观测站数据")
    public BaseResponse<Object> searchQueryRawUpperAndHxOfStation9(@ApiParam(value = "开始时间", required = true) @RequestParam("beginTime") String beginTime,
                                                               @ApiParam(value = "结束时间", required = true) @RequestParam("endTime") String endTime) {

        try {
            //参数校验
            if (org.apache.commons.lang3.StringUtils.isEmpty(beginTime) && org.apache.commons.lang3.StringUtils.isEmpty(endTime)) {
                return new BaseResponse<Object>(CustomErrorType.PARAMS_NULL.getCode(), CustomErrorType.PARAMS_NULL.getMessage(), null);
            }
            WeatherObsrvHxData weatherData = new WeatherObsrvHxData();
            WeatherHx weatherHx = weatherServiceImpl.queryWeatherRawHxData(beginTime, endTime);
            weatherData.setWeatherHx(weatherHx);
            WeatherJoinData weatherJoinData = weatherServiceImpl.queryWeatherRawUpperObservationData(beginTime, endTime);
            weatherData.setWeatherJoinData(weatherJoinData);
            if (weatherJoinData == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), weatherJoinData);
            }
            if (weatherHx == null) {
                return new BaseResponse<Object>(CustomErrorType.DATA_IS_NULL.getCode(), CustomErrorType.DATA_IS_NULL.getMessage(), weatherHx);
            }
            if (weatherData != null) {
                return new BaseResponse<Object>(CustomErrorType.SUCCESS.getCode(), CustomErrorType.SUCCESS.getMessage(), weatherData);
            }
        } catch (Exception e) {
            log.error("数据异常,errorMsg: {}", e);
            return new BaseResponse<Object>(CustomErrorType.ERROR.getCode(), CustomErrorType.ERROR.getMessage(), null);
        }
        return null;

    }
}
