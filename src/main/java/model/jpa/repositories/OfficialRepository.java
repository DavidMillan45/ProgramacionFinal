package model.jpa.repositories;


import model.jpa.entities.Official;

import java.util.List;
import java.util.Optional;

public interface OfficialRepository {

    List<Official> findAll();

    Optional<Official> save(Official author);

    Optional<Official> update(String username, String name);

}