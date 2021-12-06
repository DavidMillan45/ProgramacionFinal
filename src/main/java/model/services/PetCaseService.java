package model.services;


import model.jpa.entities.Pet;
import model.jpa.entities.PetCase;
import model.jpa.repositories.PetCaseRepository;
import model.jpa.repositories.PetCaseRepositoryImpl;
import model.jpa.repositories.PetRepository;
import model.jpa.repositories.PetRepositoryImpl;
import model.resources.pojos.PetCasePojo;
import model.resources.pojos.PetPojo;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Stateless
public class PetCaseService {

    PetCaseRepository petCaseRepository;
    PetRepository petRepository;

    public List<PetCasePojo> listPetcase(String pet_id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petCaseRepository = new PetCaseRepositoryImpl(entityManager);
        List<PetCase> petscases = petCaseRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<PetCasePojo> petscasePojo = new ArrayList<>();
        for (PetCase petcase : petscases) {
            if (petcase.getPet().getPet_id().equals(pet_id)) {
                petscasePojo.add(new PetCasePojo(
                        petcase.getCase_id(),
                        petcase.getCreated_at(),
                        petcase.getType(),
                        petcase.getDescription(),
                        petcase.getPet().getPet_id()
                ));
            }
        }

        return petscasePojo;

    }

    public void savePetCase(String case_id,String created_at, String type, String description, String pet_id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petCaseRepository = new PetCaseRepositoryImpl(entityManager);
        petRepository = new PetRepositoryImpl(entityManager);

        Optional<Pet> pet = petRepository.findById(pet_id);
        pet.ifPresent(p -> {
            p.addPetCase(new PetCase(case_id,created_at, type, description));
            petRepository.save(p);
        });
        entityManager.close();
        entityManagerFactory.close();

        return;

    }


}