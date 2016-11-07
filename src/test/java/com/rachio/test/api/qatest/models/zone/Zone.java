package com.rachio.test.api.qatest.models.zone;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Zone {
    private String id;
    private Integer zoneNumber;
    private String name;
    private Boolean enabled;
    private CustomNozzle customNozzle;
    private CustomSoil customSoil;
    private CustomSlope customSlope;
    private CustomCrop customCrop;
    private CustomShade customShade;
    private Double availableWater;
    private Double rootZoneDepth;
    private Double managementAllowedDepletion;
    private Double efficiency;
    private Integer yardAreaSquareFeet;
    private String imageUrl;
    private Integer lastWateredDuration;
    private DateTime lastWateredDate;
    private Boolean scheduleDataModified;
    private Integer fixedRuntime;
    private WateringAdjustmentRuntimes wateringAdjustmentRuntimes;
    private Integer runtimeNoMultiplier;
    private Double saturatedDepthOfWater;
    private Double depthOfWater;
    private Integer maxRuntime;
    private Integer runtime;
}