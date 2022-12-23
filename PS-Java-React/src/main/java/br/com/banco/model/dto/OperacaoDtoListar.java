package br.com.banco.model.dto;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

import br.com.banco.model.Operacao;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OperacaoDtoListar implements Serializable{
   
    private String dataOperacao; 
    private double valor;
    private String tipo;
    private String nomeOperadorTransacao;

    public OperacaoDtoListar(Operacao operacao) {
        dataOperacao = operacao.getDataOperacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        valor = operacao.getValor();
        tipo = operacao.getTipo().toString();
        nomeOperadorTransacao = operacao.getNomeOperadorTransacao();
    }

    
}
