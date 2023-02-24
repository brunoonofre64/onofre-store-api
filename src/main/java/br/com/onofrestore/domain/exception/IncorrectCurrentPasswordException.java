package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class IncorrectCurrentPasswordException extends RuntimeException {
    final CodeMessage message;

    public IncorrectCurrentPasswordException(CodeMessage message) {
        this.message = message;
    }
}
