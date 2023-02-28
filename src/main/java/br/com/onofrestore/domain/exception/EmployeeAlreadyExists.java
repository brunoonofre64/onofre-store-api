package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class EmployeeAlreadyExists extends RuntimeException {

    final CodeMessage message;

    public EmployeeAlreadyExists(CodeMessage message) {
        this.message = message;
    }
}
