package model.services;

import model.jpa.entities.Official;
import model.jpa.repositories.OfficialRepository;
import model.jpa.repositories.OfficialRepositoryImpl;
import model.resources.pojos.OfficialPojo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless

public class OfficialService {
    OfficialRepository officialRepository;

    public List<OfficialPojo> listOfficials() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        List<Official> officials = officialRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<OfficialPojo> officialsPojo = new ArrayList<>();
        for (Official official : officials) {
            officialsPojo.add(new OfficialPojo(
                    official.getUsername(),
                    official.getName()
            ));
        }

        return officialsPojo;

    }

    public Optional<OfficialPojo> saveOfficial(OfficialPojo officialPojo) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);

        Official official = new Official(officialPojo.getUsername(), officialPojo.getPassword(), officialPojo.getEmail(), officialPojo.getName());
        Optional<Official> persistedOfficial = officialRepository.save(official);

        entityManager.close();
        entityManagerFactory.close();

        if (persistedOfficial.isPresent()) {
            return Optional.of(new OfficialPojo(persistedOfficial.get().getUsername(),
                    persistedOfficial.get().getPassword(),
                    persistedOfficial.get().getEmail(),
                    persistedOfficial.get().getName()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<OfficialPojo> updateOfficial(String username, String name, String email) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        Optional<Official> persistedOfficial = officialRepository.update(username, name, email);

        entityManager.close();
        entityManagerFactory.close();

        return persistedOfficial.map(value -> new OfficialPojo(
                value.getUsername(),
                value.getName()

        ));
    }

    public void updateOfficial(OfficialPojo officialPojo) {
    }
}