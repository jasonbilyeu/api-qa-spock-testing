package com.rachio.test.api.qatest.client.exceptions


class InternalServerException extends RuntimeException {
    InternalServerException(def response) {
        super("INTERNAL_SERVER_ERROR: 500 Response from server:  ${response}")
    }
}
