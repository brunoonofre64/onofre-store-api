package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class ProductNotFoundInStock extends RuntimeException {

    final CodeMessage message;

    public ProductNotFoundInStock(CodeMessage message) {
        this.message = message;
    }
}
