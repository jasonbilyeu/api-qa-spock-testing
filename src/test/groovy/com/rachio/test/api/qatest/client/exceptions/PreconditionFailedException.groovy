package com.rachio.test.api.qatest.client.exceptions


class PreconditionFailedException extends RuntimeException {
    PreconditionFailedException(def response) {
        super("PRECONDITION_FAILED: 412 Response from server:  ${response}")
    }
}
