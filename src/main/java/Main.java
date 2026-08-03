import model.Cliente;
import service.ClienteService;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    private static ClienteService service = new ClienteService();
    static void main(String[] args) {

        menu();

        var opcao = teclado.nextLine();

        switch (opcao){
            case "2":
                criarCliente();
                break;
            case "5":
                listarCliente();
                break;
            case "8":
                buscarClientePorId();
                break;
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
               5 - Listar clientes         │ (Ok)
               6 - Listar empréstimos      │ (Manutenção)
               7 - Buscar livro por título │ (Manutenção)
               8 - Buscar cliente por id   │ (Manutenção)
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

    private static void listarCliente(){
        List<Cliente> clientes = service.listarCliente();

        for (Cliente cliente : clientes) {
            System.out.printf("""
                    ---------------------------------------
                    ID: %d
                    Nome: %s
                    E-mail: %s
                    Telefone: %s
                    Ativo: %s
                    """,
                    cliente.id(),
                    cliente.nome(),
                    cliente.email(),
                    cliente.telefone(),
                    cliente.esta_ativo() ? "Sim" : "Não");
        }
    }

    private static void buscarClientePorId() {
        System.out.print("Digite o id de busca: ");
        Integer id = teclado.nextInt();

        List<Cliente> clienteBusca = service.buscarPorId(id);

        for (Cliente cliente : clienteBusca) {
            System.out.printf("""
                            ---------------------------------------
                            ID: %d
                            Nome: %s
                            E-mail: %s
                            Telefone: %s
                            Ativo: %s
                            """,
                    cliente.id(),
                    cliente.nome(),
                    cliente.email(),
                    cliente.telefone(),
                    cliente.esta_ativo() ? "Sim" : "Não");
        }
    }
}
