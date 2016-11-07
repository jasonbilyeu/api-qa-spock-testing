package com.rachio.test.api.qatest.models.device;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ForecastData {
    private Current current;
    private List<Forecast> forecast = new ArrayList<Forecast>();
}
