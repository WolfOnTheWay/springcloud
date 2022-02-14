package com.sdt.fsc.entity.weather;

import lombok.Data;
import java.io.Serializable;

@Data
public class WeatherData implements Serializable {
    private WeatherDrop weatherDrop;
    private WeatherPositionObsrv weatherPositionObsrv;
    private WeatherInfo weatherInfo;


}
