package br.com.nyk.sgb.dao;

import jakarta.persistence.EntityManager;
import br.com.nyk.sgb.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private final EntityManager em;

    public LivroDAO(EntityManager entityManager){
        this.em = entityManager;
    }

    public void inserir(Livro livro){

    }

    public List<Livro> listar(){
        List<Livro> livros = new ArrayList<>();
        return livros;
    }

    public List<Livro> buscaPorTitulo(String livro){
        List<Livro> livros = new ArrayList<>();
        return livros;
    }

    public List<Livro> buscaPorGenero(String generoBusca){
        List<Livro> livros = new ArrayList<>();
        return livros;
    }

    public void removerLivro(Long id) {
    }

    public void atualizarLivro(Livro livro) {
    }

    public Long verificarExistencia(Long id){
        return 1L;
    }
}
