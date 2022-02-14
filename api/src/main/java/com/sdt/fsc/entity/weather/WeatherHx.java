package com.sdt.fsc.entity.weather;

import lombok.Data;

@Data
public class WeatherHx {
    private String data_time;
    private Integer data_temperature;
    private Integer data_humidity;
    private Integer data_wind_direction;
    private Integer data_wind_speed;
    private Integer data_visibility;
    private Integer data_pressure;
    private Integer data_id;
    private int data_station;
    private Integer data_layer;
    private Integer data_height;
    private Integer data_wind_2av_direction;
    private Integer data_wind_2av_speed;
    private Integer data_wind_10av_direction;
    private Integer data_wind_10av_speed;


}
