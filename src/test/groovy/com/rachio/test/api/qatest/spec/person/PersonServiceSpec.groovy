package com.rachio.test.api.qatest.spec.person

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.models.person.Person
import com.rachio.test.api.qatest.models.person.PersonInfo
import spock.lang.Specification

class PersonServiceSpec extends Specification {
    static final String PERSON_ID = '7d0e4fed-e1f7-46df-b106-f1badf633c4f'

    def "should get person info"() {
        when:
        PersonInfo personInfo = Client.get('/person/info', PersonInfo)

        then:
        assert personInfo.id == PERSON_ID
    }

    def "should get person entity"() {
        when:
        Person person = Client.get("/person/${PERSON_ID}", Person)

        then:
        assert person.id == PERSON_ID
        assert person.devices.size() > 0
        assert person.managedDevices.size() > 0
        assert person.enabled
    }
}
