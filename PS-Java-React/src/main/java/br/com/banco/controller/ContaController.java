package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Conta;
import br.com.banco.service.ContaService;

@RestController
@RequestMapping("/api/contas")
@CrossOrigin("*")
public class ContaController {
    
    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<List<Conta>> listar(){
        return ResponseEntity.ok().body(contaService.listarContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarContaPorId(@PathVariable int id){
        return ResponseEntity.ok().body(contaService.buscarContaPorId(id));
    }

    @PostMapping
    public ResponseEntity<Conta> salvar(@RequestParam String nome){
        Conta conta = new Conta(nome);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.criar(conta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> editar(@PathVariable int id, @RequestBody Conta conta){
        return ResponseEntity.ok().body(contaService.editar(id, conta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable int id){
        contaService.excluirConta(id);
        return ResponseEntity.ok().body("Conta excluída com sucesso!");
    }
}
