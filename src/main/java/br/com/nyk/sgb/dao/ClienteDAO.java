package br.com.nyk.sgb.dao;
import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Cliente;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Optional;

public class ClienteDAO {
    private final EntityManager em;

    public ClienteDAO(EntityManager entityManager) {
        this.em = entityManager;
    }

    public void inserir(Cliente cliente){
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar no banco: " + e.getMessage());
            throw e;
        }
    }

    public List<Cliente> listar(){
        String JPQL = "SELECT c FROM Cliente c";
        return em.createQuery(JPQL, Cliente.class).getResultList();
    }

    public Optional<Cliente> buscarPorNome(String name){
        try {
            String JPQL = "SELECT c FROM Cliente c WHERE c.nome = :nome";
            Cliente cliente = em.createQuery(JPQL, Cliente.class)
                    .setParameter("nome", name)
                    .getSingleResult();
            return Optional.of(cliente);
        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    public Optional<Cliente> buscarPorId(Long id){
        try {
            String JPQL = "SELECT c FROM Cliente c WHERE c.id = :id AND c.estaAtivo = TRUE";
            Cliente cliente = em.createQuery(JPQL, Cliente.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(cliente);
        } catch (NoResultException | NullPointerException e){
            return Optional.empty();
        }
    }

    public void atualizar(Cliente cliente){
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.err.println("Ocorreu um erro ao tentar atualizar o cliente: " + e.getCause());
            throw e;
        }
    }

    public void desativar(Long id){
        try {
            em.getTransaction().begin();
            String JPQL = "UPDATE Cliente c SET c.estaAtivo = FALSE WHERE c.id = :id";

            em.createQuery(JPQL).setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (PersistenceException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.err.println("Ocorreu um erro ao tentar desativar o cliente: " + e.getMessage());
            throw e;
        }
    }
}
