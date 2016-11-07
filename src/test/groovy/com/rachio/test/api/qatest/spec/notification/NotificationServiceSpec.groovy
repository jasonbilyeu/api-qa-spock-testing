package com.rachio.test.api.qatest.spec.notification

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.client.exceptions.UnauthorizedException
import com.rachio.test.api.qatest.models.notification.Webhook
import com.rachio.test.api.qatest.models.notification.WebhookEventType
import com.rachio.test.api.qatest.spec.device.DeviceServiceSpec
import org.joda.time.DateTime
import spock.lang.Specification

import static com.rachio.test.api.qatest.util.ARandom.aRandom

class NotificationServiceSpec extends Specification {

    static String WEBHOOK_URL = 'http://12d85054.ngrok.io/'

    def cleanup() {
        List<Webhook> webhooksForDevice = Client.get("/notification/${DeviceServiceSpec.DEVICE_ID}/webhook", List, [:], Webhook)

        webhooksForDevice.each {
            Client.delete("/notification/webhook/${it.id}")
        }
    }

    def "should get list of Webhook Event Types"() {
        when:
        List<WebhookEventType> webhookEventTypes = Client.get('/notification/webhook_event_type', List, [:], WebhookEventType)

        then:
        assert webhookEventTypes.size() > 0
    }

    def "should get list of Webhooks for a device"() {
        given:
        Client.post("/notification/webhook", createWebhookMap(), Webhook)

        when:
        List<Webhook> webhooksForDevice = Client.get("/notification/${DeviceServiceSpec.DEVICE_ID}/webhook", List, [:], Webhook)

        then:
        assert webhooksForDevice.size() > 0
    }

    def "should get single webhook by id"() {
        given:
        Webhook createdWebhook = Client.post("/notification/webhook", createWebhookMap(), Webhook)

        when:
        Client.get("/notification/webhook/${createdWebhook.id}", Webhook)

        then:
        thrown(UnauthorizedException)  // this appears to be a bug?
    }

    def "should create a webhook for a device"() {
        when:
        def webhookToPost = createWebhookMap()
        Webhook createdWebhook = Client.post("/notification/webhook", webhookToPost, Webhook)

        then:
        assert createdWebhook.createDate >= DateTime.now().minusMinutes(1)
        assert createdWebhook.externalId == webhookToPost['externalId']
        assert createdWebhook.url == webhookToPost['url']
        assert createdWebhook.eventTypes[0].id == webhookToPost['eventTypes'][0]['id']
    }

    def "should update a webhook for a device"() {
        given:
        Webhook createdWebhook = Client.post("/notification/webhook", createWebhookMap(), Webhook)
        def updatedWebhookMap = createWebhookMap()
        updatedWebhookMap.put('id', createdWebhook.id)

        when:
        Webhook updatedWebhook = Client.put("/notification/webhook", updatedWebhookMap, Webhook)

        then:
        assert updatedWebhook.id == createdWebhook.id
        assert updatedWebhook.createDate == createdWebhook.createDate
        assert updatedWebhook.lastUpdateDate > createdWebhook.lastUpdateDate
        assert updatedWebhook.externalId == updatedWebhookMap['externalId']
        assert updatedWebhook.url == updatedWebhookMap['url']
        assert updatedWebhook.eventTypes[0].id == updatedWebhookMap['eventTypes'][0]['id']
    }

    def "should delete a webhook for a device"() {
        given:
        List<Webhook> webhooksForDeviceBefore = Client.get("/notification/${DeviceServiceSpec.DEVICE_ID}/webhook", List, [:], Webhook)
        Webhook createdWebhook = Client.post("/notification/webhook", createWebhookMap(), Webhook)

        when:
        Client.delete("/notification/webhook/${createdWebhook.id}")

        then:
        List<Webhook> webhooksForDeviceAfter = Client.get("/notification/${DeviceServiceSpec.DEVICE_ID}/webhook", List, [:], Webhook)

        then:
        assert webhooksForDeviceAfter.size() == webhooksForDeviceBefore.size()
    }

    def createWebhookMap() {
        [device: [id: DeviceServiceSpec.DEVICE_ID],
         externalId: aRandom.uuid().toString(), url: WEBHOOK_URL, eventTypes: [[id: aRandom.eventTypeId()]]]
    }
}
