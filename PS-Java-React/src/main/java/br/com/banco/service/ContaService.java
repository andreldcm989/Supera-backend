package br.com.banco.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.repositorios.ContaRepositorio;

@Service
public class ContaService {
    
    @Autowired
    private ContaRepositorio contaRepositorio;

    public Conta criar(Conta conta){
        return contaRepositorio.save(conta);
    }

    public List<Conta> listarContas(){
        return contaRepositorio.findAll();
    }

    public Conta buscarContaPorId(int id){
        return contaRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException("Conta não localizada."));
    }

    public Conta editar(Conta conta){
        Conta atual = contaRepositorio.getReferenceById(conta.getId());
        if (atual == null) {
            throw new EntityNotFoundException("Conta não localizada.");
        }
        BeanUtils.copyProperties(conta, atual);
        return contaRepositorio.save(atual);
    }

    public void excluirConta(int id){
        Conta conta = contaRepositorio.getReferenceById(id);
        if (conta == null) {
            throw new EntityNotFoundException("Conta não localizada.");
        }
        contaRepositorio.delete(conta);
    }
}
