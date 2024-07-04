package br.edu.ifba.redesmoveis.cliente.model.entities;

import java.util.List;

public class Person {
    private String name;
    private List<Phone> phones;

    public Person(String name, List<Phone> phones) {
        this.name = name;
        this.phones = phones;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Phone> getPhone() {
        return phones;
    }

    public void setPhone(List<Phone> phone) {
        this.phones = phone;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}