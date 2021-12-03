package model.jpa.repositories;


import model.jpa.entities.PetCase;

import java.util.List;
import java.util.Optional;

public interface PetCaseRepository {


    List<PetCase> findAll();

    Optional<PetCase> save(PetCase petcase);
}