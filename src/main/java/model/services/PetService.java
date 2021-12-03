package model.services;


import model.jpa.entities.Pet;
import model.jpa.repositories.PetRepository;
import model.jpa.repositories.PetRepositoryImpl;
import model.resources.pojos.PetPojo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PetService {

    PetRepository PetRepository;

    public List<PetPojo> listPet() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PetRepository = new PetRepositoryImpl(entityManager);
        List<Pet> pets = PetRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<PetPojo> petsPojo = new ArrayList<>();
        for (Pet pet : pets) {
            petsPojo.add(new PetPojo(
                    pet.getPet_id(),
                    pet.getMicrochip(),
                    pet.getName(),
                    pet.getEspecies(),
                    pet.getRace(),
                    pet.getSize(),
                    pet.getSex(),
                    pet.getPicture(),
                    pet.getOwner().getPerson_id()
            ));
        }

        return petsPojo;

    }

    public Pet savePet(String microchip, String name, String species, String race, String size, String sex, String picture) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PetRepository = new PetRepositoryImpl(entityManager);

        Pet pet = new Pet(microchip, name, species, race, size, sex, picture);
        Pet persistedPet = PetRepository.save(pet).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPet;

    }

    public void updatePet(Integer pet_id, String name, String species, String race, String size, String sex, String picture) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PetRepository = new PetRepositoryImpl(entityManager);
        PetRepository.update(pet_id, name, species, race, size, sex, picture);

        entityManager.close();
        entityManagerFactory.close();

    }

    public void updatePetMicrochi(Integer pet_id, String microchip) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        PetRepository = new PetRepositoryImpl(entityManager);
        PetRepository.updateMicrochip(pet_id, microchip);

        entityManager.close();
        entityManagerFactory.close();


    }
}