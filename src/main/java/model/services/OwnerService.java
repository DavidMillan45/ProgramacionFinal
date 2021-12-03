package model.services;



import model.jpa.entities.Owner;
import model.jpa.repositories.OwnerRepository;
import model.jpa.repositories.OwnerRepositoryImpl;
import model.resources.pojos.OwnerPojo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless

public class OwnerService {

    OwnerRepository ownerRepository;

   /* public List<OwnerPojo> listOwners() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ownerRepository = new OwnerRepositoryImpl(entityManager);
        List<Owner> owners = ownerRepository.findAll();
        entityManager.close();
        entityManagerFactory.close();
        List<OwnerPojo> ownersPojo = new ArrayList<>();
        for (Owner owner : owners) {
            ownersPojo.add(new OwnerPojo(
                    owner.getUsername(),
                    owner.getPerson_id(),
                    owner.getName(),
                    owner.getAdress(),
                    owner.getNeighborhood()
            ));
        }
        return ownersPojo;
    }*/

    public Optional<OwnerPojo> saveOwner(OwnerPojo ownerpojo) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);

        Owner owner = new Owner(ownerpojo.getUsername(), ownerpojo.getPassword(), ownerpojo.getEmail(),
                ownerpojo.getPerson_id(), ownerpojo.getName(), ownerpojo.getAdress(), ownerpojo.getNeighborhood());
        Optional<Owner> persistedOwner = ownerRepository.save(owner);

        entityManager.close();
        entityManagerFactory.close();

        if (persistedOwner.isPresent()) {
            return Optional.of(new OwnerPojo(persistedOwner.get().getUsername(),
                    persistedOwner.get().getPassword(),
                    persistedOwner.get().getEmail(),
                    persistedOwner.get().getPerson_id(),
                    persistedOwner.get().getName(),
                    persistedOwner.get().getAddress(),
                    persistedOwner.get().getNeighborhood()));
        } else {
            return Optional.empty();
        }

    }



   /*public void updateOwner(String username, String name, String addres, String neighborhood) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        ownerRepository.update(username, name, addres, neighborhood);

        entityManager.close();
        entityManagerFactory.close();

    }*/

    public void updateOwner2(OwnerPojo ownerPojo) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        ownerRepository.update(ownerPojo.getUsername(), ownerPojo.getName(), ownerPojo.getAdress(),ownerPojo.getEmail() ,ownerPojo.getNeighborhood());

        entityManager.close();
        entityManagerFactory.close();

    }
}