package com.rachio.test.api.qatest.models.device;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class DeviceCurrentSchedule {
    private String deviceId;
    private String scheduleId;
    private String type;
    private String status;
    private DateTime startDate;
    private Integer duration;
    private String zoneId;
    private DateTime zoneStartDate;
    private Integer zoneDuration;
    private Integer cycleCount;
    private Integer totalCycleCount;
    private Boolean cycling;
    private Integer durationNoCycle;
}
