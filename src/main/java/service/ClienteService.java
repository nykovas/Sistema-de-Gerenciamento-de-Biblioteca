package service;

import DAO.ClienteDAO;
import database.ConnectionFactory;
import model.Cliente;

import java.sql.Connection;

public class ClienteService {

    private final ConnectionFactory connection;

    public ClienteService(){
        this.connection = new ConnectionFactory();
    }

    public void criarCliente(Cliente cliente){
        Connection conn = connection.restoreConnection();
        new ClienteDAO(conn).inserir(cliente);
    }
}
