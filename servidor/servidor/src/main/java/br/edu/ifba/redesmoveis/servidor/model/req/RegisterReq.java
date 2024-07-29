package br.edu.ifba.redesmoveis.servidor.model.req;

import java.util.List;

import br.edu.ifba.redesmoveis.servidor.model.entities.Consumption;
import br.edu.ifba.redesmoveis.servidor.model.entities.Person;

public record RegisterReq(Person person, List<Consumption> consumptionPerHour) {
}
