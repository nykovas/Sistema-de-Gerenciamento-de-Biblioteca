package service;

import DAO.EmprestimoDAO;
import database.ConnectionFactory;
import model.Emprestimo;
import model.EmprestimoNomeado;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {

    private final ConnectionFactory connection;

    public EmprestimoService() {
        this.connection = new ConnectionFactory();
    }

    public void criarEmprestimo(Long clienteId, Long livroId, LocalDate date){
        Connection conn = connection.restoreConnection();
        new EmprestimoDAO(conn).inserir(new Emprestimo(null, clienteId, livroId, date));
    }

    public List<EmprestimoNomeado> listarEmprestimos(){
        Connection conn = connection.restoreConnection();
        List<EmprestimoNomeado> emprestimos = new EmprestimoDAO(conn).listar();
        return emprestimos;
    }
}
