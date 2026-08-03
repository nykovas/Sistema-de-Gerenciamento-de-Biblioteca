package service;

import DAO.ClienteDAO;
import database.ConnectionFactory;
import model.Cliente;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class ClienteService {

    private final ConnectionFactory connection;

    public ClienteService(){
        this.connection = new ConnectionFactory();
    }

    public void criarCliente(Cliente cliente){
        Connection conn = connection.restoreConnection();
        new ClienteDAO(conn).inserir(cliente);
    }

    public List<Cliente> listarCliente(){
        Connection conn = connection.restoreConnection();
        return new ClienteDAO(conn).listar();
    }

    public List<Cliente> buscarPorId(Integer id){
        Connection conn = connection.restoreConnection();
        return new ClienteDAO(conn).buscarPorId(id);
    }

    public void atualizarCliente(Cliente cliente){
        Connection conn = connection.restoreConnection();
        new ClienteDAO(conn).atualizar(cliente);
    }

    public void desativarCliente(Integer id){
        Connection conn = connection.restoreConnection();
        new ClienteDAO(conn).desligarCliente(id);
    }
}
