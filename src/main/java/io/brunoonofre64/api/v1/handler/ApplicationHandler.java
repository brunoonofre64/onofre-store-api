package io.brunoonofre64.api.v1.handler;

import io.brunoonofre64.domain.enums.CodeMessage;
import io.brunoonofre64.domain.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationHandler extends ResponseEntityExceptionHandler {
    private ReloadableResourceBundleMessageSource messageBundle;

    @ExceptionHandler(CustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerCustomerException(CustomerException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(getCodeMessage(CodeMessage.FIELD_NULL_OR_IS_EMPTY))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DtoNullOrIsEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerDtoNullOrIsEmptyException(DtoNullOrIsEmptyException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(getCodeMessage(CodeMessage.DTO_NULL_OR_IS_EMPTY))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListIsEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerListIsEmptyException(ListIsEmptyException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(getCodeMessage(CodeMessage.LIST_IS_EMPTY))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UuidNotFoundOrNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerUuidNotFoundOrNullException(UuidNotFoundOrNullException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(getCodeMessage(CodeMessage.UUID_NOT_FOUND_OR_NULL))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerTransactionSystemException(TransactionSystemException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(getCodeMessage(CodeMessage.CPF_INVALID_FORMAT))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CpfRepeatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerCpfRepeatedException(CpfRepeatedException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(getCodeMessage(CodeMessage.CPF_REPEATED))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    private String getCodeMessage(CodeMessage codigoMensagem) {
        return messageBundle.getMessage(codigoMensagem.getValue(), null, LocaleContextHolder.getLocale());
    }
}
