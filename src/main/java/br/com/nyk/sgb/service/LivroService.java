package br.com.nyk.sgb.service;

import br.com.nyk.sgb.dao.LivroDAO;
import br.com.nyk.sgb.database.EntityFactory;
import br.com.nyk.sgb.exception.ValidacaoException;
import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Livro;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LivroService {
    private final EntityFactory entity;

    public LivroService(){
        this.entity = new EntityFactory();
    }

    public void cadastrarLivro(Livro livro){
        try(EntityManager em = entity.entityManager()){
            new LivroDAO(em).inserir(livro);
        }
    }

    public List<Livro> listarLivros(){
        try(EntityManager em = entity.entityManager()){
            return new LivroDAO(em).listar();
        }
    }

    public List<Livro> buscarPorTitulo(String livro){
        try(EntityManager em = entity.entityManager()){
            return new LivroDAO(em).buscaPorTitulo(livro);
        }
    }

    public List<Livro> buscarPorGenero(String genero){
        try(EntityManager em = entity.entityManager()){
            return new LivroDAO(em).buscaPorGenero(genero);
        }
    }

    public void removerLivro(Long id){
        try(EntityManager em = entity.entityManager()){
            new LivroDAO(em).removerLivro(id);
        }
    }

    public void atualizarLivro(Livro livro){
        try (EntityManager em = entity.entityManager()){
            new LivroDAO(em).atualizarLivro(livro);
        }
    }

    private void validarLivro(Livro livro){
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()){
            throw new ValidacaoException("O título não pode estar vazio.");
        }
        if (livro.getAutor() == null || livro.getAutor().isBlank()){
            throw new ValidacaoException("O autor não pode estar vazio.");
        }
        if (livro.getGenero() == null || livro.getGenero().isBlank()){
            throw new ValidacaoException("O gênero não pode estar vazio.");
        }
        if (livro.getAnoPublicacao() == null){
            throw new ValidacaoException("O ano não pode estar vazio.");
        }
    }

}
