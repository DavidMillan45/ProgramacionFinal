package model.services;


import model.jpa.entities.Owner;
import model.jpa.entities.Pet;
import model.jpa.repositories.PetRepository;
import model.jpa.repositories.PetRepositoryImpl;
import model.resources.pojos.OwnerPojo;
import model.resources.pojos.PetPojo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class PetService {

    PetRepository PetRepository;

    public List<PetPojo> listPet(String owner_id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PetRepository = new PetRepositoryImpl(entityManager);
        List<Pet> pets = PetRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<PetPojo> petsPojo = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getOwner().getUsername().equals(owner_id)) {
                petsPojo.add(new PetPojo(
                        pet.getPet_id(),
                        pet.getMicrochip(),
                        pet.getName(),
                        pet.getEspecies(),
                        pet.getRace(),
                        pet.getSize(),
                        pet.getSex(),
                        pet.getOwner().getPerson_id()
                ));
            }
        }

        return petsPojo;

    }
    public Optional<PetPojo> savePet(String pet_id, String microchip, String name, String species, String race, String size, String sex) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PetRepository = new PetRepositoryImpl(entityManager);

        Pet pet = new Pet(pet_id,microchip, name, species, race, size, sex);
        Optional<Pet> persistedPet = PetRepository.save(pet);

        entityManager.close();
        entityManagerFactory.close();
        if (persistedPet.isPresent()) {
            return Optional.of(new PetPojo(persistedPet.get().getPet_id(),
                    persistedPet.get().getMicrochip(),
                    persistedPet.get().getName(),
                    persistedPet.get().getEspecies(),
                    persistedPet.get().getName(),
                    persistedPet.get().getRace(),
                    persistedPet.get().getSex(),
                    persistedPet.get().getOwner().getPerson_id()));
        } else {
            return Optional.empty();
        }

    }

    public void updatePet(String pet_id, String name, String species, String race, String size, String sex) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PetRepository = new PetRepositoryImpl(entityManager);
        PetRepository.update(pet_id, name, species, race, size, sex);

        entityManager.close();
        entityManagerFactory.close();

    }

    public void updatePetMicrochi(String pet_id, String microchip) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PetRepository = new PetRepositoryImpl(entityManager);
        PetRepository.updateMicrochip(pet_id, microchip);

        entityManager.close();
        entityManagerFactory.close();


    }

}