package br.com.banco.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.banco.enums.TipoTransacao;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_transacoes")
@NoArgsConstructor
@Getter
public class Operacao implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private LocalDateTime dataOperacao;
    
    @Column(nullable = false, length = 20, precision = 2)
    private double valor;

    @Column(length = 30, nullable = false)
    private TipoTransacao tipo;
    
    @Column(length = 50, nullable = true)
    private String nomeOperadorTransacao;
    
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Operacao(double valor, TipoTransacao tipo, Conta conta) {
        this.dataOperacao = LocalDateTime.now();
        this.valor = valor;
        this.tipo = tipo;
        this.conta = conta;
    }


    public Operacao(double valor, TipoTransacao tipo, String nomeOperadorTransacao, Conta conta) {
        this.dataOperacao = LocalDateTime.now();
        this.valor = valor;
        this.tipo = tipo;
        this.nomeOperadorTransacao = nomeOperadorTransacao;
        this.conta = conta;
    }

    
}
