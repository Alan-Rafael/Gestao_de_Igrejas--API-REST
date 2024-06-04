package com.agenda.agendaLagoinha.ministerios.exception;


import com.agenda.agendaLagoinha.infra.infra.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MinistryExceptionHandler {
    @ExceptionHandler(MinistryNotFoundException.class)
    private ResponseEntity<RestErrorMessage> notFoundMinistry(MinistryNotFoundException exception){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
