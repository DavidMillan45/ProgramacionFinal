package model.jpa.repositories;


import model.jpa.entities.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Optional<Pet> findById(String pet_id);

    List<Pet> findAll();

    Optional<Pet> update(String pet_id, String name, String species, String race, String size, String sex, String picture);

    Optional<Pet> updateMicrochip(String pet_id, String microchip);

    Optional<Pet> save(Pet pet);

}