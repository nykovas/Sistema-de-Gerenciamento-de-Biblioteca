import java.util.Scanner;

import static util.ClienteMenu.*;
import static util.EmprestimoMenu.*;
import static util.LivroMenu.*;
import static util.Menu.*;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            menu();
            opcao = teclado.nextInt();

            switch (opcao){
                case 1:
                    clienteMenu();
                    break;
                case 2:
                    livroMenu();
                    break;
                case 3:
                    emprestimoMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
