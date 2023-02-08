package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class OrderNotFoundException extends RuntimeException {

    final CodeMessage message;
    public OrderNotFoundException(CodeMessage message) {
        this.message = message;
    }
}



