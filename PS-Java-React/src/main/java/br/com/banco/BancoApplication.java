package br.com.banco;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import br.com.banco.model.Conta;
import br.com.banco.repositorios.ContaRepositorio;

@SpringBootApplication
@Configuration
public class BancoApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(BancoApplication.class, args);
    }

    @Autowired
    private ContaRepositorio contaRepositorio;
    
    @Override
    public void run(String... args) throws Exception {
        Conta c1 = new Conta("André");
        Conta c2 = new Conta("Luiz");
        Conta c3 = new Conta("Maria");

        contaRepositorio.saveAll(Arrays.asList(c1, c2, c3));
    }
}
