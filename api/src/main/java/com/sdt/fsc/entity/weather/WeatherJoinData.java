package com.sdt.fsc.entity.weather;

import lombok.Data;

@Data
public class WeatherJoinData {
    private String displayName;
    /**
     * 天气现象
     */
    private String phenomenon;
    /**
     * 总云量
     */
    private String allCloudAmount;
}
