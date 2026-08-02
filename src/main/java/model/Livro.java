package model;

public record Livro(String titulo,
                    String autor,
                    String genero,
                    Integer ano_publicacao,
                    Integer quantidade) {
}
