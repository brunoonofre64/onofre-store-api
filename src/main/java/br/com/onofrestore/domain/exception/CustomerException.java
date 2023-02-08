package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class CustomerException extends RuntimeException {

     final CodeMessage message;

    public CustomerException(CodeMessage message) {
        this.message = message;
    }
}
