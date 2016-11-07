package com.rachio.test.api.qatest.models.device;

import com.rachio.test.api.qatest.models.schedulerule.ScheduleRule;
import com.rachio.test.api.qatest.models.zone.Zone;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@Data
public class Device {
    private DateTime createDate;
    private String id;
    private String status;
    private List<Zone> zones = new ArrayList<Zone>();
    private String timeZone;
    private Double latitude;
    private Double longitude;
    private String zip;
    private String name;
    private List<ScheduleRule> scheduleRules = new ArrayList<ScheduleRule>();
    private String serialNumber;
    private DateTime rainDelayExpirationDate;
    private DateTime rainDelayStartDate;
    private String macAddress;
    private Double elevation;
    private List<Object> webhooks = new ArrayList<Object>();
    private Boolean paused;
    private Boolean on;
    private List<Object> flexScheduleRules = new ArrayList<Object>();
    private String model;
    private String scheduleModeType;
    private Integer utcOffset;
}
