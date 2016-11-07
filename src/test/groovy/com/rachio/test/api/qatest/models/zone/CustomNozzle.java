package com.rachio.test.api.qatest.models.zone;

import lombok.Data;

@Data
public class CustomNozzle {
    private String name;
    private String imageUrl;
    private String category;
    private Double inchesPerHour;
}
