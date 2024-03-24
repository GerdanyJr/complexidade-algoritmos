package br.edu.ifba.redesMoveis.usecases.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifba.redesMoveis.model.entities.Person;
import br.edu.ifba.redesMoveis.model.entities.Phone;
import br.edu.ifba.redesMoveis.port.util.Util;
import br.edu.ifba.redesMoveis.usecases.orderer.PersonOrderer;
import br.edu.ifba.redesMoveis.usecases.orderer.PersonOrdererImpl;

public class OperationsImpl implements Operations<Person> {

    private final PersonOrderer personOrderer;

    public OperationsImpl() {
        this.personOrderer = new PersonOrdererImpl();
    }

    /*
     * Complexidade linear O(n), onde n é o tamanho da lista people
     */
    @Override
    public void print(List<Person> people) {
        for (Person person : people) {
            System.out.println("Pessoa monitorada " + person.getId() + ", nome: " + person.getName());
        }
    }

    /*
     * Complexidade quadrática O(n^2)
     */
    @Override
    public void printWithPhone(List<Person> people) {
        for (Person person : people) {
            System.out.println("Lista de telefones de: " + person.getName());
            for (Phone phone : person.getPhone()) {
                System.out.println("Operadora: " + phone.getCompany() + ", número: " + phone.getNumber());
            }
            System.out.println();
        }
    }

    /*
     * Complexidade O(n^4)
     */
    @Override
    public List<Person> orderByConsumption(List<Person> people) {
        return personOrderer.orderByAverageConsumption(people);
    }

    /*
     * Complexidade cúbica O(n^3), pois chama o método getConsumptionPerHour, que
     * possui três loops aninhados
     */
    @Override
    public Map<String, Integer> findPeakTime(List<Person> people) {
        Map<String, Integer> consumptionPerHour = getConsumptionPerHour(people);
        Map<String, Integer> peakTime = new HashMap<>();
        peakTime.put("", 0);

        for (Map.Entry<String, Integer> entry : consumptionPerHour.entrySet()) {
            Integer currentValue = entry.getValue();
            Integer maxValue = peakTime.values().iterator().next();

            if (currentValue > maxValue) {
                peakTime.clear();
                peakTime.put(entry.getKey(), currentValue);
            } else if (currentValue.equals(maxValue)) {
                peakTime.put(entry.getKey(), currentValue);
            }
        }
        return peakTime;
    }

    /*
     * Complexidade cúbica O(n^3), pois
     * possui três loops aninhados
     */
    private Map<String, Integer> getConsumptionPerHour(List<Person> people) {
        Map<String, Integer> consumptionPerHour = Util.getDayHours();
        for (Person person : people) {
            for (Phone phone : person.getPhone()) {
                Map<String, Integer> consumption = phone.getConsumption();
                for (Map.Entry<String, Integer> entry : consumption.entrySet()) {
                    String hour = entry.getKey();
                    int value = entry.getValue();
                    consumptionPerHour.put(hour, consumptionPerHour.get(hour) + value);
                }
            }
        }
        return consumptionPerHour;
    }

}
