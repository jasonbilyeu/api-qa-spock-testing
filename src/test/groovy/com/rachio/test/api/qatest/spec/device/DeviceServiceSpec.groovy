package com.rachio.test.api.qatest.spec.device

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.models.device.Device
import com.rachio.test.api.qatest.models.device.DeviceCurrentSchedule
import com.rachio.test.api.qatest.models.device.ForecastData
import com.rachio.test.api.qatest.models.device.ScheduleItem
import org.joda.time.DateTime
import spock.lang.Specification
import spock.lang.Unroll

class DeviceServiceSpec extends Specification {
    static final String DEVICE_ID = '611e8182-f787-48f0-bf59-508417fa4d27'

    def "should get device entity"() {
        when:
        Device device = Client.get("/device/${DEVICE_ID}", Device)

        then:
        assert device.id == DEVICE_ID
    }

    def "should get device current schedule"() {
        when:
        DeviceCurrentSchedule currentSchedule = Client.get("/device/${DEVICE_ID}/current_schedule", DeviceCurrentSchedule)

        then:
        assert currentSchedule // Can't seem to be able to find a device that has a current schedule..
    }

    def "should retrieve schedule items for device"() {
        when:
        List<ScheduleItem> scheduleItems = Client.get("/device/${DEVICE_ID}/scheduleitem", List, [:], ScheduleItem)

        then:
        assert scheduleItems
    }

    @Unroll
    def "should retrieve forecast data for device in #unitType units"() {
        when:
        ForecastData forecastData = Client.get("/device/${DEVICE_ID}/forecast", ForecastData, [units:unitType])

        then:
        assert forecastData.current
        assert forecastData.current.unitType == unitType
        assert forecastData.forecast.size() > 0

        where:
        unitType << ['US', 'METRIC']
    }

    def "should be able to stop water on a device"() {
        when:
        Client.put("/device/stop_water", [id:DEVICE_ID])
        Device device = Client.get("/device/${DEVICE_ID}", Device)

        then:
        device.status == 'ONLINE'  // not sure what to assert here?
    }

    def "should be able to set a rain delay on a device"() {
        when:
        def duration = 604800
        Client.put("/device/rain_delay", [id:DEVICE_ID, duration:duration])
        Device device = Client.get("/device/${DEVICE_ID}", Device)

        then:
        device.rainDelayExpirationDate >= DateTime.now().plusDays(6)
        device.rainDelayStartDate >= DateTime.now().minusMinutes(1)
    }

    def "should be able to turn device off"() {
        when:
        Client.put("/device/off", [id:DEVICE_ID])
        Device device = Client.get("/device/${DEVICE_ID}", Device)

        then:
        !device.on
    }

    def "should be able to turn device on"() {
        when:
        Client.put("/device/on", [id:DEVICE_ID])
        Device device = Client.get("/device/${DEVICE_ID}", Device)

        then:
        device.on
    }
}
