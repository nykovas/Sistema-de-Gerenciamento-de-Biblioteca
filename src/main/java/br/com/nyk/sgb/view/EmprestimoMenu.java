package br.com.nyk.sgb.view;

import br.com.nyk.sgb.model.Emprestimo;
import br.com.nyk.sgb.DTO.EmprestimoCliente;
import br.com.nyk.sgb.DTO.EmprestimoNomeado;
import br.com.nyk.sgb.DTO.EmprestimoTopCinco;
import br.com.nyk.sgb.service.EmprestimoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmprestimoMenu {

    private static final Scanner teclado = new Scanner(System.in);
    private static final EmprestimoService service = new EmprestimoService();

    public static void emprestimoMenu(){
        var opcao = -1;
        while (opcao != 0){
            System.out.println("""
                ====================
                 Menu de Empréstimo
                ====================
                1. Realizar empréstimo
                2. Listar empréstimos
                3. Listar top cinco mais emprestados
                4. Listar quantidade de emprestimos por cliente
                0. Sair
                """);
            System.out.print("Seleciona a opção desejada: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("Em manutenção.");
                    break;
                case 2:
                    System.out.println("Em manutenção.");
                    break;
                case 3:
                    System.out.println("Em manutenção.");
                    break;
                case 4:
                    System.out.println("Em manutenção.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void criarEmprestimo(){
        System.out.print("Digite o id do cliente: ");
        Long idCliente = teclado.nextLong();

        System.out.print("Digite o id do livro: ");
        Long idLivro = teclado.nextLong();

        service.criarEmprestimo(new Emprestimo(null, idCliente, idLivro, LocalDate.now()));
    }

    private static void listarEmprestimos(){
        List<EmprestimoNomeado> emprestimos = service.listarEmprestimos();
        for (EmprestimoNomeado emprestimo : emprestimos) {
            System.out.printf("""
                   ---------------------------------------
                   ID: %d
                   Cliente: %s
                   Livro: %s
                   Data do empréstimo: %s
                   """,
                   emprestimo.id(),
                   emprestimo.nomeCliente(),
                   emprestimo.nomeLivro(),
                   emprestimo.date());
        }
    }

    private static void listarTopCinco(){
        List<EmprestimoTopCinco> emprestimoTopCincos = service.listarTopCinco();
        for (EmprestimoTopCinco emprestimo : emprestimoTopCincos) {
            System.out.printf("""
                   ---------------------------------------
                   Livro: %s
                   Quantidade emprestados: %s
                   """,
                    emprestimo.titulo(),
                    emprestimo.contagem());
        }
    }

    private static void listarQuantidadeDeEmprestimoPorCliente(){
        List<EmprestimoCliente> emprestimoClientes = service.listarQuantidadeDeEmprestimosPorCliente();
        for (EmprestimoCliente emprestimo : emprestimoClientes) {
            System.out.printf("""
                   ---------------------------------------
                   Cliente: %s
                   Quantidade de emprestimos: %s
                   """,
                    emprestimo.nome(),
                    emprestimo.quantidade());
        }
    }
}
