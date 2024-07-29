package br.edu.ifba.redesmoveis.servidor.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.redesmoveis.servidor.model.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {

}
