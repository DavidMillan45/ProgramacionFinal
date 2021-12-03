package model.jpa.repositories;




import model.jpa.entities.Official;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class OfficialRepositoryImpl implements OfficialRepository {

    private EntityManager entityManager;

    public OfficialRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Official> findAll() {
        return entityManager.createQuery("from Official").getResultList();
    }


    public Optional<Official> update(String username, String name, String email) {

        try {
            entityManager.getTransaction().begin();

            Official official = entityManager.find(Official.class, username);
            official.setName(name);
            official.setName(email);

            //entityManager.update(owner); // Revisar si esto es obligatorio
            entityManager.getTransaction().commit();

            return Optional.of(official);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Official> save(Official official) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(official);
            entityManager.getTransaction().commit();
            return Optional.of(official);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}