package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class ProductAlreadyExists extends RuntimeException {
    final CodeMessage message;

    public ProductAlreadyExists(CodeMessage message) {
        this.message = message;
    }
}
