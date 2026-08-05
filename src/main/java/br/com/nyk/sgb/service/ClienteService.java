package br.com.nyk.sgb.service;

import br.com.nyk.sgb.dao.ClienteDAO;
import br.com.nyk.sgb.database.EntityFactory;
import br.com.nyk.sgb.exception.ValidacaoException;
import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Cliente;

import java.util.List;
import java.util.Optional;

public class ClienteService {

    private final EntityFactory entity;

    public ClienteService(){
        this.entity = new EntityFactory();
    }

    public void criarCliente(Cliente cliente){
        try(EntityManager em = entity.entityManager()){
            new ClienteDAO(em).inserir(cliente);
        }
    }

    public List<Cliente> listarCliente(){
        try(EntityManager em = entity.entityManager()){
            return new ClienteDAO(em).listar();
        }
    }

    public Optional<Cliente> buscarPorNome(String nome){
        try(EntityManager em = entity.entityManager()){
            return new ClienteDAO(em).buscarPorNome(nome);
        }
    }

    public Optional<Cliente> buscarPorId(Long id){
        try(EntityManager em = entity.entityManager()){
            return new ClienteDAO(em).buscarPorId(id);
        }
    }

    public void atualizarCliente(Cliente cliente){
        try(EntityManager em = entity.entityManager()){
            validarCliente(cliente);
            new ClienteDAO(em).atualizar(cliente);
        }
    }

    public void desativarCliente(Long id){
        try(EntityManager em = entity.entityManager()){
            new ClienteDAO(em).desativar(id);
        }
    }

    private void validarCliente(Cliente cliente){
        if (cliente.getNome() == null || cliente.getNome().isBlank()){
            throw new ValidacaoException("O nome do cliente não pode estar vazio.");
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank()){
            throw new ValidacaoException("O e-mail do cliente não pode estar vazio.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()){
            throw new ValidacaoException("O telefone do cliente não pode estar vazio.");
        }
    }

}
