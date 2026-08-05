package br.com.nyk.sgb.service;

import br.com.nyk.sgb.dao.LivroDAO;
import br.com.nyk.sgb.database.EntityFactory;
import br.com.nyk.sgb.exception.ValidacaoException;
import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Livro;

import java.util.List;
import java.util.Objects;

public class LivroService {
    private final EntityFactory entity;

    public LivroService(){
        this.entity = new EntityFactory();
    }

    public void cadastrarLivro(Livro livro){
        EntityManager em = entity.entityManager();
        validarLivro(livro);
        new LivroDAO(em).inserir(livro);
    }

    public List<Livro> listarLivros(){
        EntityManager em = entity.entityManager();
        return new LivroDAO(em).listar();
    }

    public List<Livro> buscarPorTitulo(String livro){
        EntityManager em = entity.entityManager();
        List<Livro> livros = new LivroDAO(em).buscaPorTitulo(livro);
        if (livros.isEmpty()){
            System.out.println("Nenhum livro encontrado.");
        }
        return livros;
    }

    public List<Livro> buscarPorGenero(String genero){
        EntityManager em = entity.entityManager();
        List<Livro> livros = new LivroDAO(em).buscaPorGenero(genero);
        if (livros.isEmpty()){
            System.out.println("Nenhum livro com o gênero: " + genero + " encontrado.");
        }
        return livros;
    }

    public void removerLivro(Long id){
        EntityManager em = entity.entityManager();
        verificarExistencia(id);
        new LivroDAO(em).removerLivro(id);
    }

    public void atualizarLivro(Livro livro){
        EntityManager em = entity.entityManager();
        verificarExistencia(livro.id());
        validarLivro(livro);
        new LivroDAO(em).atualizarLivro(livro);
    }

    private void validarLivro(Livro livro){
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
        EntityManager em = entity.entityManager();
        LivroDAO livroDAO = new LivroDAO(em);
        if (!Objects.equals(id, livroDAO.verificarExistencia(id))){
            throw new ValidacaoException("Nenhum livro com o id: " + id + "encontrado.");
        }
    }
}
