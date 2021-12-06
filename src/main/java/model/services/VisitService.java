package model.services;


import model.jpa.entities.Pet;
import model.jpa.entities.PetCase;
import model.jpa.entities.Visit;
import model.jpa.repositories.*;
import model.resources.pojos.PetCasePojo;
import model.resources.pojos.VisitPojo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Stateless
public class VisitService {
    VisitRepository visitRepository;
    PetRepository petRepository;

    public List<VisitPojo> listvisit(String username, String pet_id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        List<Visit> visits = visitRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<VisitPojo> visitPojo = new ArrayList<>();
        for (Visit visit : visits) {
            if (visit.getPet().getPet_id().equals(pet_id) && visit.getVet().getUsername().equals(username)) {
                visitPojo.add(new VisitPojo(
                        visit.getVisit_id(),
                        visit.getCreated_at(),
                        visit.getType(),
                        visit.getDescription(),
                        visit.getVet().getUsername(),
                        visit.getPet().getPet_id()
                ));
            }
        }

        return visitPojo;

    }

    public void saveVisit(String created_at, String type, String description, String pet_id) {

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