package br.edu.ifba.redesmoveis.cliente.port.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ifba.redesmoveis.cliente.model.entities.Consumption;
import br.edu.ifba.redesmoveis.cliente.model.entities.Phone;
import br.edu.ifba.redesmoveis.cliente.model.enums.Company;

public class PhoneGenerator {
    private static final Integer CONSUMO_MAXIMO = 1000;
    private final Random random;

    public PhoneGenerator() {
        this.random = new Random();
    }

    // complexidade linear O(n) onde n é numberToGenerate
    public List<Phone> generatePhones(int numberToGenerate) {
        List<Phone> phones = new ArrayList<>();
        for (int j = 0; j < numberToGenerate; j++) {
            Company company = generateRandomCompany();
            String number = generatePhoneNumber();
            List<Consumption> consuption = generateConsumptions();
            phones.add(new Phone(consuption, company, number));
        }
        return phones;
    }

    // complexidade constante O(1)
    private String generatePhoneNumber() {
        return "77" + random.nextInt(900000000);
    }

    // complexidade constante O(1)
    private Company generateRandomCompany() {
        Company company;
        int number = random.nextInt(4);
        switch (number) {
            case 0:
                company = Company.OI;
                break;
            case 1:
                company = Company.CLARO;
                break;
            case 2:
                company = Company.VIVO;
                break;
            case 3:
                company = Company.TIM;
                break;
            default:
                company = Company.OI;
                break;
        }
        return company;
    }

    // complexidade linear O(24)
    private List<Consumption> generateConsumptions() {
        List<Consumption> consumptions = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            consumptions.add(new Consumption(i, random.nextInt(CONSUMO_MAXIMO)));
        }
        return consumptions;
    }
}
