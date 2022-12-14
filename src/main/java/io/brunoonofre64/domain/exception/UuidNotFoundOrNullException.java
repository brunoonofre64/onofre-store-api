package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class UuidNotFoundOrNullException extends RuntimeException {

     final CodeMessage message;

    public UuidNotFoundOrNullException(CodeMessage message){
        this.message = message;
    }
}
