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
        String sql = "INSERT INTO livro (titulo, autor, genero, ano_publicacao, estoque) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps;

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
        String sql = "SELECT * FROM livro WHERE estoque > 0 ORDER BY id";
        PreparedStatement ps;
        ResultSet rs;
        List<Livro> livros = new ArrayList<>();

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

    public List<Livro> buscaPorTitulo(String livro){
        String sql = "SELECT * FROM livro WHERE titulo ILIKE ?";
        PreparedStatement ps;
        ResultSet rs;
        List<Livro> livros = new ArrayList<>();

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + livro + "%");
            rs = ps.executeQuery();

            while (rs.next()){
                Long id = rs.getLong(1);
                String titulo = rs.getString(2);
                String autor = rs.getString(3);
                String genero = rs.getString(4);
                Integer anoPublicacao = rs.getInt(5);
                Integer estoque = rs.getInt(6);

                livros.add(new Livro(id, titulo, autor, genero, anoPublicacao, estoque));
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

    public void removerLivro(Long id){
        String sql = "UPDATE livro SET estoque = 0 WHERE id = ?";
        PreparedStatement ps;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setLong(1, id);
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

    public void atualizarLivro(Livro livro){
        String sql = "UPDATE livro SET titulo = ?, autor = ?, genero = ?, ano_publicacao = ?, estoque = ? WHERE id = ?";
        PreparedStatement ps;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);

            ps.setString(1, livro.titulo());
            ps.setString(2, livro.autor());
            ps.setString(3, livro.genero());
            ps.setInt(4, livro.anoPublicacao());
            ps.setInt(5, livro.quantidade());
            ps.setLong(6, livro.id());

            ps.executeUpdate();

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
