package br.com.banco.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.repositorios.ContaRepositorio;

@Service
public class ContaService {

    @Autowired
    private ContaRepositorio contaRepositorio;

    public Conta criar(Conta conta) {
        return contaRepositorio.save(conta);
    }

    public List<Conta> listarContas() {
        return contaRepositorio.findAll();
    }

    public Conta buscarContaPorId(int id) {
        return contaRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException("Conta não localizada."));
    }

    public Conta editar(int id, Conta conta) {
        try {
            Conta atual = contaRepositorio.getReferenceById(id);
            BeanUtils.copyProperties(conta, atual);
            return contaRepositorio.save(atual);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Conta não localizada.");
        } catch (FatalBeanException e) {
            throw new EntityNotFoundException("Erro ao atualizar o Recurso.");
        }
    }

    public void excluirConta(int id) {
        try {
            contaRepositorio.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Objeto não encontrado.");
        }
    }
}
