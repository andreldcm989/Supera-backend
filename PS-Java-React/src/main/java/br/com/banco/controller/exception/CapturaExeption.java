package br.com.banco.controller.exception;

import java.time.Instant;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.banco.service.exception.ValorDepositoInvalidoException;

@ControllerAdvice
public class CapturaExeption {
    
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseErrorFormatter> metodoNaoDisponivel(HttpRequestMethodNotSupportedException e, HttpServletRequest request){
        ResponseErrorFormatter erro = new ResponseErrorFormatter(
            Instant.now()
            , HttpStatus.METHOD_NOT_ALLOWED.value()
            , "Método não suportado."
            , e.getMessage(), 
            request.getRequestURI());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
    }

    @ExceptionHandler(ValorDepositoInvalidoException.class)
    public ResponseEntity<ResponseErrorFormatter> valorInvalido(ValorDepositoInvalidoException e, HttpServletRequest request){
        ResponseErrorFormatter erro = new ResponseErrorFormatter(Instant.now()
            , HttpStatus.BAD_REQUEST.value(), 
            "Valor inválido", 
            e.getMessage(),
            request.getRequestURI());
        return ResponseEntity.badRequest().body(erro);
    }
}
