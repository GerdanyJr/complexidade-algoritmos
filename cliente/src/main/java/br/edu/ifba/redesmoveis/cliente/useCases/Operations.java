package br.edu.ifba.redesmoveis.cliente.useCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ifba.redesmoveis.cliente.model.entities.Consumption;
import br.edu.ifba.redesmoveis.cliente.model.entities.Person;
import br.edu.ifba.redesmoveis.cliente.model.entities.Phone;

public class Operations {

    // complexidade quadrática O(n^2)
    public static List<Consumption> getConsumptionPerHour(Person person) {
        List<Consumption> consumptionPerHour = new ArrayList<>();
        int[] hourSum = new int[24];
        Arrays.fill(hourSum, 0);

        for (Phone phone : person.getPhone()) {
            List<Consumption> consumoTelefone = phone.getConsumption();
            for (int i = 0; i < 24; i++) {
                hourSum[i] += consumoTelefone.get(i).getConsumption();
            }
        }

        for (int i = 0; i < 24; i++) {
            consumptionPerHour.add(new Consumption(i, hourSum[i]));
        }

        return consumptionPerHour;
    }
}
