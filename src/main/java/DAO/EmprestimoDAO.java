package DAO;

import model.Cliente;
import model.Emprestimo;
import model.Livro;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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
}
