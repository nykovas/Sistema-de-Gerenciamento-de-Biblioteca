package DAO;

import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Livro> listar(){
        PreparedStatement ps;
        ResultSet rs;
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE estoque > 0";

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Long id = rs.getLong(1);
                String titulo = rs.getString(2);
                String autor = rs.getString(3);
                String genero = rs.getString(4);
                Integer anoPublicacao = rs.getInt(5);
                Integer quantidade = rs.getInt(6);

                livros.add(new Livro(id, titulo, autor, genero, anoPublicacao, quantidade));
            }

            rs.close();
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
        return livros;
    }
}
