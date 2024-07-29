package br.edu.ifba.redesmoveis.cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import br.edu.ifba.redesmoveis.cliente.application.Client;
import br.edu.ifba.redesmoveis.cliente.infra.client.ApiClient;

@SpringBootApplication
@EnableFeignClients
public class ClienteApplication implements CommandLineRunner {

	private static final int TOTAL_DE_CLIENTES = 100;

	@Autowired
	private ApiClient apiClient;

	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Thread> threads = enviarLeituras();
		awaitThreads(threads);
	}

	private static void awaitThreads(List<Thread> threads) throws InterruptedException {
		for (Thread thread : threads) {
			thread.join();
		}
	}

	private List<Thread> enviarLeituras() {
		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < TOTAL_DE_CLIENTES; i++) {
			threads.add(new Thread(new Client(apiClient)));
			threads.get(i).start();
		}

		return threads;
	}
}