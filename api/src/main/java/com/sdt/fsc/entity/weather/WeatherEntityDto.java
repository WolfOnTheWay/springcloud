package com.sdt.fsc.entity.weather;

import lombok.Data;

@Data
public class WeatherEntityDto {


    private Integer stationNo;
    private String observeTime;

    private String phenomenon;

    private Integer windSpeed;

    private Long temperature;

    private Long pressure;

    private Long relativeHumidity;

    private Long absoluteHumidity;

    private String allCloudAmount;
}
