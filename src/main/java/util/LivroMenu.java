package util;

import model.Livro;
import service.LivroService;

import java.util.Scanner;

public class LivroMenu {
    private static Scanner teclado = new Scanner(System.in);
    private static LivroService service = new LivroService();
    public static void livroMenu(){
        System.out.println("""
                ===============
                 Menu de Livro
                ===============
                1. Cadastrar livro (Ok)
                2. Listar livros (Manutenção)
                3. Buscar livro por título (Manutenção)
                4. Remover livro (Manutenção)
                """);
        System.out.print("Selecione a opção desejada: ");
        var opcao = teclado.nextLine();

        switch (opcao){
            case "1":
                cadastrarLivro();
                break;
            case "2":

        }
    }

    private static void cadastrarLivro(){
        System.out.println("""
                ===================
                 Cadastro de Livro
                ===================
                """);

        System.out.print("Digite o título do livro: ");
        String titulo = teclado.nextLine();

        System.out.print("Digite o nome do autor: ");
        String autor = teclado.nextLine();

        System.out.print("Digite o gênero: ");
        String genero = teclado.nextLine();

        System.out.print("Digite o ano de publicação: ");
        Integer anoPublicacao = teclado.nextInt();
        teclado.nextLine();

        System.out.print("Digite a quantidade inicial: ");
        Integer quantidade = teclado.nextInt();

        service.cadastrarLivro(new Livro(titulo, autor, genero, anoPublicacao, quantidade));
    }

}
