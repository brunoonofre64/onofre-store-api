package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class LoginNotFoundException extends RuntimeException {

    final CodeMessage message;

    public LoginNotFoundException(CodeMessage message) {
        this.message = message;
    }
}
