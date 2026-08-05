package br.com.nyk.sgb;

import br.com.nyk.sgb.database.EntityFactory;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

import static br.com.nyk.sgb.view.ClienteMenu.*;
import static br.com.nyk.sgb.view.LivroMenu.livroMenu;
import static br.com.nyk.sgb.view.Menu.*;

public class Main {
    private static final Scanner teclado = new Scanner(System.in);
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
                    System.out.println("Em manutenção.");
                    break;
                case 0:
                    EntityFactory.fecharFactory();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
