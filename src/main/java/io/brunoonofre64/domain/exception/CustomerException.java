package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class CustomerException extends RuntimeException {

    private CodeMessage message;

    public CustomerException(CodeMessage message) {
        this.message = message;
    }
}
