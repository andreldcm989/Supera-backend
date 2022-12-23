package br.com.banco.service.exception;

public class ValorDepositoInvalidoException extends RuntimeException {
    
    public ValorDepositoInvalidoException(){
        super("O valor para deposito deve ser maior que zero (0).");
    }

}
