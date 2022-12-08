package io.brunoonofre64.domain.exception;

import io.brunoonofre64.domain.enums.CodeMessage;

public class DtoNullOrIsEmptyException extends RuntimeException {

    private CodeMessage message;

   public DtoNullOrIsEmptyException(CodeMessage message) {
        this.message = message;
    }
}
