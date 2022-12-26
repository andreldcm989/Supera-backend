package br.com.banco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.model.Operacao;
import br.com.banco.model.TipoTransacao;
import br.com.banco.model.dto.DepositoOuSaqueDto;
import br.com.banco.model.dto.OperacaoDtoListar;
import br.com.banco.model.dto.TransferenciaDto;
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
        contaService.buscarContaPorId(idConta);
        return operacaoRepositorio.findByContaId(idConta).stream().map(OperacaoDtoListar::new).toList();
    }

    public OperacaoDtoListar deposita(DepositoOuSaqueDto deposito){
        Conta conta = contaService.deposita(deposito);
        TipoTransacao tipo = tipoTransacaoService.buscarTipoTransacaoPorId(deposito.getIdTipoTransacao());
        Operacao transacao = new Operacao(deposito.getValor(), tipo, conta);
        return new OperacaoDtoListar(operacaoRepositorio.save(transacao));
    }

    public OperacaoDtoListar transfere(TransferenciaDto transferenciaDto) {
        if(transferenciaDto.getIdConta() == transferenciaDto.getIdContaDestino()){
            throw new IllegalArgumentException("A conta de destino deve ser diferente da conta emissora.");
        }
        List<Conta> contas = contaService.transfere(transferenciaDto);
        Conta emissor = contas.stream().filter(c -> c.getId() == transferenciaDto.getIdConta()).findFirst().get();
        Conta destino = contas.stream().filter(c -> c.getId() == transferenciaDto.getIdContaDestino()).findFirst().get();
        TipoTransacao tipo = tipoTransacaoService.buscarTipoTransacaoPorId(transferenciaDto.getIdTipoTransacao());
        Operacao saqueEmissor = new Operacao(transferenciaDto.getValor(), tipo, destino.getTitular(), emissor);
        Operacao depositoDestino = new Operacao(transferenciaDto.getValor(), tipo,  emissor.getTitular(),destino);
        operacaoRepositorio.save(saqueEmissor);
        operacaoRepositorio.save(depositoDestino);
        return new OperacaoDtoListar(saqueEmissor);
    }

    public OperacaoDtoListar saca(DepositoOuSaqueDto dto){
        Conta conta = contaService.saca(dto.getIdConta(), dto.getValor());
        TipoTransacao transacao = tipoTransacaoService.buscarTipoTransacaoPorId(dto.getIdTipoTransacao());
        Operacao saque = new Operacao(dto.getValor(), transacao, conta);
        return new OperacaoDtoListar(operacaoRepositorio.save(saque));
    }
}
