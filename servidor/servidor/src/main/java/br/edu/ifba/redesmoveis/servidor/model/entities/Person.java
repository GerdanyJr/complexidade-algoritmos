package br.edu.ifba.redesmoveis.servidor.model.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Phone> phones;

    public Person() {
    }

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