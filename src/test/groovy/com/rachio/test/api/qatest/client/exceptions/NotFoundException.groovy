package com.rachio.test.api.qatest.client.exceptions

class NotFoundException extends RuntimeException {
    NotFoundException(def response) {
        super("NOT_FOUND: 404 Response from server:  ${response}")
    }
}