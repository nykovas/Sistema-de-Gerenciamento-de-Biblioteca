package model;

public record Cliente(Long id,
                      String nome,
                      String email,
                      String telefone,
                      Boolean estaAtivo) {

    public Cliente(String nome, String email, String telefone, Boolean estaAtivo) {
        this(null, nome, email, telefone, estaAtivo);
    }
}
