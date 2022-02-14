package com.sdt.fsc.entity.weather;

import java.io.Serializable;

public class WeatherUpper implements Serializable {
    private Integer id;
    private Integer stationno;
    private String soundtime;
    private Integer height;
    private Integer winddirect;
    private Integer windspeed;
    private Integer pressure;
    private Integer temperature;
    private Integer humidity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStationno() {
        return stationno;
    }

    public void setStationno(Integer stationno) {
        this.stationno = stationno;
    }

    public String getSoundtime() {
        return soundtime;
    }

    public void setSoundtime(String soundtime) {
        this.soundtime = soundtime;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(Integer winddirect) {
        this.winddirect = winddirect;
    }

    public Integer getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(Integer windspeed) {
        this.windspeed = windspeed;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}
