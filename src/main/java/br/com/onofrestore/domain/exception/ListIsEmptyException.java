package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class ListIsEmptyException extends RuntimeException {

    final CodeMessage message;

    public ListIsEmptyException(CodeMessage message) {
        this.message = message;
    }
}
