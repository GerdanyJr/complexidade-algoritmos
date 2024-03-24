package br.edu.ifba.redesMoveis.port.generator;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.redesMoveis.model.entities.Person;
import br.edu.ifba.redesMoveis.model.entities.Phone;

public class PersonGenerator {
    private final PhoneGenerator phoneGenerator;

    public PersonGenerator() {
        phoneGenerator = new PhoneGenerator();
    }

    /*
     * complexidade quadrática O(n^2), pois dentro de um laço de repetição é feito a
     * chamada de um método que invoca outro laço(generatePhones)
     */
    public List<Person> generate(int numberToGenerate, String name) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numberToGenerate; i++) {
            List<Phone> phones = phoneGenerator.generatePhones(numberToGenerate);
            people.add(new Person(i, name + " #" + i, phones));
        }
        return people;
    }
}
