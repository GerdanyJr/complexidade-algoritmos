package br.edu.ifba.redesmoveis.cliente.port.generator;

import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;

import br.edu.ifba.redesmoveis.cliente.model.entities.Person;
import br.edu.ifba.redesmoveis.cliente.model.entities.Phone;

public class PersonGenerator {
    private final PhoneGenerator phoneGenerator;
    private final Faker faker;

    public PersonGenerator() {
        phoneGenerator = new PhoneGenerator();
        faker = new Faker(new Locale("pt-BR"));
    }

    /*
     * complexidade quadrática O(n^2), pois dentro de um laço de repetição é feito a
     * chamada de um método que invoca outro laço(generatePhones)
     */
    public Person generate() {
        List<Phone> phones = phoneGenerator.generatePhones(10);

        return new Person(faker.harryPotter().character(), phones);
    }
}
