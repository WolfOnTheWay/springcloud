package com.sdt.fsc.entity.search;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wxh
 * orc 表 通用字段
 */
public class OrcTable implements Serializable {
    private static final long serialVersionUID = 2465773156770432185L;
    private String id;
    private String key;
    private String device_id;
    private Double value;
    private Long timestamps;
    private String base;
    private String area;
    private String unit;
    private String system;
    private String subsystem;
    private String device_type;
    private String device_sn;
    private String metric;
    private String gateway_sn;
    private String frame_sn;
    private String master_standby;
    private Date parttime;
    private Date dates;
    private Long timestamps1;
    public Long getTimestamps1() {
        return timestamps1;
    }

    public void setTimestamps1(Long timestamps1) {
        this.timestamps1 = timestamps1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Long timestamps) {
        this.timestamps = timestamps;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_sn() {
        return device_sn;
    }

    public void setDevice_sn(String device_sn) {
        this.device_sn = device_sn;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getGateway_sn() {
        return gateway_sn;
    }

    public void setGateway_sn(String gateway_sn) {
        this.gateway_sn = gateway_sn;
    }

    public String getFrame_sn() {
        return frame_sn;
    }

    public void setFrame_sn(String frame_sn) {
        this.frame_sn = frame_sn;
    }

    public String getMaster_standby() {
        return master_standby;
    }

    public void setMaster_standby(String master_standby) {
        this.master_standby = master_standby;
    }

    public Date getParttime() {
        return parttime;
    }

    public void setParttime(Date parttime) {
        this.parttime = parttime;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }
}
