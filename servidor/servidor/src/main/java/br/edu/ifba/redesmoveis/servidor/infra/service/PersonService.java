package br.edu.ifba.redesmoveis.servidor.infra.service;

import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;

public interface PersonService {
    void register(String payload);

    PeakTime getPeakTime();
}
