package model.jpa.repositories;


import model.jpa.entities.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Optional<Pet> findById(Integer pet_id);

    List<Pet> findAll();

    Optional<Pet> update(Integer pet_id, String name, String species, String race, String size, String sex, String picture);

    Optional<Pet> updateMicrochip(Integer pet_id, String microchip);

    Optional<Pet> save(Pet pet);

}