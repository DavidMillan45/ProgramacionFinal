package model.jpa.repositories;

import model.jpa.entities.UserApp;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserAppRepositoryImpl implements UserAppRepository {

    private EntityManager entityManager;

    public UserAppRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<UserApp> findAll() {
        return entityManager.createQuery("from UserApp").getResultList();
    }

    public Optional<UserApp> findByUsername(String username) {
        UserApp user = entityManager.find(UserApp.class, username);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public Optional<UserApp> update(String username, String email) {

        try {
            entityManager.getTransaction().begin();

            UserApp userapp = entityManager.find(UserApp.class, username);
            userapp.setEmail(email);

            //entityManager.update(owner); // Revisar si esto es obligatorio
            entityManager.getTransaction().commit();

            return Optional.of(userapp);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<UserApp> save(UserApp userap) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userap);
            entityManager.getTransaction().commit();
            return Optional.of(userap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}