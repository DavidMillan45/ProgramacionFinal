package model.services;


import model.jpa.entities.Pet;
import model.jpa.entities.Visit;
import model.jpa.repositories.PetRepository;
import model.jpa.repositories.PetRepositoryImpl;
import model.jpa.repositories.VisitRepository;
import model.jpa.repositories.VisitRepositoryImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;


@Stateless
public class VisitService {
    VisitRepository visitRepository;
    PetRepository petRepository;

    public void saveVisit(String created_at, String type, String description, Integer pet_id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        petRepository = new PetRepositoryImpl(entityManager);

        Optional<Pet> pet = petRepository.findById(pet_id);
        pet.ifPresent(p -> {
            p.addVisit(new Visit(created_at, type, description));
            petRepository.save(p);
        });

        entityManager.close();
        entityManagerFactory.close();

        return;

    }
}