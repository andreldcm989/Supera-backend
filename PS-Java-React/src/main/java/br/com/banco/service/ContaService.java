package br.com.banco.service;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.model.dto.DepositoDto;
import br.com.banco.model.dto.TransferenciaDto;
import br.com.banco.repositorios.ContaRepositorio;
import br.com.banco.service.exception.ValorInvalidoException;

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

    public Conta deposita(DepositoDto dto){
        try {            
            Conta conta = contaRepositorio.getReferenceById(dto.getIdConta());
            validaValorDeposito(dto.getValor());
            conta.deposita(dto.getValor());
            return contaRepositorio.save(conta);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Nenhuma conta localizada com o ID " + dto.getIdConta() +".");
        }
    }

    public List<Conta> transfere(TransferenciaDto dto){
        try {
            Conta emissor = contaRepositorio.getReferenceById(dto.getIdContaEmissora());
            Conta destino = contaRepositorio.getReferenceById(dto.getIdContaDestino());
            validaValorDeposito(dto.getValor());
            validaValorSaque(emissor, dto.getValor());
            emissor.saca(dto.getValor());
            destino.deposita(dto.getValor());
            contaRepositorio.save(emissor);
            contaRepositorio.save(destino);
            return contaRepositorio.findAllById(Arrays.asList(emissor.getId(), destino.getId()));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Conta não localizada, verifique e tente novamente.");
        }
    }

    public void validaValorDeposito(double valor){
        if (valor <= 0){
            throw new ValorInvalidoException("O valor para deposito deve ser maior que zero (0).");
        }
    }

    public void validaValorSaque(Conta conta, double valor){
        if (valor > conta.getSaldo()){
            throw new ValorInvalidoException("Saldo insuficiente.");
        }
    }
}
