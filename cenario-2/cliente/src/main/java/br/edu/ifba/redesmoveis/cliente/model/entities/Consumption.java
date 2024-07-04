package br.edu.ifba.redesmoveis.cliente.model.entities;

public class Consumption {
    private Integer hourOfDay;
    private Integer consumption;

    public Consumption(Integer hourOfDay, Integer consumption) {
        this.hourOfDay = hourOfDay;
        this.consumption = consumption;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

    public Integer getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(Integer hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

}
