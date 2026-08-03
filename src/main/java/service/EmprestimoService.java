package service;

import DAO.EmprestimoDAO;
import database.ConnectionFactory;
import model.Emprestimo;

import java.sql.Connection;
import java.time.LocalDate;

public class EmprestimoService {

    private final ConnectionFactory connection;

    public EmprestimoService() {
        this.connection = new ConnectionFactory();
    }

    public void criarEmprestimo(Long clienteId, Long livroId, LocalDate date){
        Connection conn = connection.restoreConnection();
        new EmprestimoDAO(conn).inserir(new Emprestimo(clienteId, livroId, date));
    }
}
