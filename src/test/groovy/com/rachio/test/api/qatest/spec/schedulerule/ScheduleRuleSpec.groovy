package com.rachio.test.api.qatest.spec.schedulerule

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.models.schedulerule.ScheduleRule
import spock.lang.Specification

import static com.rachio.test.api.qatest.util.ARandom.aRandom

class ScheduleRuleSpec extends Specification {

    static final String SCHEDULE_ITEM_ID = 'ad0dd122-6039-4f1a-be46-b2e677f1081c';

    def "should get schedule rule entity"() {
        when:
        ScheduleRule scheduleRule = Client.get("/schedulerule/${SCHEDULE_ITEM_ID}", ScheduleRule)

        then:
        assert scheduleRule.id == SCHEDULE_ITEM_ID
    }

    def "should skip a schedule rule"() {
        when:
        Client.put("/schedulerule/skip", [id: SCHEDULE_ITEM_ID])

        then:
        noExceptionThrown()  // I queried schedulerule before and after but see no difference, not sure what to check
    }

    def "should start a schedule rule"() {
        when:
        Client.put("/schedulerule/start", [id: SCHEDULE_ITEM_ID])

        then:
        noExceptionThrown()  // I queried schedulerule before and after but see no difference, not sure what to check
    }

    def "should make seasonal adjustments to a schedule rule"() {
        given:
        ScheduleRule scheduleRuleBefore = Client.get("/schedulerule/${SCHEDULE_ITEM_ID}", ScheduleRule)

        when:
        Client.put("/schedulerule/seasonal_adjustment", [id: SCHEDULE_ITEM_ID, adjustment: aRandom.seasonalAdjustment()])
        ScheduleRule scheduleRuleAfter = Client.get("/schedulerule/${SCHEDULE_ITEM_ID}", ScheduleRule)

        then:
        assert scheduleRuleAfter.totalDuration != scheduleRuleBefore.totalDuration
        assert scheduleRuleAfter.totalDurationNoCycle != scheduleRuleBefore.totalDurationNoCycle
    }
}
