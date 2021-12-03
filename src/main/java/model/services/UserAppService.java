package model.services;


import model.jpa.entities.UserApp;
import model.jpa.repositories.UserAppRepository;
import model.jpa.repositories.UserAppRepositoryImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless

public class UserAppService {


    UserAppRepository userappRepository;

    public Optional<String> validateUser(String username, String password) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Getting credentials from the database
        userappRepository = new UserAppRepositoryImpl(entityManager);
        Optional<UserApp> user = userappRepository.findByUsername(username);

        entityManager.close();
        entityManagerFactory.close();

        // Validating if credentials provided by the user are valid
        // If success, return the user role
        if (user.isPresent()) {
            if (user.get().getUsername().equals(username) && user.get().getPassword().equals(password)) {
                return Optional.of(user.get().getRole());
            }
        }

        return Optional.empty();

    }
    /*public UserApp saveUserApp(String username, String password, String email, String role) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userappRepository = new UserAppRepositoryImpl(entityManager);
        Optional<UserApp > userapp = castRepository.save(new Cast(role), movieId, actorId);
        entityManager.close();
        entityManagerFactory.close();
        CastPOJO castPOJO = null;
        if (cast.isPresent()) {
            castPOJO = new CastPOJO(cast.get().getCastId(), cast.get().getMovie(), cast.get().getActor(), cast.get().getRole());
        }
        return castPOJO;
    }*/


    public void updateUserApp(String username, String email) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userappRepository = new UserAppRepositoryImpl(entityManager);
        userappRepository.update(username, email);

        entityManager.close();
        entityManagerFactory.close();

    }

}