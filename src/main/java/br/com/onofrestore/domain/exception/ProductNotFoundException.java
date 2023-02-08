package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class ProductNotFoundException extends RuntimeException{

    final CodeMessage message;

    public ProductNotFoundException(CodeMessage message) {
        this.message = message;
    }
}
