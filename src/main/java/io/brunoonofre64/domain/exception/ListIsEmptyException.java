package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class ListIsEmptyException extends RuntimeException {

    private CodeMessage message;

    public ListIsEmptyException(CodeMessage message) {
        this.message = message;
    }
}
