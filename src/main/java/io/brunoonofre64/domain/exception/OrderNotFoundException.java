package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class OrderNotFoundException extends RuntimeException {

    final CodeMessage message;
    public OrderNotFoundException(CodeMessage message) {
        this.message = message;
    }
}



