package com.sdt.fsc.mapper.mysqlmapper;

import com.sdt.fsc.entity.weather.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.WeakHashMap;

/**
 * @author wangxinhao
 * 气象字典数据
 */
public interface WeatherMapper {
    /**
     * 查询code
     *
     * @param codeList
     * @return
     */
    List<WeatherEntity> queryWeatherCodeName(@Param("codeList") List<Integer> codeList);

    /**
     * 查询气象详情数据
     *
     * @param startTime
     * @param endTime
     * @return
     */
    WeatherInfo queryWeatherAll(@Param("startTime") String startTime, @Param("endTime") String endTime);
    WeatherDrop queryWeatherDrop(@Param("startTime") String startTime, @Param("endTime") String endTime);
    WeatherPositionObsrv queryWeatherObsrv(@Param("startTime") String startTime, @Param("endTime") String endTime);
    WeatherUpper queryWeatherUpper(@Param("startTime") String startTime, @Param("endTime") String endTime);
    WeatherHx queryWeatherHx(@Param("startTime") String startTime, @Param("endTime") String endTime);

    WeatherHx queryWeatherHxRew(@Param("startTime") String startTime, @Param("endTime") String endTime);
    WeatherJoinData queryWeatherObsrvRew(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
