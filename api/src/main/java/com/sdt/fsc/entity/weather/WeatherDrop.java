package com.sdt.fsc.entity.weather;

import java.io.Serializable;
import java.util.Date;

public class WeatherDrop implements Serializable {

    private Integer id;
    private String createTime;
    private String sitesID;
    private String humidity;
    private Float temperature;
    private Float pressure;
    private Integer allCloudAmount;
    private Float visibility;
    private Float windSpeed;
    private String windDirect;
    private String phenomenon;
    private Date insert_time;
    private String cloudForms;
    private String observeMan;
    private Float dewPointTemperature;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSitesID() {
        return sitesID;
    }

    public void setSitesID(String sitesID) {
        this.sitesID = sitesID;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getPressure() {
        return pressure;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public Integer getAllCloudAmount() {
        return allCloudAmount;
    }

    public void setAllCloudAmount(Integer allCloudAmount) {
        this.allCloudAmount = allCloudAmount;
    }

    public Float getVisibility() {
        return visibility;
    }

    public void setVisibility(Float visibility) {
        this.visibility = visibility;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirect() {
        return windDirect;
    }

    public void setWindDirect(String windDirect) {
        this.windDirect = windDirect;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public String getCloudForms() {
        return cloudForms;
    }

    public void setCloudForms(String cloudForms) {
        this.cloudForms = cloudForms;
    }

    public String getObserveMan() {
        return observeMan;
    }

    public void setObserveMan(String observeMan) {
        this.observeMan = observeMan;
    }

    public Float getDewPointTemperature() {
        return dewPointTemperature;
    }

    public void setDewPointTemperature(Float dewPointTemperature) {
        this.dewPointTemperature = dewPointTemperature;
    }
}
