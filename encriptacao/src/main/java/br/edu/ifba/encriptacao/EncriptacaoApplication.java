package br.edu.ifba.encriptacao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifba.encriptacao.service.EncryptionService;
import br.edu.ifba.encriptacao.service.impl.EncryptionServiceImpl;

@SpringBootApplication
public class EncriptacaoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EncriptacaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			EncryptionService encryptionService = new EncryptionServiceImpl();
			encryptionService.saveKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
