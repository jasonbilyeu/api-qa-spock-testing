package com.rachio.test.api.qatest.models.device;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Current {
    private DateTime localizedTimeStamp;
    private Double precipIntensity;
    private Double precipProbability;
    private Double humidity;
    private Double cloudCover;
    private String weatherType;
    private Double windSpeed;
    private Double dewPoint;
    private String unitType;
    private String dailyWeatherType;
    private String weatherStationId;
    private Double currentTemperature;
    private String weatherSummary;
    private String iconUrl;
    private Object icons;
    private Double calculatedPrecip;
    private String prettyTime;
    private Integer time;
}
