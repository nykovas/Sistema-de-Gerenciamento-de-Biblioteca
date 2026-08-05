package br.com.nyk.sgb.dao;

import br.com.nyk.sgb.DTO.EmprestimoCliente;
import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Emprestimo;
import br.com.nyk.sgb.DTO.EmprestimoNomeado;
import br.com.nyk.sgb.DTO.EmprestimoTopCinco;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    private EntityManager em;

    public EmprestimoDAO(EntityManager entityManager) {
        this.em = entityManager;
    }

    public void inserir(Emprestimo emprestimo){

    }

    public List<EmprestimoNomeado> listar(){
        List<EmprestimoNomeado> emprestimoNomeados = new ArrayList<>();
        return emprestimoNomeados;
    }

    public List<EmprestimoTopCinco> listarCincoMaisEmprestados(){
        List<EmprestimoTopCinco> emprestimoTopCincos = new ArrayList<>();
        return emprestimoTopCincos;
    }

    public List<EmprestimoCliente> listarEmprestimoPorCliente(){
        List<EmprestimoCliente> emprestimoClientes = new ArrayList<>();
        return emprestimoClientes;
    }
}
