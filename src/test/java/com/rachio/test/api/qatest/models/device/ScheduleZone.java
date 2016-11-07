package com.rachio.test.api.qatest.models.device;

import lombok.Data;

@Data
public class ScheduleZone {
    private String zoneId;
    private Integer zoneNumber;
    private Integer duration;
    private Integer sortOrder;
}
