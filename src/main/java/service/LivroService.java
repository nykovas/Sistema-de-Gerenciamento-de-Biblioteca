package service;

import DAO.LivroDAO;
import database.ConnectionFactory;
import model.Livro;

import java.sql.Connection;

public class LivroService {
    private final ConnectionFactory connection;

    public LivroService(){
        this.connection = new ConnectionFactory();
    }

    public void cadastrarLivro(Livro livro){
        Connection conn = connection.restoreConnection();
        new LivroDAO(conn).inserir(livro);
    }
}
