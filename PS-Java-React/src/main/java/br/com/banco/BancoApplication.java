package br.com.banco;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import br.com.banco.model.Conta;
import br.com.banco.model.TipoTransacao;
import br.com.banco.repositorios.ContaRepositorio;
import br.com.banco.repositorios.TipoTransacaoRepositorio;

@SpringBootApplication
@Configuration
public class BancoApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BancoApplication.class, args);
    }

    @Autowired
    private ContaRepositorio contaRepositorio;

    @Autowired
    private TipoTransacaoRepositorio tipoRepositorio;
    
    @Override
    public void run(String... args) throws Exception {
        Conta c1 = new Conta("André");
        Conta c2 = new Conta("Luiz");
        Conta c3 = new Conta("Maria");

        contaRepositorio.saveAll(Arrays.asList(c1, c2, c3));

        TipoTransacao t1 = new TipoTransacao("DEPOSITO");
        TipoTransacao t2 = new TipoTransacao("SAQUE");
        TipoTransacao t3 = new TipoTransacao("TRANSFERENCIA");
        tipoRepositorio.saveAll(Arrays.asList(t1, t2, t3));

    }
}
