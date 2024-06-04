package com.agenda.agendaLagoinha.infra;


import com.agenda.agendaLagoinha.infra.infra.ErrorNotValidMessageDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    private MessageSource messageSource;

    public ExceptionHandlerController(MessageSource messageSource){
        this.messageSource=messageSource;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorNotValidMessageDto> hanlderConstrainViolationException(ConstraintViolationException exception){
        String errorMenssage = exception.getMessage();
        ErrorNotValidMessageDto errorResponse = new ErrorNotValidMessageDto(errorMenssage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
