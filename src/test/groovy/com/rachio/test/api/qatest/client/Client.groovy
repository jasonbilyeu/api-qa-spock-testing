package com.rachio.test.api.qatest.client

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.rachio.test.api.qatest.client.exceptions.ForbiddenException
import com.rachio.test.api.qatest.client.exceptions.InternalServerException
import com.rachio.test.api.qatest.client.exceptions.NotFoundException
import com.rachio.test.api.qatest.client.exceptions.BadRequestException
import com.rachio.test.api.qatest.client.exceptions.PreconditionFailedException
import com.rachio.test.api.qatest.client.exceptions.UnauthorizedException
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.HttpResponseException

import static groovyx.net.http.Method.DELETE
import static groovyx.net.http.Method.POST
import static groovyx.net.http.Method.PUT
import static groovyx.net.http.ContentType.JSON

class Client {

    static final String API_TOKEN = '482f6cfa-1d00-4d62-9f58-5d7e2538721b'
    static final String BASE_URL = 'https://api.rach.io'
    static final String PATH_VERSION = '/1/public'
    static final HTTPBuilder http = new HTTPBuilder(BASE_URL)
    static final ObjectMapper mapper = new ObjectMapper()

    def static get(String path, Class clazz, Map query=[:], Class listItemClazz = null) {
        def responseObject

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        http.setHeaders([Authorization: "Bearer ${API_TOKEN}"])
        http.parser.'application/json' = http.parser.'text/plain'

        try {
            http.get(path: PATH_VERSION + path, contentType: JSON, query: query) { resp, json ->
                if (listItemClazz != null) {
                    responseObject = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(clazz, listItemClazz))
                } else {
                    responseObject = mapper.readValue(json, clazz)
                }
            }
        } catch (HttpResponseException responseException) {
            throwAppropriateException(responseException.response);
        }

        responseObject
    }

    static def post(String path, Map bodyToSend, Class clazz, Class listItemClazz = null) {
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        http.setHeaders([Authorization: "Bearer ${API_TOKEN}"])
        http.parser.'application/json' = http.parser.'text/plain'

        def responseObject = null

        try {
            http.request(POST, JSON) { req ->
                requestContentType = JSON
                body = bodyToSend
                uri.path = PATH_VERSION + path

                response.success = { resp, json ->
                    if (listItemClazz != null) {
                        responseObject = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(clazz, listItemClazz))
                    } else {
                        responseObject = mapper.readValue(json, clazz)
                    }
                }

                response.failure = { resp ->
                    throwAppropriateException(resp)
                }
            }
        } catch (HttpResponseException responseException) {
            throwAppropriateException(responseException.response);
        }

        responseObject
    }


    static def put(String path, Map bodyToSend, Class clazz = null) {
        http.setHeaders([Authorization: "Bearer ${API_TOKEN}"])
        def responseObject = null

        try {
            http.request(PUT, JSON) { req ->
                requestContentType = JSON
                body = bodyToSend
                uri.path = PATH_VERSION + path

                response.success = { resp, json ->
                    if (clazz != null) {
                        responseObject = mapper.readValue(json, clazz)
                    }
                }

                response.failure = { resp ->
                    throwAppropriateException(resp)
                }
            }
        } catch (HttpResponseException responseException) {
            throwAppropriateException(responseException.response);
        }

        responseObject
    }

    static delete(String path) {
        http.setHeaders([Authorization: "Bearer ${API_TOKEN}"])
        try {
            http.request(DELETE){ req ->
                uri.path = PATH_VERSION + path

                response.failure = { resp ->
                    throwAppropriateException(resp)
                }
            }
        } catch (HttpResponseException responseException) {
            throwAppropriateException(responseException.response);
        }
    }

    static def throwAppropriateException(def response) {
        switch(response.status) {
            case 400:
                throw new BadRequestException(response)
            case 401:
                throw new UnauthorizedException(response)
            case 403:
                throw new ForbiddenException(response)
            case 404:
                throw new NotFoundException(response)
            case 412:
                throw new PreconditionFailedException(response)
            case 500:
                throw new InternalServerException(response)
            default:
                throw new RuntimeException("Could not find an appropriate exception for status code ${response.status}")
        }
    }
}
