import java.util.Scanner;

import static util.ClienteMenu.*;
import static util.EmprestimoMenu.*;
import static util.LivroMenu.*;
import static util.Menu.*;

public class Main {
    private static Scanner teclado = new Scanner(System.in);
    static void main(String[] args) {

        menu();
        var opcao = teclado.nextLine();

        switch (opcao){
            case "1":
                clienteMenu();
                break;
            case "2":
                livroMenu();
                break;
            case "3":
                emprestimoMenu();
                break;
        }
    }
}
