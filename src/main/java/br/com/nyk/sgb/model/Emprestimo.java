package br.com.nyk.sgb.model;

import java.time.LocalDate;

public record Emprestimo(Long id,
                         Long id_cliente,
                         Long id_livro,
                         LocalDate data_emprestimo) {
}
