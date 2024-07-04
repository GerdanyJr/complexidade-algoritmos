package br.edu.ifba.redesmoveis.servidor.infra.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.redesmoveis.servidor.infra.repository.PersonRepository;
import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;
import br.edu.ifba.redesmoveis.servidor.infra.service.PersonService;
import br.edu.ifba.redesmoveis.servidor.model.entities.Consumption;
import br.edu.ifba.redesmoveis.servidor.model.req.RegisterReq;
import br.edu.ifba.redesmoveis.servidor.useCases.Operations;
import br.edu.ifba.redesmoveis.servidor.util.ConsumptionUtil;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final Operations operations;
    private final List<Consumption> consumptions;

    public PersonServiceImpl(PersonRepository personRepository, Operations operations) {
        this.personRepository = personRepository;
        this.operations = operations;
        consumptions = ConsumptionUtil.fillConsumptions();
    }

    @Override
    public void register(RegisterReq registerReq) {
        System.out.println("Registrando pessoa com nome " + registerReq.person().getName());
        personRepository.save(registerReq.person());

        var consumptionPerHour = registerReq.consumptionPerHour();

        for (int i = 0; i < consumptionPerHour.size(); i++) {
            for (int j = 0; j < consumptions.size(); j++) {
                Consumption consumption = consumptions.get(j);

                if (consumptionPerHour.get(i).getHourOfDay() == consumption.getHourOfDay()) {
                    consumption
                            .setConsumption(
                                    consumption.getConsumption() +
                                            consumptionPerHour.get(i).getConsumption());
                }
            }
        }
    }

    @Override
    public PeakTime getPeakTime() {
        return operations.findPeakTime(consumptions);
    }

}
