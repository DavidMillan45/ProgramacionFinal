package model.jpa.repositories;


import model.jpa.entities.Owner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class OwnerRepositoryImpl implements OwnerRepository {

    private EntityManager entityManager;

    public OwnerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /*public Optional<Book> findById(Integer id) {
        Book book = entityManager.find(Book.class, id);
        return book != null ? Optional.of(book) : Optional.empty();
    }
    public Optional<Book> findByTitle(String title) {
        Book book = entityManager.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class)
                .setParameter("title", title)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }
    public Optional<Book> findByTitleNamedQuery(String title) {
        Book book = entityManager.createNamedQuery("Book.findByTitle", Book.class)
                .setParameter("title", title)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }*/

    public List<Owner> findAll() {
        return entityManager.createQuery("from Owner").getResultList();
    }

    public Optional<Owner> update(String username, String email,String name, String address, String neighborhood) {

        try {
            entityManager.getTransaction().begin();

            Owner owner = entityManager.find(Owner.class, username);
            owner.setName(name);
            owner.setEmail(email);
            owner.setAddress(address);
            owner.setNeighborhood(neighborhood);


            //entityManager.update(owner); // Revisar si esto es obligatorio
            entityManager.getTransaction().commit();

            return Optional.of(owner);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Optional<Owner> save(Owner owner) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}