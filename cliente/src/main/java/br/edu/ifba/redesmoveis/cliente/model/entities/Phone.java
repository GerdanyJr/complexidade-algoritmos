package br.edu.ifba.redesmoveis.cliente.model.entities;

import java.util.List;

import br.edu.ifba.redesmoveis.cliente.model.enums.Company;

public class Phone {
    private List<Consumption> consumption;
    private Company company;
    private String number;

    public Phone(List<Consumption> consumption, Company company, String number) {
        this.consumption = consumption;
        this.company = company;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Consumption> getConsumption() {
        return consumption;
    }

    public void setConsumption(List<Consumption> consumption) {
        this.consumption = consumption;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
