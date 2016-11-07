package com.rachio.test.api.qatest.spec.zone

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.models.zone.Zone
import org.joda.time.DateTime
import spock.lang.Ignore
import spock.lang.Specification

class ZoneServiceSpec extends Specification {
    static final String ZONE_ID = '1b33d92e-39a3-4951-83bc-a40f71a9c808'

    def "should get back a single zone"() {
        when:
        Zone zone = Client.get("/zone/${ZONE_ID}", Zone)

        then:
        zone.id == ZONE_ID
    }

    @Ignore("This test sporadically fails.  Need to troubleshoot further.")
    def "should be able to start watering in a zone"() {
        when:
        Client.put("/zone/start", [id: ZONE_ID, duration: 1])
        sleep(1050) // I have to actually let it complete watering before checking the result
        Zone zone = Client.get("/zone/${ZONE_ID}", Zone)

        then:
        zone.lastWateredDuration == 1
        zone.lastWateredDate >= DateTime.now().minusMinutes(1)
    }

    @Ignore("This test sporadically fails.  Need to troubleshoot further.")
    def "should be able to start watering multiple zones"() {
        when:
        Client.put("/zone/start_multiple", [zones: [[id: ZONE_ID, duration: 1, sortOrder: 1]]])
        sleep(1050) // I have to actually let it complete watering before checking the result
        Zone zone = Client.get("/zone/${ZONE_ID}", Zone)

        then:
        zone.lastWateredDuration == 1
        zone.lastWateredDate >= DateTime.now().minusMinutes(1)
    }
}
