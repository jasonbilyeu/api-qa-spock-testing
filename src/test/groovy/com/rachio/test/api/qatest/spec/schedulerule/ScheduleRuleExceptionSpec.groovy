package com.rachio.test.api.qatest.spec.schedulerule

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.client.exceptions.BadRequestException
import com.rachio.test.api.qatest.client.exceptions.ForbiddenException
import com.rachio.test.api.qatest.models.schedulerule.ScheduleRule
import spock.lang.Specification

import static com.rachio.test.api.qatest.util.ARandom.aRandom

class ScheduleRuleExceptionSpec extends Specification {

    static final String SCHEDULE_ITEM_ID = 'ad0dd122-6039-4f1a-be46-b2e677f1081c';

    def "should throw 403 when attempting to get non-existing schedule rule"() {
        when:
        Client.get("/schedulerule/${aRandom.uuid()}", ScheduleRule)

        then:
        thrown(ForbiddenException)
    }

    def "should throw 400 when attempting to skip non-existing schedule rule"() {
        when:
        Client.put("/schedulerule/skip", [id: aRandom.uuid()])

        then:
        thrown(BadRequestException)
    }

    def "should throw 400 when attempting to start non-existing schedule rule"() {
        when:
        Client.put("/schedulerule/start", [id: aRandom.uuid()])

        then:
        thrown(BadRequestException)
    }

    def "should throw 400 when attempting to make seasonal adjustments to non-existing schedule rule"() {
        when:
        Client.put("/schedulerule/seasonal_adjustment", [id: aRandom.uuid(), adjustment: aRandom.seasonalAdjustment()])

        then:
        thrown(BadRequestException)
    }
}
