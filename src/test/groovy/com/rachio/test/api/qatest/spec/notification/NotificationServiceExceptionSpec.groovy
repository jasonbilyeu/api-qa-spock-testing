package com.rachio.test.api.qatest.spec.notification

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.client.exceptions.ForbiddenException
import com.rachio.test.api.qatest.client.exceptions.PreconditionFailedException
import com.rachio.test.api.qatest.client.exceptions.UnauthorizedException
import com.rachio.test.api.qatest.models.notification.Webhook
import com.rachio.test.api.qatest.spec.device.DeviceServiceSpec
import spock.lang.Specification

import static com.rachio.test.api.qatest.util.ARandom.aRandom

class NotificationServiceExceptionSpec extends Specification {

    static String WEBHOOK_URL = 'http://12d85054.ngrok.io/'

    def cleanup() {
        List<Webhook> webhooksForDevice = Client.get("/notification/${DeviceServiceSpec.DEVICE_ID}/webhook", List, [:], Webhook)

        webhooksForDevice.each {
            Client.delete("/notification/webhook/${it.id}")
        }
    }

    def "should throw 403 when attempting to get list of Webhooks for non-existing device"() {
        when:
        Client.get("/notification/${aRandom.uuid()}/webhook", List, [:], Webhook)

        then:
        thrown(ForbiddenException)
    }

    def "should throw 401 when attempting to get non-existent webhook by id"() {
        when:
        Client.get("/notification/webhook/${aRandom.uuid()}", Webhook)

        then:
        thrown(UnauthorizedException)
    }

    def "should throw 401 when attempting to create an empty webhook for a device"() {
        when:
        Client.post("/notification/webhook", [:], Webhook)

        then:
        thrown(UnauthorizedException)
    }

    def "should throw 403 when attempting to update a non-existing webhook for a device"() {
        when:
        Client.put("/notification/webhook", [id:aRandom.uuid().toString()], Webhook)

        then:
        thrown(ForbiddenException)
    }

    def "should throw 403 when attempting to delete a non-existing webhook for a device"() {
        when:
        Client.delete("/notification/webhook/${aRandom.uuid()}")

        then:
        thrown(PreconditionFailedException)  // is this correct?
    }
}
