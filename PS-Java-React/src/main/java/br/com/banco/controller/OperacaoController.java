package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Operacao;
import br.com.banco.model.dto.DepositoDto;
import br.com.banco.model.dto.OperacaoDtoListar;
import br.com.banco.service.OperacaoService;

@RestController
@RequestMapping("/api/contas/{idConta}")
public class OperacaoController {
    
    @Autowired
    private OperacaoService operacaoService;

    @GetMapping("/transacoes")
    public ResponseEntity<List<OperacaoDtoListar>> listar(@PathVariable int idConta){
        return ResponseEntity.ok().body(operacaoService.listarOperacoesPorConta(idConta));
    }

    @PostMapping("/deposito")
    public ResponseEntity<OperacaoDtoListar> deposito(@RequestBody DepositoDto depositoDto){
        return ResponseEntity.ok().body(operacaoService.deposita(depositoDto));
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Operacao> buscarOperacaoPorId(@PathVariable int id){
    //     return ResponseEntity.ok().body(OperacaoService.buscarOperacaoPorId(id));
    // }

    // @PostMapping
    // public ResponseEntity<Operacao> salvar(@RequestBody Operacao Operacao){
    //     return ResponseEntity.status(HttpStatus.CREATED).body(OperacaoService.criar(Operacao));
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Operacao> editar(@PathVariable int id, @RequestBody Operacao Operacao){
    //     return ResponseEntity.ok().body(OperacaoService.editar(id, Operacao));
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Object> excluir(@PathVariable int id){
    //     OperacaoService.excluirOperacao(id);
    //     return ResponseEntity.ok().body("Operacao excluída com sucesso!");
    // }
}
