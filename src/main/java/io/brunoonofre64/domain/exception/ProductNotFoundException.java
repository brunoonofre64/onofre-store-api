package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class ProductNotFoundException extends RuntimeException{

    final CodeMessage message;

    public ProductNotFoundException(CodeMessage message) {
        this.message = message;
    }
}
