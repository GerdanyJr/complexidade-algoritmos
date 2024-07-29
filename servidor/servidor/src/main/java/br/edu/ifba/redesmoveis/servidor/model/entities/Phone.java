package br.edu.ifba.redesmoveis.servidor.model.entities;

import java.util.List;

import br.edu.ifba.redesmoveis.servidor.model.enums.Company;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Consumption> consumption;
    @Enumerated(EnumType.STRING)
    private Company company;
    @Column(unique = true)
    private String number;

    public Phone() {
    }

    public Phone(Integer id, List<Consumption> consumption, Company company, String number) {
        this.id = id;
        this.consumption = consumption;
        this.company = company;
        this.number = number;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Phone [company=" + company + ", number=" + number + ", consumption=" + consumption + "]";
    }

    public List<Consumption> getConsumption() {
        return consumption;
    }

    public void setConsumption(List<Consumption> consumption) {
        this.consumption = consumption;
    }

}
