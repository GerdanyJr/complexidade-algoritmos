package br.edu.ifba.redesmoveis.cliente.application;

import java.util.List;

import br.edu.ifba.redesmoveis.cliente.infra.client.ApiClient;
import br.edu.ifba.redesmoveis.cliente.model.entities.Consumption;
import br.edu.ifba.redesmoveis.cliente.model.entities.Person;
import br.edu.ifba.redesmoveis.cliente.model.req.RegisterReq;
import br.edu.ifba.redesmoveis.cliente.port.generator.PersonGenerator;
import br.edu.ifba.redesmoveis.cliente.useCases.Operations;

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
        // calcular consumo médio do cliente através desse método

        List<Consumption> consumptionPerHour = Operations.getConsumptionPerHour(person);

        // Fim calculo
        try {
            apiClient.register(new RegisterReq(person, consumptionPerHour));
            System.out.println("Registro enviado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Erro no registro!");
        }
    }

}
