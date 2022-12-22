package br.com.banco.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_contas")
@NoArgsConstructor
@Getter
public class Conta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titular;
    private double saldo;

    // private List<Operacao> operacaos = new ArrayList<>();

    public Conta(String titular) {
        this.titular = titular;
        this.saldo = 0;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

}
