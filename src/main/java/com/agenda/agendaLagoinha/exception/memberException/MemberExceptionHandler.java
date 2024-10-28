package com.agenda.agendaLagoinha.exception.memberException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.agenda.agendaLagoinha.exception.infra.infra.RestErrorMessage;

@ControllerAdvice
public class MemberExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MemberNotFoundException.class)
    private ResponseEntity<RestErrorMessage> memberNotFoundHandler(MemberNotFoundException exception){
        RestErrorMessage responseError = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
    }

    @ExceptionHandler(MemberExistException.class)
    private ResponseEntity<RestErrorMessage> memberDoesHasExistHandler(MemberExistException exception){
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessage);
    }
}
