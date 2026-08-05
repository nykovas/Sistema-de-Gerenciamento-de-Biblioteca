package br.com.nyk.sgb.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityFactory {

    public EntityManager entityManager(){
        return entityManagerFactory().createEntityManager();
    }

    private EntityManagerFactory entityManagerFactory (){
        return Persistence
                .createEntityManagerFactory("sgb");
    }
}
