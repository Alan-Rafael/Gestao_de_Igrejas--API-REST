package com.agenda.agendaLagoinha.exception.infra;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.agenda.agendaLagoinha.exception.infra.infra.ErrorNotValidMessageDto;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorNotValidMessageDto> hanlderConstrainViolationException(ConstraintViolationException exception){
        String errorMenssage = exception.getMessage();
        ErrorNotValidMessageDto errorResponse = new ErrorNotValidMessageDto(errorMenssage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
