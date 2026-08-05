package br.com.nyk.sgb.dao;

import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Livro;
import jakarta.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroDAO {
    private final EntityManager em;

    public LivroDAO(EntityManager entityManager){
        this.em = entityManager;
    }

    public void inserir(Livro livro){
        try {
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }

            System.err.println("Ocorreu um erro ao tentar inserir o livro: " + e);
            throw e;
        }
    }

    public List<Livro> listar(){
        String JPQL = "SELECT l FROM Livro l WHERE l.estoque > 0 ORDER BY l.id";
        return em.createQuery(JPQL, Livro.class).getResultList();
    }

    public List<Livro> buscaPorTitulo(String titulo){
        String JPQL = "Select l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(:titulo) AND l.estoque > 0 ORDER BY l.id";
        return em.createQuery(JPQL, Livro.class)
                .setParameter("titulo", "%" + titulo + "%")
                .getResultList();
    }

    public List<Livro> buscaPorGenero(String genero){
        String JPQL = "Select l FROM Livro l WHERE LOWER(l.genero) like LOWER(:genero) AND l.estoque > 0 ORDER BY l.id";
        return em.createQuery(JPQL, Livro.class)
                .setParameter("genero", "%" + genero + "%")
                .getResultList();
    }

    public void removerLivro(Long id) {
        try {
            em.getTransaction().begin();
            String JPQL = "UPDATE Livro l SET l.estoque = 0 WHERE l.id = :id";
            em.createQuery(JPQL)
                    .setParameter("id", id)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (PersistenceException e){
            if (em.isOpen()){
                em.getTransaction().rollback();
            }
            System.err.println("Ocorreu um erro ao tentar remover o livro: " + e.getMessage());
        }
    }

    public void atualizarLivro(Livro livro) {
        try {
            em.getTransaction().begin();
            em.merge(livro);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.err.println("Ocorreu um erro ao tentar atualizar o livro: " + e);
            throw e;
        }
    }
}
