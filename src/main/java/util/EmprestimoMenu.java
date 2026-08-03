package util;

import model.Cliente;
import model.EmprestimoNomeado;
import service.EmprestimoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmprestimoMenu {

    private static Scanner teclado = new Scanner(System.in);
    private static EmprestimoService service = new EmprestimoService();

    public static void emprestimoMenu(){
        System.out.println("""
                ====================
                 Menu de Empréstimo
                ====================
                1. Realizar empréstimo (Ok)
                2. Listar empréstimos (Ok)
                """);
        System.out.print("Seleciona a opção desejada: ");

        var opcao = teclado.nextLine();

        switch (opcao){
            case "1":
                criarEmprestimo();
                break;
            case "2":
                listarEmprestimos();
                break;
        }
    }

    private static void criarEmprestimo(){
        System.out.print("Digite o id do cliente: ");
        Long idCliente = teclado.nextLong();

        System.out.print("Digite o id do livro: ");
        Long idLivro = teclado.nextLong();

        service.criarEmprestimo(idCliente, idLivro, LocalDate.now());
    }

    private static void listarEmprestimos(){
        List<EmprestimoNomeado> emprestimos = service.listarEmprestimos();
        for (EmprestimoNomeado emprestimo : emprestimos) {
            System.out.printf("""
                   ---------------------------------------
                   ID: %d
                   Cliente: %s
                   Livro: %s
                   Data: %s
                   """,
                   emprestimo.id(),
                   emprestimo.nomeCliente(),
                   emprestimo.nomeLivro(),
                   emprestimo.date());
        }
    }
}
