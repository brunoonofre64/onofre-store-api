package br.com.onofrestore.api.v1.handler;

import br.com.onofrestore.domain.enums.CodeMessage;
import br.com.onofrestore.domain.exception.*;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
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
                .details(this.getCodeMessage(CodeMessage.FIELD_NULL_OR_IS_EMPTY))
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
                .details(this.getCodeMessage(CodeMessage.DTO_NULL_OR_IS_EMPTY))
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
                .details(this.getCodeMessage(CodeMessage.LIST_IS_EMPTY))
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
                .details(this.getCodeMessage(CodeMessage.UUID_NOT_FOUND_OR_NULL))
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
                .details(this.getCodeMessage(CodeMessage.CPF_INVALID_FORMAT))
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
                .details(this.getCodeMessage(CodeMessage.CPF_REPEATED))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(this.getCodeMessage(CodeMessage.CPF_INVALID_FORMAT))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerEmailException(EmailException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(this.getCodeMessage(CodeMessage.EMAIL_IS_EMPTY))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(this.getCodeMessage(CodeMessage.EMAIL_ALREADY_EXISTS))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerUserNotFoundException(UserNotFoundException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(this.getCodeMessage(CodeMessage.USER_NOTFOUND))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NewPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerUserNotFoundException(NewPasswordException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(this.getCodeMessage(CodeMessage.INVALID_PASSWORD))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(this.getCodeMessage(CodeMessage.USER_ALREADY_EXISTS))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectCurrentPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerIncorrectCurrentPasswordException(IncorrectCurrentPasswordException ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(this.getCodeMessage(CodeMessage.INCORRECT_CURRENT_PASSWORD))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrors> handlerEmployeeAlreadyExists(EmployeeAlreadyExists ex) {
        ApiErrors apiErrors = ApiErrors
                .builder()
                .title(getCodeMessage(CodeMessage.INVALID_REQUEST))
                .codeStatus(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .details(this.getCodeMessage(CodeMessage.CPF_REPEATED))
                .build();
        return new ResponseEntity<>(apiErrors, HttpStatus.BAD_REQUEST);
    }

    private String getCodeMessage(CodeMessage codigoMensagem) {
        return messageBundle.getMessage(codigoMensagem.getValue(), null, LocaleContextHolder.getLocale());
    }
}
