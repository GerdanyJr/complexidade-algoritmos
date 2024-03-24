package br.edu.ifba.redesMoveis.usecases.orderer;

import java.util.List;

import br.edu.ifba.redesMoveis.model.entities.Person;
import br.edu.ifba.redesMoveis.model.entities.Phone;

public class PersonOrdererImpl implements PersonOrderer {

    /*
     * complexidade O(n^4), pois o método possui um algoritmo de bubble sort, que
     * tem complexidade quadrática, mas dentro do segundo loop existe a chamada de
     * um método que tem um laço e chama outro método que tem um laço, logo existem
     * 4 laços de repetição aninhados, gerando uma complexidade O(n^4)
     */
    @Override
    public List<Person> orderByAverageConsumption(List<Person> people) {
        for (int i = 0; i < people.size(); i++) {
            for (int j = 0; j < people.size() - i - 1; j++) {
                double avg1 = getPersonAverageConsumption(people.get(j));
                double avg2 = getPersonAverageConsumption(people.get(j + 1));
                if (avg2 < avg1) {
                    Person temp = people.get(j);
                    people.set(j, people.get(j + 1));
                    people.set(j + 1, temp);
                }
            }
        }
        return people;
    }

    /*
     * complexidade quadrática O(n²), pois dentro do laço de repetição é invocado
     * outro laço, através da chamada do método getPhoneAverageConsumption
     */
    private Double getPersonAverageConsumption(Person person) {
        Double avg = 0d;
        for (Phone phone : person.getPhone()) {
            avg += getPhoneAverageConsumption(phone);
        }
        return avg / person.getPhone().size();
    }

    // complexidade linear O(n), onde n é o tamanho da lista de consumo do telefone
    private Double getPhoneAverageConsumption(Phone phone) {
        Double avg = 0d;
        for (Integer consuptiom : phone.getConsumption().values()) {
            avg += consuptiom;
        }
        return avg / phone.getConsumption().size();
    }
}
