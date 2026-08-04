package DAO;

import model.Emprestimo;
import model.EmprestimoNomeado;
import model.EmprestimoTopCinco;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    private Connection conn;

    public EmprestimoDAO(Connection connection) {
        this.conn = connection;
    }

    public void inserir(Emprestimo emprestimo){
        String sql = "INSERT INTO emprestimo (id_cliente, id_livro, data_emprestimo) VALUES (?, ?, ?)";
        PreparedStatement ps;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setLong(1, emprestimo.id_cliente());
            ps.setLong(2, emprestimo.id_livro());
            ps.setDate(3, Date.valueOf(emprestimo.data_emprestimo()));

            ps.execute();

            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public List<EmprestimoNomeado> listar(){
        String sql = """
                SELECT e.id,
                       c.nome,
                       l.titulo,
                       e.data_emprestimo
                FROM emprestimo e
                INNER JOIN cliente c ON c.id = e.id_cliente
                INNER JOIN livro l ON l.id = e.id_livro
                ORDER BY id
                """;
        PreparedStatement ps;
        ResultSet rs;
        List<EmprestimoNomeado> emprestimos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Long id = rs.getLong(1);
                String nome = rs.getString(2);
                String livro = rs.getString(3);
                LocalDate date = rs.getDate(4).toLocalDate();

                emprestimos.add(new EmprestimoNomeado(id, nome, livro, date.format(formatter)));
            }

            ps.close();
            rs.close();
            conn.rollback();
            conn.close();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return emprestimos;
    }

    public List<EmprestimoTopCinco> listarCincoMaisEmprestados(){
        List<EmprestimoTopCinco> topCinco = new ArrayList<>();
        String sql = """
                SELECT l.titulo,
                       count(*)
                FROM emprestimo e
                INNER JOIN livro l ON l.id = e.id_livro
                GROUP BY l.titulo
                ORDER BY count(*) DESC
                LIMIT 5
                """;
        PreparedStatement ps;
        ResultSet rs;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                String titulo = rs.getString(1);
                Integer contagem = rs.getInt(2);

                topCinco.add(new EmprestimoTopCinco(titulo, contagem));
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return topCinco;
    }
}
