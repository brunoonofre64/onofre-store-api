package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class CpfInvalidFormatException extends RuntimeException {

    final CodeMessage message;

    public CpfInvalidFormatException(CodeMessage message) {
        this.message = message;
    }
}
