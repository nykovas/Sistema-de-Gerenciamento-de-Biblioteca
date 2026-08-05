package br.com.nyk.sgb.dao;

import br.com.nyk.sgb.DTO.EmprestimoCliente;
import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Emprestimo;
import br.com.nyk.sgb.DTO.EmprestimoNomeado;
import br.com.nyk.sgb.DTO.EmprestimoTopCinco;
import jakarta.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    private final EntityManager em;

    public EmprestimoDAO(EntityManager entityManager) {
        this.em = entityManager;
    }

    public void inserir(Emprestimo emprestimo){
        try {
            em.getTransaction().begin();
            em.persist(emprestimo);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            if (em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.out.println("Ocorreu um erro ao tentar salvar o empréstimo: " + e);
            throw e;
        }

    }

    public List<EmprestimoNomeado> listar(){
        String JPQL = """
                    SELECT new br.com.nyk.sgb.DTO.EmprestimoNomeado(
                        e.id,
                        e.cliente.nome,
                        e.livro.titulo,
                        e.dataEmprestimo
                    ) FROM Emprestimo e
                    ORDER BY e.id
                    """;
        return em.createQuery(JPQL, EmprestimoNomeado.class)
                .getResultList();
    }

    public List<EmprestimoTopCinco> listarCincoMaisEmprestados(){
        String JPQL = """
                    SELECT new br.com.nyk.sgb.DTO.EmprestimoTopCinco(
                        e.livro.titulo,
                        COUNT(e)
                    )
                    FROM Emprestimo e
                    GROUP BY e.livro.titulo
                    ORDER BY COUNT(e) DESC
                    """;
        return em.createQuery(JPQL, EmprestimoTopCinco.class)
                .setMaxResults(5)
                .getResultList();
    }

    public List<EmprestimoCliente> listarEmprestimoPorCliente(){
        String JPQL = """
                    SELECT new br.com.nyk.sgb.DTO.EmprestimoCliente(
                        e.cliente.nome,
                        COUNT(e)
                    )
                    FROM Emprestimo e
                    GROUP BY e.cliente.nome
                    ORDER BY COUNT(e) DESC
                    """;
        return em.createQuery(JPQL, EmprestimoCliente.class)
                .setMaxResults(5)
                .getResultList();
    }
}
