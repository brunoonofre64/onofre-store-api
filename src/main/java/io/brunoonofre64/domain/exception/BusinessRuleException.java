package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class BusinessRuleException extends RuntimeException {

    final CodeMessage message;

    public BusinessRuleException(CodeMessage message) {
        this.message = message;
    }
}
