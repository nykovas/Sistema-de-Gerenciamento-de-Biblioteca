import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        menu();

        var opcao = teclado.nextLine();

    }

    private static void menu(){
        System.out.println("""
               ===== BIBLIOTECA =====
              \s
               1 - Cadastrar livro         │ (Manutenção)
               2 - Cadastrar cliente       │ (Manutenção)
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
}
