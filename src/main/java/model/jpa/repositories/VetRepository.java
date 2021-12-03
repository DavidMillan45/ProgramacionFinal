package model.jpa.repositories;


import model.jpa.entities.Vet;

import java.util.List;
import java.util.Optional;

public interface VetRepository {

    Optional<Vet> findByUsername(String username);

    Optional<Vet> update(String username, String name, String email, String address, String neighborhood);

    List<Vet> findAll();

    Optional<Vet> save(Vet vet);

}