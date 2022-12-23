package br.com.banco.enums;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipos_operacoes")
public enum TipoTransacao {
    
    DEPOSITO,
    SAQUE,
    TRANSFERENCIA;
}
