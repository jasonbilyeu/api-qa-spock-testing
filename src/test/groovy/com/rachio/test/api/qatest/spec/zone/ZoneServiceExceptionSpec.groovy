package com.rachio.test.api.qatest.spec.zone

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.client.exceptions.BadRequestException
import com.rachio.test.api.qatest.client.exceptions.ForbiddenException
import com.rachio.test.api.qatest.models.zone.Zone
import spock.lang.Specification

import static com.rachio.test.api.qatest.util.ARandom.aRandom

class ZoneServiceExceptionSpec extends Specification {

    def "should throw 403 error when attempting to select a non-existing zone"() {
        when:
        Client.get("/zone/${aRandom.uuid()}", Zone)

        then:
        thrown(ForbiddenException)
    }

    def "should throw 400 error when attempting to start watering a non-existing zone"() {
        when:
        Client.put("/zone/start", [id: aRandom.uuid(), duration: aRandom.wateringDuration()])

        then:
        thrown(BadRequestException)
    }

    def "should throw 400 error when attempting to start watering multiple non-existing zones"() {
        when:
        Client.put("/zone/start_multiple", [zones: [
                [id: aRandom.uuid(), duration: aRandom.wateringDuration(), sortOrder: 1],
                [id: aRandom.uuid(), duration: aRandom.wateringDuration(), sortOrder: 2]
        ]])

        then:
        thrown(BadRequestException)
    }
}
