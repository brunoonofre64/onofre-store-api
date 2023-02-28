package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class EmployeeNotFound extends RuntimeException {
    final CodeMessage message;

    public EmployeeNotFound(CodeMessage message) {
        this.message = message;
    }
}
