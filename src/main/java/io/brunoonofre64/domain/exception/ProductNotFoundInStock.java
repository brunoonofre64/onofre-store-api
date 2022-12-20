package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class ProductNotFoundInStock extends RuntimeException {

    final CodeMessage message;

    public ProductNotFoundInStock(CodeMessage message) {
        this.message = message;
    }
}
