package br.edu.ifba.redesmoveis.servidor.infra.service;

import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;
import br.edu.ifba.redesmoveis.servidor.model.req.RegisterReq;

public interface PersonService {
    void register(RegisterReq registerReq);
    PeakTime getPeakTime();
}
