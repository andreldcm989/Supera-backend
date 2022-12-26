package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.dto.DepositoOuSaqueDto;
import br.com.banco.model.dto.OperacaoDtoListar;
import br.com.banco.model.dto.TransferenciaDto;
import br.com.banco.service.OperacaoService;

@RestController
@RequestMapping("/api/contas/{idConta}")
@CrossOrigin("*")
public class OperacaoController {
    
    @Autowired
    private OperacaoService operacaoService;

    @GetMapping("/transacoes")
    public ResponseEntity<List<OperacaoDtoListar>> listar(@PathVariable int idConta){
        return ResponseEntity.ok().body(operacaoService.listarOperacoesPorConta(idConta));
    }

    @PostMapping("/transacoes/deposito")
    public ResponseEntity<OperacaoDtoListar> deposito(@RequestBody DepositoOuSaqueDto depositoDto){
        return ResponseEntity.ok().body(operacaoService.deposita(depositoDto));
    }

    @PostMapping("/transacoes/transferencia")
    public ResponseEntity<OperacaoDtoListar> transfere(@RequestBody TransferenciaDto transferenciaDto){
        return ResponseEntity.ok().body(operacaoService.transfere(transferenciaDto));
    }

    @PostMapping("/transacoes/saque")
    public ResponseEntity<OperacaoDtoListar> saca(@RequestBody DepositoOuSaqueDto saqueDto){
        return ResponseEntity.ok().body(operacaoService.saca(saqueDto));
    }
}
