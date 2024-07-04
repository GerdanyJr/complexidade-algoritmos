package br.edu.ifba.redesmoveis.cliente.model.req;

import java.util.List;

import br.edu.ifba.redesmoveis.cliente.model.entities.Consumption;
import br.edu.ifba.redesmoveis.cliente.model.entities.Person;

public record RegisterReq(Person person, List<Consumption> consumptionPerHour) {
    
}
