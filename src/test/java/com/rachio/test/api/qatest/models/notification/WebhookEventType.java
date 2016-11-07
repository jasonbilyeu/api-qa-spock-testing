package com.rachio.test.api.qatest.models.notification;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class WebhookEventType {
    private DateTime createDate;
    private DateTime lastUpdateDate;
    private Integer id;
    private String name;
    private String description;
    private String type;
}
