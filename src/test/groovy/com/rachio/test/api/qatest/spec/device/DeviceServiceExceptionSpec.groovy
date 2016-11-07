package com.rachio.test.api.qatest.spec.device

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.client.exceptions.BadRequestException
import com.rachio.test.api.qatest.client.exceptions.ForbiddenException
import com.rachio.test.api.qatest.models.device.Device
import com.rachio.test.api.qatest.models.device.DeviceCurrentSchedule
import com.rachio.test.api.qatest.models.device.ForecastData
import com.rachio.test.api.qatest.models.device.ScheduleItem
import spock.lang.Specification

import static com.rachio.test.api.qatest.util.ARandom.aRandom

class DeviceServiceExceptionSpec extends Specification {

    def "should throw 403 error if device does not exist"() {
        when:
        Client.get("/person/${aRandom.uuid()}", Device)

        then:
        thrown(ForbiddenException)
    }

    def "should throw 403 exception for non-existing device current schedule"() {
        when:
        Client.get("/device/${aRandom.uuid()}/current_schedule", DeviceCurrentSchedule)

        then:
        thrown(ForbiddenException)
    }

    def "should throw 403 exception for non-existing device schedule items"() {
        when:
        Client.get("/device/${aRandom.uuid()}/scheduleitem", List, [:], ScheduleItem)

        then:
        thrown(ForbiddenException)
    }

    def "should throw 403 exception for non-existing device forecast data"() {
        when:
        Client.get("/device/${aRandom.uuid()}/forecast", ForecastData, [units:'US'])

        then:
        thrown(ForbiddenException)
    }

    def "should throw 400 exception for non-existing device attempt to stop water"() {
        when:
        Client.put("/device/stop_water", [id:aRandom.uuid()])

        then:
        thrown(BadRequestException)
    }

    def "should throw 400 exception for non-existing device attempt to set rain delay"() {
        when:
        Client.put("/device/rain_delay", [id: aRandom.uuid(), duration: aRandom.rainDelayDuration()])

        then:
        thrown(BadRequestException)
    }

    def "should throw 400 exception for attempt to turn off non-existing device"() {
        when:
        Client.put("/device/off", [id:aRandom.uuid()])

        then:
        thrown(BadRequestException)
    }

    def "should throw 400 exception for attempt to turn on non-existing device"() {
        when:
        Client.put("/device/on", [id:aRandom.uuid()])

        then:
        thrown(BadRequestException)
    }
}
