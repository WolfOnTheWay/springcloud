package com.sdt.fsc.entity.weather;

import java.io.Serializable;

/**
 * @author  wangxinhao
 * 气象字典数据
 */
public class WeatherEntity implements Serializable {
    private static final long serialVersionUID = -1533248161702557227L;
    private Integer id;
    /**
     * 对应code码
     */
    private String code;
    /**
     * 简称
     */
    private String displayName;
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
