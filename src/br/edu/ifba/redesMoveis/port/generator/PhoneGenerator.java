package br.edu.ifba.redesMoveis.port.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import br.edu.ifba.redesMoveis.model.entities.Phone;
import br.edu.ifba.redesMoveis.model.enums.Company;

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
            Map<String, Integer> consuption = generateConsuption();
            phones.add(new Phone(company, number, consuption));
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

    // complexidade constante O(24)
    private Map<String, Integer> generateConsuption() {
        Random random = new Random();
        Map<String, Integer> consuption = new TreeMap<>();
        for (int i = 0; i < 24; i++) {
            consuption.put(String.format("%02d:00", i), random.nextInt(CONSUMO_MAXIMO));
        }
        return consuption;
    }
}
