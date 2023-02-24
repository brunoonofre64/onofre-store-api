package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class UserNotFoundException extends RuntimeException {
    final CodeMessage message;

    public UserNotFoundException(CodeMessage message) {
        this.message =message;
    }
}
