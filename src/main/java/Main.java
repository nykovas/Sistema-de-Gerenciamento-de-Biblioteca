import model.Cliente;
import service.ClienteService;

import java.util.Scanner;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    private static ClienteService service = new ClienteService();
    static void main(String[] args) {

        menu();

        var opcao = teclado.nextLine();

        switch (opcao){
            case "2":
                criarCliente();
        }

    }

    private static void menu(){
        System.out.println("""
               ===== BIBLIOTECA =====
              \s
               1 - Cadastrar livro         │ (Manutenção)
               2 - Cadastrar cliente       │ (Ok)
               3 - Realizar empréstimo     │ (Manutenção)
               4 - Listar livros           │ (Manutenção)
               5 - Listar clientes         │ (Manutenção)
               6 - Listar empréstimos      │ (Manutenção)
               7 - Buscar livro por título │ (Manutenção)
               8 - Buscar cliente por nome │ (Manutenção)
               9 - Remover livro           │ (Manutenção)
              10 - Remover cliente         │ (Manutenção)
               0 - Sair                    │ (Manutenção)
              \s""");
        System.out.print("Selecione a opção desejada: ");

    }

    private static void criarCliente(){
        System.out.println("""
                ===================
                Cadastro de Cliente
                ===================
                """);
        System.out.print("Digite o seu nome: ");
        String nome = teclado.nextLine();

        System.out.print("Digite seu email: ");
        String email = teclado.nextLine();

        System.out.print("Digite seu telefone: ");
        String telefone = teclado.nextLine();

        service.criarCliente(new Cliente(nome, email, telefone, true));
    }
}
