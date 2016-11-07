package com.rachio.test.api.qatest.client.exceptions


class UnauthorizedException extends RuntimeException {
    UnauthorizedException(def response) {
        super("UNAUTHORIZED: 401 Response from server:  ${response}")
    }
}
