package br.edu.ifba.redesmoveis.servidor.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Consumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer hourOfDay;
    private Integer consumption;

    public Consumption() {
    }

    public Consumption(Integer hourOfDay, Integer consumption) {
        this.hourOfDay = hourOfDay;
        this.consumption = consumption;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(Integer hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

}
