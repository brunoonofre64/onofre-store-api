package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class UuidNotFoundOrNullException extends RuntimeException {
    private CodeMessage message;
    public UuidNotFoundOrNullException(CodeMessage message){
        this.message = message;
    }
}
