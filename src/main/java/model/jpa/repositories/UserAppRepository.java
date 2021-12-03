package model.jpa.repositories;


import model.jpa.entities.UserApp;

import java.util.List;
import java.util.Optional;

public interface UserAppRepository {

    List<UserApp> findAll();

    Optional<UserApp> save(UserApp userapp);

    Optional<UserApp> findByUsername(String username);

    Optional<UserApp> update(String username, String email);
}
