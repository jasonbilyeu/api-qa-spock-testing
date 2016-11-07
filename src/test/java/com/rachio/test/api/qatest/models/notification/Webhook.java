package com.rachio.test.api.qatest.models.notification;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@Data
public class Webhook {
    private DateTime createDate;
    private DateTime lastUpdateDate;
    private String id;
    private String url;
    private List<WebhookEventType> eventTypes = new ArrayList<WebhookEventType>();
    private String externalId;
}
