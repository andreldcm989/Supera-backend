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
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @OneToOne
    @JoinColumn(name = "id_tipo_transacao")
    private TipoTransacao tipo;
    
    @Column(length = 50, nullable = true)
    private String nomeOperadorTransacao;

    @Column(nullable = false, length = 20, precision = 2)
    private double saldoAtualizado;
    
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Operacao(double valor, TipoTransacao tipo, Conta conta) {
        this.dataOperacao = LocalDateTime.now();
        this.valor = valor;
        this.tipo = tipo;
        this.conta = conta;
        this.saldoAtualizado = conta.getSaldo();
    }


    public Operacao(double valor, TipoTransacao tipo, String nomeOperadorTransacao, Conta conta) {
        this.dataOperacao = LocalDateTime.now();
        this.valor = valor;
        this.tipo = tipo;
        this.nomeOperadorTransacao = nomeOperadorTransacao;
        this.conta = conta;
        this.saldoAtualizado = conta.getSaldo();
    }



    
}
