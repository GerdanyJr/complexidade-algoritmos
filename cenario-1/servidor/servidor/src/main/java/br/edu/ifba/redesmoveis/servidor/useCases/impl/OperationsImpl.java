package br.edu.ifba.redesmoveis.servidor.useCases.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;
import br.edu.ifba.redesmoveis.servidor.model.entities.Consumption;
import br.edu.ifba.redesmoveis.servidor.model.entities.Person;
import br.edu.ifba.redesmoveis.servidor.model.entities.Phone;
import br.edu.ifba.redesmoveis.servidor.useCases.Operations;

@Component
public class OperationsImpl implements Operations {
    @Override
    public PeakTime findPeakTime(List<Person> people) {
        List<Consumption> consumptionPerHour = getConsumptionPerHour(people);
        String peakHour = "";
        Integer peakConsumption = 0;

        for (Consumption consumption : consumptionPerHour) {
            if (consumption.getConsumption() > peakConsumption) {
                peakHour = String.valueOf(consumption.getHourOfDay());
                peakConsumption = consumption.getConsumption();
            }
        }

        return new PeakTime(peakHour, peakConsumption);
    }

    /*
     * Complexidade cúbica O(n^3), pois
     * possui três loops aninhados
     */
    private List<Consumption> getConsumptionPerHour(List<Person> people) {
        List<Consumption> consumptionPerHour = new ArrayList<>();
        int[] hourSum = new int[24];
        Arrays.fill(hourSum, 0);

        for (Person person : people) {
            for (Phone phone : person.getPhone()) {
                List<Consumption> consumoTelefone = phone.getConsumption();
                for (int i = 0; i < 24; i++) {
                    hourSum[i] += consumoTelefone.get(i).getConsumption();
                }
            }
        }
        for (int i = 0; i < 24; i++) {
            consumptionPerHour.add(new Consumption(i, hourSum[i]));
        }

        return consumptionPerHour;
    }
}
