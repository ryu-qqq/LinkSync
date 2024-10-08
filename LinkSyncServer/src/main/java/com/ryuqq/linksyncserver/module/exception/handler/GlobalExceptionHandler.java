package com.ryuqq.linksyncserver.module.exception.handler;

import com.ryuqq.linksyncserver.module.common.ApiResponse;
import com.ryuqq.linksyncserver.module.common.ErrorResponse;
import com.ryuqq.linksyncserver.module.exception.ApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@RestController
public class GlobalExceptionHandler {

    private static final String ERROR_LOG_MSG_FORMAT = "ERROR Message : {}";
    private static final String CHECK_LOG_CODE_FORMAT = "Class : {}, Code : {}, Message : {}";

    protected void loggingError(ApplicationException e){
        String exceptionClassName = e.getClass().getSimpleName();
        String errorCode = e.getErrorCode();
        String message = e.getMessage();
        log.error(CHECK_LOG_CODE_FORMAT, exceptionClassName, errorCode, message);
    }


    private void loggingError(Exception e, String errorMsg){
        String stackTrace = Arrays.stream(e.getStackTrace())
                .limit(10)
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
        String logMessage = errorMsg + "\n" + stackTrace;
        log.error(ERROR_LOG_MSG_FORMAT, logMessage);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> String.format("%s", fieldError.getDefaultMessage()))
                .collect(Collectors.joining(" "));


        String exceptionClassName = e.getClass().getSimpleName();
        loggingError(e, errorMsg);

        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, exceptionClassName, errorMsg);

        return ResponseEntity
                .status(errorResponse.getStatus())
                .body(errorResponse);
    }



}
