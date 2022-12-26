package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.TipoTransacao;
import br.com.banco.service.TipoTransacaoService;

@RestController
@RequestMapping("/api/tiposTransacoes")
@CrossOrigin("*")
public class TipoTransacaoController {
    
    @Autowired
    private TipoTransacaoService tipoTransacaoService;

    @GetMapping
    public ResponseEntity<List<TipoTransacao>> listar(){
        return ResponseEntity.ok().body(tipoTransacaoService.listarTipoTransacaos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTransacao> buscarTipoTransacaoPorId(@PathVariable int id){
        return ResponseEntity.ok().body(tipoTransacaoService.buscarTipoTransacaoPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoTransacao> salvar(@RequestBody TipoTransacao tipoTransacao){
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoTransacaoService.criar(tipoTransacao));
    }

}
