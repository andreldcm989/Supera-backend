package br.com.banco.service.exception;

public class ValorInvalidoException extends RuntimeException {
    
    public ValorInvalidoException(){
        super();
    }

    public ValorInvalidoException(String msg){
        super(msg);
    }

}
