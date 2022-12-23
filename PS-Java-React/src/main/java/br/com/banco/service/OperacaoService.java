package br.com.banco.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.model.Operacao;
import br.com.banco.model.TipoTransacao;
import br.com.banco.model.dto.DepositoDto;
import br.com.banco.model.dto.OperacaoDtoListar;
import br.com.banco.repositorios.OperacaoRepositorio;

@Service
public class OperacaoService {

    @Autowired
    private OperacaoRepositorio operacaoRepositorio;

    @Autowired
    private TipoTransacaoService tipoTransacaoService;

    @Autowired
    private ContaService contaService;

    public List<OperacaoDtoListar> listarOperacoesPorConta(int idConta) {
        return operacaoRepositorio.findByContaId(idConta).stream().map(OperacaoDtoListar::new).toList();
    }

    public OperacaoDtoListar deposita(DepositoDto deposito){
        Conta conta = contaService.deposita(deposito.getIdConta(), deposito.getValor());
        TipoTransacao tipo = tipoTransacaoService.buscarTipoTransacaoPorId(deposito.getIdTipoTransacao());
        Operacao transacao = new Operacao(deposito.getValor(), tipo, conta);
        return new OperacaoDtoListar(operacaoRepositorio.save(transacao));
    }
}
