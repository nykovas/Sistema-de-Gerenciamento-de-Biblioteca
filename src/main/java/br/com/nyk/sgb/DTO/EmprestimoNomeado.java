package br.com.nyk.sgb.DTO;

import java.time.LocalDate;

public record EmprestimoNomeado(Long id,
                                String nomeCliente,
                                String nomeLivro,
                                LocalDate date) {
}
