package com.rachio.test.api.qatest.models.device;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ScheduleItem {
    private DateTime absoluteStartDate;
    private DateTime date;
    private Date iso8601Date;
    private List<ScheduleZone> zones = new ArrayList<ScheduleZone>();
    private String scheduleRuleId;
    private Boolean cycleSoak;
    private Integer totalDuration;
    private String scheduleType;
    private Integer startHour;
    private Integer startMinute;
    private Integer totalCycleCount;
}
