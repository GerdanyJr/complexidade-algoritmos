package br.edu.ifba.redesmoveis.servidor.useCases;

import java.util.List;

import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;
import br.edu.ifba.redesmoveis.servidor.model.entities.Consumption;

public interface Operations {
    PeakTime findPeakTime(List<Consumption> consumptions);
}
