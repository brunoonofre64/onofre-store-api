package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class EmailAlreadyExistsException extends RuntimeException {
    final CodeMessage message;

    public EmailAlreadyExistsException(CodeMessage message) {
        this.message = message;
    }
}
