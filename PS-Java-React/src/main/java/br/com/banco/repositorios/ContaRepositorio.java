package br.com.banco.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.model.Conta;

public interface ContaRepositorio extends JpaRepository<Conta, Integer>{
    
}
