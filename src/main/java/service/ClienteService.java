package service;

import DAO.ClienteDAO;
import database.ConnectionFactory;
import exception.ValidacaoException;
import model.Cliente;

import java.sql.Connection;
import java.util.List;

public class ClienteService {

    private final ConnectionFactory connection;

    public ClienteService(){
        this.connection = new ConnectionFactory();
    }

    public void criarCliente(Cliente cliente){
        Connection conn = connection.restoreConnection();
        validarCliente(cliente);
        new ClienteDAO(conn).inserir(cliente);
    }

    public List<Cliente> listarCliente(){
        Connection conn = connection.restoreConnection();
        return new ClienteDAO(conn).listar();
    }

    public List<Cliente> buscarPorId(Long id){
        Connection conn = connection.restoreConnection();
        verificarExistencia(id);
        return new ClienteDAO(conn).buscarPorId(id);
    }

    public void atualizarCliente(Cliente cliente){
        Connection conn = connection.restoreConnection();
        validarCliente(cliente);
        new ClienteDAO(conn).atualizar(cliente);
    }

    public void desativarCliente(Long id){
        Connection conn = connection.restoreConnection();
        verificarExistencia(id);
        new ClienteDAO(conn).desligarCliente(id);
    }

    private void validarCliente(Cliente cliente){
        if (cliente.nome() == null || cliente.nome().isBlank()){
            throw new ValidacaoException("O nome do cliente não pode estar vazio.");
        }

        if (cliente.email() == null || cliente.email().isBlank()){
            throw new ValidacaoException("O e-mail do cliente não pode estar vazio.");
        }

        if (cliente.telefone() == null || cliente.telefone().isBlank()){
            throw new ValidacaoException("O telefone do cliente não pode estar vazio.");
        }
    }

    private void verificarExistencia(Long id){
        Connection conn = connection.restoreConnection();
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        if (!id.equals(clienteDAO.verificarExistencia(id))){
            throw new ValidacaoException("Cliente não encontrado.");
        }
    }
}
