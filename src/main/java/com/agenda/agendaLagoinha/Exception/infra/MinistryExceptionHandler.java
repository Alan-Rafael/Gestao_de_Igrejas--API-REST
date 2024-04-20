package com.agenda.agendaLagoinha.Exception.infra;


import com.agenda.agendaLagoinha.Exception.MinistryNotFoundException;
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
