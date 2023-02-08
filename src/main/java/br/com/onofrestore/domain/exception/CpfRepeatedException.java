package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class CpfRepeatedException extends RuntimeException {

    final CodeMessage message;

    public CpfRepeatedException(CodeMessage message) {
        this.message = message;
    }
}
