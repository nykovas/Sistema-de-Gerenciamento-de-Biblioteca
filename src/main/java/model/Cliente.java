package model;

public record Cliente(Integer id,
                      String nome,
                      String email,
                      String telefone,
                      Boolean esta_ativo) {

    public Cliente(String nome, String email, String telefone, Boolean esta_ativo) {
        this(null, nome, email, telefone, esta_ativo);
    }
}
