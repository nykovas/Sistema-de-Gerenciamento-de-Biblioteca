package br.com.nyk.sgb.view;

import br.com.nyk.sgb.model.Cliente;
import br.com.nyk.sgb.service.ClienteService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClienteMenu {
    private static final Scanner teclado = new Scanner(System.in);
    private static final ClienteService service = new ClienteService();
    public static void clienteMenu(){
        int opcao = -1;
        while (opcao != 0){
            System.out.println("""
                =================
                 Menu do Cliente
                =================
                1. Cadastrar cliente
                2. Listar clientes
                3. Buscar cliente por nome
                4. Buscar cliente por id
                5. Atualizar cliente
                6. Desativar cliente
                0. Sair
                """);
            System.out.print("Digite a opção desejada: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao){
                case 1:
                    criarCliente();
                    break;
                case 2:
                    listarClientes();
                    break;
                case 3:
                    buscarClientePorNome();
                    break;
                case 4:
                    buscarClientePorId();
                    break;
                case 5:
                    atualizarCliente();
                    break;
                case 6:
                    desativarCliente();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        }

    private static void criarCliente(){
        System.out.println("""
                =====================
                 Cadastro de Cliente
                =====================
                """);
        System.out.print("Digite o seu nome: ");
        String nome = teclado.nextLine();

        System.out.print("Digite seu email: ");
        String email = teclado.nextLine();

        System.out.print("Digite seu telefone: ");
        String telefone = teclado.nextLine();

        service.criarCliente(new Cliente(null, nome, email, telefone, true));
    }

    private static void listarClientes(){
        List<Cliente> clientes = service.listarCliente();

        if (clientes.isEmpty()){
            System.out.println("A lista de clientes está vazia.");
            System.out.println("Retornando...");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.printf("""
                    ---------------------------------------
                    ID: %d
                    Nome: %s
                    E-mail: %s
                    Telefone: %s
                    Ativo: %s
                    """,
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getTelefone(),
                    cliente.getEstaAtivo() ? "Sim" : "Não");
        }
    }

    private static void buscarClientePorNome() {
        System.out.print("Digite o nome de busca: ");
        String nome = teclado.nextLine();

        Optional<Cliente> clienteOptional = service.buscarPorNome(nome);

        if (clienteOptional.isEmpty()){
            System.out.println("Nenhum cliente encontrado");
            System.out.println("Retornando...");
            return;
        }

        Cliente cliente = clienteOptional.get();

            System.out.printf("""
                   ---------------------------------------
                   ID: %d
                   Nome: %s
                   E-mail: %s
                   Telefone: %s
                   Ativo: %s
                   """,
                   cliente.getId(),
                   cliente.getNome(),
                   cliente.getEmail(),
                   cliente.getTelefone(),
                   cliente.getEstaAtivo() ? "Sim" : "Não");
    }

    private static void buscarClientePorId(){
        System.out.print("Digite o id de busca: ");
        Long id = teclado.nextLong();

        Optional<Cliente> clienteOptional = service.buscarPorId(id);

        if (clienteOptional.isEmpty()){
            System.out.println("Nenhum cliente com o id: " + id + " encontrado");
            System.out.println("Retornando...");
            return;
        }

        Cliente cliente = clienteOptional.get();

        System.out.printf("""
                   ---------------------------------------
                   ID: %d
                   Nome: %s
                   E-mail: %s
                   Telefone: %s
                   Ativo: %s
                   """,
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getEstaAtivo() ? "Sim" : "Não");
    }

    private static void atualizarCliente(){
        System.out.println("""
                ========================
                 Atualização de Cliente
                ========================
                """);

        System.out.print("Selecione o id do cliente a ser alterado: ");
        Long id = teclado.nextLong();
        teclado.nextLine();

        System.out.print("Digite o nome novo do cliente: ");
        String nome = teclado.nextLine();

        System.out.print("Digite o email novo do cliente: ");
        String email = teclado.nextLine();

        System.out.print("Digite o telefone novo do cliente: ");
        String telefone = teclado.nextLine();

        service.atualizarCliente(new Cliente(id, nome, email, telefone, true));
    }

    private static void desativarCliente(){
        System.out.println("""
                ========================
                 Desativação de Cliente
                ========================
                """);
        System.out.print("Digite o id do cliente a ser desativado: ");
        Long id = teclado.nextLong();

        service.desativarCliente(id);
    }
}
