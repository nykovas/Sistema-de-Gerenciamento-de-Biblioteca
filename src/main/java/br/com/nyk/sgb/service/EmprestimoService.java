package br.com.nyk.sgb.service;

import br.com.nyk.sgb.dao.EmprestimoDAO;
import br.com.nyk.sgb.database.EntityFactory;
import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Emprestimo;
import br.com.nyk.sgb.DTO.EmprestimoCliente;
import br.com.nyk.sgb.DTO.EmprestimoNomeado;
import br.com.nyk.sgb.DTO.EmprestimoTopCinco;

import java.util.List;

public class EmprestimoService {

    private final EntityFactory entity;

    public EmprestimoService() {
        this.entity = new EntityFactory();
    }

    public void criarEmprestimo(Emprestimo emprestimo){
        EntityManager em = entity.entityManager();
        // validarEmprestimo(emprestimo);
        new EmprestimoDAO(em).inserir(emprestimo);
    }

    public List<EmprestimoNomeado> listarEmprestimos(){
        EntityManager em = entity.entityManager();
        return new EmprestimoDAO(em).listar();
    }

    public List<EmprestimoTopCinco> listarTopCinco(){
        EntityManager em = entity.entityManager();
        return new EmprestimoDAO(em).listarCincoMaisEmprestados();
    }

    public List<EmprestimoCliente> listarQuantidadeDeEmprestimosPorCliente() {
        EntityManager em = entity.entityManager();
        return new EmprestimoDAO(em).listarEmprestimoPorCliente();
    }

}
