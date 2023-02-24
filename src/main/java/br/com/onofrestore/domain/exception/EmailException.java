package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class EmailException extends RuntimeException {
    final CodeMessage message;

    public EmailException(CodeMessage message) {
        this.message = message;
    }
}
