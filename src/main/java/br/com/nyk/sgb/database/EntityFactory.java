package br.com.nyk.sgb.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityFactory {
    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("sgb");

    public EntityManager entityManager(){
        return factory.createEntityManager();
    }

    public static void fecharFactory(){
        if (factory != null && factory.isOpen()){
            factory.close();
        }
    }
}
