package model.jpa.repositories;


import model.jpa.entities.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    List<Owner> findAll();

    Optional<Owner> save(Owner book);

    Optional<Owner> update(String userame, String name,String adress, String neighborhood);

}