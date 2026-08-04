package service;

import DAO.ClienteDAO;
import DAO.EmprestimoDAO;
import DAO.LivroDAO;
import database.ConnectionFactory;
import exception.ValidacaoException;
import model.Emprestimo;
import model.EmprestimoNomeado;
import model.EmprestimoTopCinco;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class EmprestimoService {

    private final ConnectionFactory connection;

    public EmprestimoService() {
        this.connection = new ConnectionFactory();
    }

    public void criarEmprestimo(Long clienteId, Long livroId, LocalDate date){
        Connection conn = connection.restoreConnection();
        validarEmprestimo(new Emprestimo(null, clienteId, livroId, date));
        new EmprestimoDAO(conn).inserir(new Emprestimo(null, clienteId, livroId, date));
    }

    public List<EmprestimoNomeado> listarEmprestimos(){
        Connection conn = connection.restoreConnection();
        List<EmprestimoNomeado> emprestimos = new EmprestimoDAO(conn).listar();
        return emprestimos;
    }

    public List<EmprestimoTopCinco> listarTopCinco(){
        Connection conn = connection.restoreConnection();
        List<EmprestimoTopCinco> emprestimoTopCincos = new EmprestimoDAO(conn)
                .listarCincoMaisEmprestados();
        return emprestimoTopCincos;
    }

    private void validarEmprestimo(Emprestimo emprestimo){
        Connection conn = connection.restoreConnection();
        Connection conn2 = connection.restoreConnection();
        ClienteDAO cliente = new ClienteDAO(conn);
        LivroDAO livro = new LivroDAO(conn2);

        if (!Objects.equals(emprestimo.id_cliente(), cliente.verificarExistencia(emprestimo.id_cliente()))){
            throw new ValidacaoException("Cliente não encontrado.");
        }

        if (!Objects.equals(emprestimo.id_livro(), livro.verificarExistencia(emprestimo.id_livro()))){
            throw new ValidacaoException("Livro não encontrado.");
        }
    }
}
