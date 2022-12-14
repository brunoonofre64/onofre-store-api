package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class CpfRepeatedException extends RuntimeException {

    final CodeMessage message;

    public CpfRepeatedException(CodeMessage message) {
        this.message = message;
    }
}
