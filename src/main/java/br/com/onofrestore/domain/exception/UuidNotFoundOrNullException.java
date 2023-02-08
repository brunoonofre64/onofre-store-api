package br.com.onofrestore.domain.exception;

import br.com.onofrestore.domain.enums.CodeMessage;

public class UuidNotFoundOrNullException extends RuntimeException {

     final CodeMessage message;

    public UuidNotFoundOrNullException(CodeMessage message){
        this.message = message;
    }
}
