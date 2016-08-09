package com.rachio.test.api.qatest.spec

import groovy.util.logging.Log
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import spock.lang.Specification

@Log
class TestSpec extends Specification {

    /* TODO: REPLACE WITH RACHIO PROVIDED API TOKEN */
    def apiToken = '2bce46e7-b77a-4408-93d9-658c36348e51'

    def "Get Call and receive 200"() {
        def getRequest
        when: "Make call to endpoint"
        try {
            def path = '/v2/5185415ba171ea3a00704eed'
            /* RESTClient requires a content-type of JSON/XML */
             getRequest = new RESTClient('http://www.mocky.io').get(
                    path: path,
                    requestContentType : ContentType.JSON,
                    headers : ['Authorization': 'Bearer ' + apiToken]
            )
        } catch (Exception e) {
            log.severe(e.toString())
            e.printStackTrace()
        }

        then: "Call should succeed"
        assert getRequest.status == 200

    }
}