package service;

import DAO.LivroDAO;
import database.ConnectionFactory;
import model.Livro;

import java.sql.Connection;
import java.util.List;

public class LivroService {
    private final ConnectionFactory connection;

    public LivroService(){
        this.connection = new ConnectionFactory();
    }

    public void cadastrarLivro(Livro livro){
        Connection conn = connection.restoreConnection();
        new LivroDAO(conn).inserir(livro);
    }

    public List<Livro> listarLivros(){
        Connection conn = connection.restoreConnection();
        List<Livro> livros = new LivroDAO(conn).listar();
        return livros;
    }

    public List<Livro> buscarPorTitulo(String livro){
        Connection conn = connection.restoreConnection();
        List<Livro> livros = new LivroDAO(conn).buscaPorTitulo(livro);
        return livros;
    }
}
