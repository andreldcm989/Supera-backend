package br.com.banco.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.model.Operacao;

public interface OperacaoRepositorio extends JpaRepository<Operacao, Integer>{
}
