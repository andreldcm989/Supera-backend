package br.com.banco.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.model.TipoTransacao;

public interface TipoTransacaoRepositorio extends JpaRepository<TipoTransacao, Integer> {
    
}
