package com.sdt.fsc.service.contract.weather;

import com.sdt.fsc.common.BaseResponse;
import com.sdt.fsc.entity.weather.*;

import java.util.List;

public interface WeatherService {
    /**
     * 查询多条code
     * @param codeList
     * @return
     */
    List<WeatherEntity> queryWeatherCodeName(List<Integer> codeList);


    WeatherInfo queryWeatherAll(String startTime, String endTime);
    WeatherDrop queryWeatherDrop(String startTime, String endTime);
    WeatherPositionObsrv queryWeatherObsrv(String startTime, String endTime);
    WeatherUpper queryWeatherUpper(String startTime, String endTime);
    WeatherHx queryWeatherHx(String startTime, String endTime);

    BaseResponse<Object> getHumanObserveData1(String beginTime, String endTime);

    BaseResponse<Object> getHumanObserveDataAll(String beginTime, String endTime);
    BaseResponse<Object> getHxAndUpperObservationData(String beginTime, String endTime);
    BaseResponse<Object> getHxData(String beginTime, String endTime);
    BaseResponse<Object> getUpperObservationData(String beginTime, String endTime);
    BaseResponse<Object> getUpperObservationAndHxDataOfStation9(String beginTime, String endTime);
    //9号观测站mysql接口
    WeatherHx queryWeatherRawHxData(String beginTime, String endTime);
    WeatherJoinData queryWeatherRawUpperObservationData(String beginTime, String endTime);
}
