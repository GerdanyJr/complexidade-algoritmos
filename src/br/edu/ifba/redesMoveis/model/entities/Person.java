package br.edu.ifba.redesMoveis.model.entities;

import java.util.List;

public class Person {
    private Integer id;
    private String name;
    private List<Phone> phones;

    public Person(Integer id, String name, List<Phone> phones) {
        this.id = id;
        this.name = name;
        this.phones = phones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", phones=" + phones + "]";
    }
}