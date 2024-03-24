package br.edu.ifba.redesMoveis;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.edu.ifba.redesMoveis.model.entities.Person;
import br.edu.ifba.redesMoveis.port.generator.PersonGenerator;
import br.edu.ifba.redesMoveis.usecases.operations.Operations;
import br.edu.ifba.redesMoveis.usecases.operations.OperationsImpl;

public class App {
    public static void main(String[] args) throws Exception {
        Operations<Person> operations = new OperationsImpl();
        PersonGenerator personGenerator = new PersonGenerator();
        List<Person> people = personGenerator.generate(10, "gerdas");

        // (d.1) imprimir a lista de objetos, pessoas ou animais sobre os quais ocorre o
        // monitoramento
        System.out.println("---------------------D1---------------------");
        operations.print(people);

        System.out.println("\n---------------------D2---------------------");
        operations.printWithPhone(people);

        System.out.println("\n---------------------D3---------------------");
        System.out.println("Lista antes da ordenação");
        operations.print(people);
        operations.orderByConsumption(people);
        System.out.println("\nLista após ordenação");
        operations.print(people);

        System.out.println("\n---------------------D4---------------------");
        Map<String, Integer> peakTime = operations.findPeakTime(people);
        Iterator<String> iterator = peakTime.keySet().iterator();
        String horarioDePico = iterator.hasNext() ? iterator.next() : null;
        System.out
                .println("O horário de pico é: " +
                        horarioDePico +
                        "h" +
                        ", com um consumo total de: " +
                        peakTime.get(horarioDePico) +
                        "Mb");
    }
}
