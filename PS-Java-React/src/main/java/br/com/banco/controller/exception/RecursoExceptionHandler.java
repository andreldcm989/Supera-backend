package br.com.banco.controller.exception;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecursoExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorFormatter> capturaErro(EntityNotFoundException e, HttpServletRequest request){
        ResponseErrorFormatter erro = new ResponseErrorFormatter(
            Instant.now()
            , HttpStatus.NOT_FOUND.value()
            , "Recurso não encontrado."
            , e.getMessage(), 
            request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
