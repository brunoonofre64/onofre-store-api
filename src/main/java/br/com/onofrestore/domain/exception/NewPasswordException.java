package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class NewPasswordException extends RuntimeException {
    final CodeMessage message;

    public NewPasswordException(CodeMessage message) {
        this.message =message;
    }
}
