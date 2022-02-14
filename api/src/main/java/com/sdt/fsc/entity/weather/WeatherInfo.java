package com.sdt.fsc.entity.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author wangxinhao
 * 气象相信信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfo implements Serializable {
    private static final long serialVersionUID = 7918691230459992847L;
    /**
     * id
     */
    private Integer stationNo;
    /**
     * 时间
     */
    private String observeTime;

    /**
     * 天气现象
     */
    private String phenomenon;

    /**
     * 风速
     */
    private Integer windSpeed;
    /**
     * 气温
     */
    private Long temperature;

    /**
     * 本站气压
     */
    private Long pressure;
    /**
     * 相对湿度
     */
    private Long relativeHumidity;

    /**
     * 绝对湿度
     */
    private Long absoluteHumidity;

    /**
     * 总云量
     */
    private String allCloudAmount;


//    private String windDirect;
//    private Long wetTemperature;
//    private String isRain;
//    private String isIce;
//    private Long vapourPressure;
//    private Long dewPointTemperature;
//    private String visibility;
//    private String verticalVisibility;
//    private String mediumLowAmount;
//    private String lowCloudAmount;
//    private String highCloudForm_1;
//    private String highCloudHeight_1;
//    private String highCloudForm_2;
//    private String highCloudHeight_2;
//    private String highCloudForm_3;
//    private String highCloudHeight_3;
//    private String mediumCloudForm_1;
//    private String mediumCloudHeight_1;
//    private String mediumCloudForm_2;
//    private String mediumCloudHeight_2;
//    private String lowCloudForm_1;
//    private String lowCloudHeight_1;
//    private String lowCloudAmount_1;
//    private String lowCloudForm_2;
//    private String lowCloudHeight_2;
//    private String lowCloudAmount_2;
//    private String lowCloudForm_3;
//    private String lowCloudHeight_3;
//    private String lowCloudAmount_3;
//    private String lowCloudForm_4;
//    private String lowCloudHeight_4;
//    private String lowCloudAmount_4;
//    private String lowCloudForm_5;
//    private String lowCloudHeight_5;
//    private String lowCloudAmount_5;
//    private String observeMan;

}
