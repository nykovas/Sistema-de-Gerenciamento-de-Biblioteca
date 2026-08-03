package util;

import model.Cliente;
import service.ClienteService;

import java.util.List;
import java.util.Scanner;

public class ClienteMenu {
    private static Scanner teclado = new Scanner(System.in);
    private static ClienteService service = new ClienteService();
    public static void clienteMenu(){
        System.out.println("""
                =================
                 Menu do Cliente
                =================
                1. Cadastrar cliente
                2. Listar clientes
                3. Buscar cliente por nome
                4. Atualizar cliente
                5. Desativar cliente
                """);
        System.out.print("Digite a opção desejada: ");
        var opcao = teclado.nextLine();

        switch (opcao){
            case "1":
                criarCliente();
                break;
            case "2":
                listarCliente();
                break;
            case "3":
                buscarClientePorId();
                break;
            case "4":
                atualizarCliente();
                break;
            case "5":
                desativarCliente();
                break;
        }
    }

    public static void criarCliente(){
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

    public static void listarCliente(){
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
                    cliente.estaAtivo() ? "Sim" : "Não");
        }
    }

    public static void buscarClientePorId() {
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
                    cliente.estaAtivo() ? "Sim" : "Não");
        }
    }

    public static void atualizarCliente(){
        System.out.println("""
                ========================
                 Atualização de Cliente
                ========================
                """);

        System.out.print("Selecione o id do cliente a ser alterado: ");
        Integer id = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Digite o nome novo do cliente: ");
        String nome = teclado.nextLine();

        System.out.print("Digite o email novo do cliente: ");
        String email = teclado.nextLine();

        System.out.print("Digite o telefone novo do cliente: ");
        String telefone = teclado.nextLine();

        service.atualizarCliente(new Cliente(id, nome, email, telefone, true));
    }

    public static void desativarCliente(){
        System.out.println("""
                ========================
                 Desativação de Cliente
                ========================
                """);
        System.out.print("Digite o id do cliente a ser desativado: ");
        Integer id = teclado.nextInt();

        service.desativarCliente(id);
    }
}
