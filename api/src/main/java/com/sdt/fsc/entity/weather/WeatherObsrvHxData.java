package com.sdt.fsc.entity.weather;

import lombok.Data;

import java.io.Serializable;

@Data
public class WeatherObsrvHxData implements Serializable {
    private WeatherJoinData weatherJoinData;
    private WeatherHx weatherHx;
}
