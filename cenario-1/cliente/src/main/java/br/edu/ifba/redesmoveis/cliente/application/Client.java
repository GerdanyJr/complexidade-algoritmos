package br.edu.ifba.redesmoveis.cliente.application;

import br.edu.ifba.redesmoveis.cliente.infra.client.ApiClient;
import br.edu.ifba.redesmoveis.cliente.model.entities.Person;
import br.edu.ifba.redesmoveis.cliente.port.generator.PersonGenerator;

public class Client implements Runnable {

    private final ApiClient apiClient;
    private final PersonGenerator personGenerator;

    public Client(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.personGenerator = new PersonGenerator();
    }

    @Override
    public void run() {
        Person person = personGenerator.generate();
        try {
            apiClient.register(person);
            System.out.println("Registro enviado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Erro no registro!");
        }
    }

}
