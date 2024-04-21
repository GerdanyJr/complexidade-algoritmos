package br.edu.ifba.redesmoveis.servidor.infra.service.impl;

import org.springframework.stereotype.Service;

import br.edu.ifba.redesmoveis.servidor.infra.repository.PersonRepository;
import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;
import br.edu.ifba.redesmoveis.servidor.infra.service.PersonService;
import br.edu.ifba.redesmoveis.servidor.model.entities.Person;
import br.edu.ifba.redesmoveis.servidor.useCases.Operations;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final Operations operations;

    public PersonServiceImpl(PersonRepository personRepository, Operations operations) {
        this.personRepository = personRepository;
        this.operations = operations;
    }

    @Override
    public void register(Person person) {
        personRepository.save(person);
    }

    @Override
    public PeakTime getPeakTime() {
        return operations.findPeakTime(personRepository.findAll());
    }

}
