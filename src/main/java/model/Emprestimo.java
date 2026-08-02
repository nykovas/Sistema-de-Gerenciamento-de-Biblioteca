package model;

import java.time.LocalDate;

public record Emprestimo(Integer id_cliente,
                         Integer id_livro,
                         LocalDate data_emprestimo) {
}
