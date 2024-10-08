package com.ryuqq.linksyncserver.module.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException{

    protected NotFoundException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }

}
