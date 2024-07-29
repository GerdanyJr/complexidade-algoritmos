package br.edu.ifba.redesmoveis.servidor.useCases.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;
import br.edu.ifba.redesmoveis.servidor.model.entities.Consumption;
import br.edu.ifba.redesmoveis.servidor.useCases.Operations;

@Component
public class OperationsImpl implements Operations {
    @Override
    public PeakTime findPeakTime(List<Consumption> consumptions) {
        Consumption peakConsumption = consumptions.get(0);

        for (int i = 0; i < consumptions.size(); i++) {
            Consumption current = consumptions.get(i);
            System.out.println(current.getHourOfDay() + ":" + current.getConsumption());
            if (current.getConsumption() > peakConsumption.getConsumption()) {
                peakConsumption = current;
            }
        }

        return new PeakTime(peakConsumption.getHourOfDay().toString(), peakConsumption.getConsumption());
    }

}
