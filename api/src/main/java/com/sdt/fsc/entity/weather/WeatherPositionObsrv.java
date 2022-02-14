package com.sdt.fsc.entity.weather;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WeatherPositionObsrv implements Serializable {
    private Integer id;
    private String createTime;
    private String sitesID;
    private String observeMan;
    private String phenomenon;
    private Long temperature;
    private String relativeHumidity;
    private String allCloudAmount;
    private Long visibility;
    private String windDirect;
    private Long windSpeed;
    private String mediumLowAmount;
    private String cloudForms;
    private Long dewPointTemperature;
    private String absoluteHumidity;
    private Long pressure;
    private Float significantWaveHeight;

}
