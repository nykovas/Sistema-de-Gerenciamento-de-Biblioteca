package model;

public record Cliente(String nome,
                      String email,
                      String telefone,
                      Boolean esta_ativo) {
}
