package br.com.nyk.sgb.model;

public record Livro(Long id,
                    String titulo,
                    String autor,
                    String genero,
                    Integer anoPublicacao,
                    Integer quantidade) {
    public Livro(String titulo, String autor, String genero, Integer anoPublicacao, Integer quantidade){
        this(null, titulo, autor, genero, anoPublicacao, quantidade);
    }
}
