package com.rachio.test.api.qatest.spec.person

import com.rachio.test.api.qatest.client.Client
import com.rachio.test.api.qatest.client.exceptions.ForbiddenException
import com.rachio.test.api.qatest.models.person.Person
import spock.lang.Specification

import static com.rachio.test.api.qatest.util.ARandom.aRandom

class PersonServiceExceptionSpec extends Specification {

    def "should throw 403 error if person does not exist"() {
        when:
        Client.get("/person/${aRandom.uuid()}", Person)

        then:
        thrown(ForbiddenException)
    }
}
