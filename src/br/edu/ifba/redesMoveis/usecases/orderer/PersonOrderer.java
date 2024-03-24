package br.edu.ifba.redesMoveis.usecases.orderer;

import java.util.List;

import br.edu.ifba.redesMoveis.model.entities.Person;

public interface PersonOrderer {
    public List<Person> orderByAverageConsumption(List<Person> people);
}
