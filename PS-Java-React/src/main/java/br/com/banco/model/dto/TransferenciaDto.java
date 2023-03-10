package br.com.banco.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransferenciaDto implements Serializable{
    
    private int idTipoTransacao;
    private int idConta;
    private int idContaDestino;
    private double valor;
}
