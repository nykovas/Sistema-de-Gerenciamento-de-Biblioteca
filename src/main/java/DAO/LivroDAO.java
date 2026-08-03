package DAO;

import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivroDAO {
    private final Connection conn;

    public LivroDAO(Connection connection){
        this.conn = connection;
    }

    public void inserir(Livro livro){
        PreparedStatement ps;
        String sql = "INSERT INTO livro (titulo, autor, genero, ano_publicacao, estoque) VALUES (?, ?, ?, ?, ?)";

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setString(1, livro.titulo());
            ps.setString(2, livro.autor());
            ps.setString(3, livro.genero());
            ps.setInt(4, livro.anoPublicacao());
            ps.setInt(5, livro.quantidade());

            ps.execute();

            ps.close();
            conn.commit();
            conn.close();
        } catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
