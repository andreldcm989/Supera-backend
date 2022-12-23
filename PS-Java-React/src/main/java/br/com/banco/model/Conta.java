package br.com.banco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private double saldo;

    @OneToMany(mappedBy = "conta")
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

    public void transfere(double valor, Conta conta){
        this.saldo -= valor;
        conta.saldo += valor;
    }

    public void deposita(double valor){
        this.saldo += valor;
    }

}
