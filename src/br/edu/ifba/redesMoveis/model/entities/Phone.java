package br.edu.ifba.redesMoveis.model.entities;

import java.util.Map;

import br.edu.ifba.redesMoveis.model.enums.Company;

public class Phone {
    private Map<String, Integer> consumption;
    private Company company;
    private String number;

    public Phone(Company company, String number, Map<String, Integer> consumption) {
        this.company = company;
        this.number = number;
        this.consumption = consumption;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Map<String, Integer> getConsumption() {
        return consumption;
    }

    public void setConsumption(Map<String, Integer> consumption) {
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "Phone [company=" + company + ", number=" + number + ", consumption=" + consumption + "]";
    }

}
