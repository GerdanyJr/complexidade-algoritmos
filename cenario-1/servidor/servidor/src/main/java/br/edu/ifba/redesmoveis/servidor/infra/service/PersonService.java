package br.edu.ifba.redesmoveis.servidor.infra.service;

import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;
import br.edu.ifba.redesmoveis.servidor.model.entities.Person;

public interface PersonService {
    void register(Person person);
    PeakTime getPeakTime();
}
