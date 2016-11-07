package com.rachio.test.api.qatest.models.schedulerule;

import com.rachio.test.api.qatest.models.device.ScheduleZone;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScheduleRule {
    private String id;
    private List<ScheduleZone> zones = new ArrayList<ScheduleZone>();
    private List<String> scheduleJobTypes = new ArrayList<String>();
    private String summary;
    private Boolean rainDelay;
    private Boolean waterBudget;
    private String cycleSoakStatus;
    private DateTime startDate;
    private String name;
    private Boolean enabled;
    private Integer totalDuration;
    private Double weatherIntelligenceSensitivity;
    private Double seasonalAdjustment;
    private Integer totalDurationNoCycle;
    private Integer cycles;
    private Boolean cycleSoak;
    private String externalName;
}
