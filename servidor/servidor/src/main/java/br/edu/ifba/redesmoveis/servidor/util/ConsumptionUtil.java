package br.edu.ifba.redesmoveis.servidor.util;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.redesmoveis.servidor.model.entities.Consumption;

public class ConsumptionUtil {
    public static List<Consumption> fillConsumptions() {
        List<Consumption> consumptions = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            consumptions.add(new Consumption(i, 0));
        }
        return consumptions;
    }
}
