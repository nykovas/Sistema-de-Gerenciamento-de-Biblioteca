package util;

public class ClienteMenu {
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
    }
}
