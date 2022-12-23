package br.com.banco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_contas")
@NoArgsConstructor
@Getter
public class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titular;
    @Column(length = 20, precision = 2)
    private double saldo;

    @JsonIgnore
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Operacao> operacoes = new ArrayList<>();

    public Conta(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public List<Operacao> getOperacoes() {
        return Collections.unmodifiableList(operacoes);
    }

    public void adiciona(Operacao operacao) {
        operacoes.add(operacao);
    }

    public void exclui(Operacao operacao) {
        operacoes.remove(operacao);
    }

    public void saca(double valor){
        this.saldo-=valor;
    }

    public void deposita(double valor){
        this.saldo += valor;
    }

}
