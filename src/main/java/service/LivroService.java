package service;

import DAO.LivroDAO;
import database.ConnectionFactory;
import exception.ValidacaoException;
import model.Livro;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;

public class LivroService {
    private final ConnectionFactory connection;

    public LivroService(){
        this.connection = new ConnectionFactory();
    }

    public void cadastrarLivro(Livro livro){
        Connection conn = connection.restoreConnection();
        validarLivro(livro, conn);
        new LivroDAO(conn).inserir(livro);
    }

    public List<Livro> listarLivros(){
        Connection conn = connection.restoreConnection();
        return new LivroDAO(conn).listar();
    }

    public List<Livro> buscarPorTitulo(String livro){
        Connection conn = connection.restoreConnection();
        List<Livro> livros = new LivroDAO(conn).buscaPorTitulo(livro);
        if (livros.isEmpty()){
            System.out.println("Nenhum livro encontrado.");
        }
        return livros;
    }

    public List<Livro> buscarPorGenero(String genero){
        Connection conn = connection.restoreConnection();
        List<Livro> livros = new LivroDAO(conn).buscaPorGenero(genero);
        if (livros.isEmpty()){
            System.out.println("Nenhum livro com o gênero: " + genero + " encontrado.");
        }
        return livros;
    }

    public void removerLivro(Long id){
        Connection conn = connection.restoreConnection();
        verificarExistencia(id);
        new LivroDAO(conn).removerLivro(id);
    }

    public void atualizarLivro(Livro livro){
        Connection conn = connection.restoreConnection();
        new LivroDAO(conn).atualizarLivro(livro);
    }

    private void validarLivro(Livro livro, Connection conn){
        if (livro.titulo() == null || livro.titulo().isBlank()){
            throw new ValidacaoException("O título não pode estar vazio.");
        }
        if (livro.autor() == null || livro.autor().isBlank()){
            throw new ValidacaoException("O autor não pode estar vazio.");
        }
        if (livro.genero() == null || livro.genero().isBlank()){
            throw new ValidacaoException("O gênero não pode estar vazio.");
        }
        if (livro.anoPublicacao() == null){
            throw new ValidacaoException("O ano não pode estar vazio.");
        }
    }
    private void verificarExistencia(Long id){
        Connection conn = connection.restoreConnection();
        LivroDAO livroDAO = new LivroDAO(conn);
        if (!Objects.equals(id, livroDAO.verificarExistencia(id))){
            throw new ValidacaoException("Nenhum livro encontrado.");
        }
    }
}
