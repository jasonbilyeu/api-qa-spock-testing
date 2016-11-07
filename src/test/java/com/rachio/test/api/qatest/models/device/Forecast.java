package com.rachio.test.api.qatest.models.device;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Forecast {
    private DateTime localizedTimeStamp;
    private Double precipIntensity;
    private Double precipProbability;
    private Double temperatureMin;
    private Double temperatureMax;
    private Double windSpeed;
    private Double humidity;
    private Double cloudCover;
    private Double dewPoint;
    private String weatherType;
    private String dailyWeatherType;
    private String unitType;
    private String weatherSummary;
    private String iconUrl;
    private Object icons;
    private Double calculatedPrecip;
    private String prettyTime;
    private Integer time;
}
