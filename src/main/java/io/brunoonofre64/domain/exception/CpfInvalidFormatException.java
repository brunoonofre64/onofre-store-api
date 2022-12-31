package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class CpfInvalidFormatException extends RuntimeException {

    final CodeMessage message;

    public CpfInvalidFormatException(CodeMessage message) {
        this.message = message;
    }
}
