package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class BusinessRuleException extends RuntimeException {

    final CodeMessage message;

    public BusinessRuleException(CodeMessage message) {
        this.message = message;
    }
}
