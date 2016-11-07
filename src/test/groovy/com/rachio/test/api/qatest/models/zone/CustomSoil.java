package com.rachio.test.api.qatest.models.zone;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class CustomSoil {
    private DateTime createDate;
    private DateTime lastUpdateDate;
    private String id;
    private String name;
    private String imageUrl;
    private String category;
    private Double infiltrationRate;
    private Boolean editable;
    private Double percentAvailableWater;
}
