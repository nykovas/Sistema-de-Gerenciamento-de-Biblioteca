package util;

import model.Cliente;
import model.Livro;
import service.LivroService;

import java.util.List;
import java.util.Scanner;

public class LivroMenu {
    private static final Scanner teclado = new Scanner(System.in);
    private static final LivroService service = new LivroService();
    public static void livroMenu(){
        int opcao = -1;
        while (opcao != 0){
            System.out.println("""
                ===============
                 Menu de Livro
                ===============
                1. Cadastrar livro
                2. Listar livros
                3. Buscar livro por título
                4. Buscar livro por gênero
                5. Atualizar livro
                6. Remover livro
                0. Sair
                """);
            System.out.print("Selecione a opção desejada: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao){
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    buscarLivroPorTitulo();
                    break;
                case 4:
                    buscarLivroPorGenero();
                    break;
                case 5:
                    atualizarLivro();
                    break;
                case 6:
                    removerLivro();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
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

    private static void listarLivros(){
        List<Livro> livros = service.listarLivros();

        for (Livro livro : livros) {
            System.out.printf("""
                    ---------------------------------------
                    ID: %d
                    Título: %s
                    Autor: %s
                    Gênero: %s
                    Ano de Publicação: %s
                    Estoque Disponível: %s
                    """,
                    livro.id(),
                    livro.titulo(),
                    livro.autor(),
                    livro.genero(),
                    livro.anoPublicacao(),
                    livro.quantidade());
        }
    }

    private static void buscarLivroPorTitulo(){
        System.out.print("Digite o nome do livro que deseja buscar: ");
        String titulo = teclado.nextLine();

        List<Livro> livros = service.buscarPorTitulo(titulo);

        for (Livro livro : livros) {
            System.out.printf("""
                    ---------------------------------------
                    ID: %d
                    Título: %s
                    Autor: %s
                    Gênero: %s
                    Ano de Publicação: %s
                    Estoque Disponível: %s
                    """,
                    livro.id(),
                    livro.titulo(),
                    livro.autor(),
                    livro.genero(),
                    livro.anoPublicacao(),
                    livro.quantidade());
        }
    }

    private static void buscarLivroPorGenero(){
        System.out.print("Digite o gênero do livro que deseja buscar: ");
        String genero = teclado.nextLine();

        List<Livro> livros = service.buscarPorGenero(genero);

        for (Livro livro : livros) {
            System.out.printf("""
                    ---------------------------------------
                    ID: %d
                    Título: %s
                    Autor: %s
                    Gênero: %s
                    Ano de Publicação: %s
                    Estoque Disponível: %s
                    """,
                    livro.id(),
                    livro.titulo(),
                    livro.autor(),
                    livro.genero(),
                    livro.anoPublicacao(),
                    livro.quantidade());
        }
    }
    private static void removerLivro(){
        System.out.print("Digite o id do livro que deseja apagar: ");
        Long id = teclado.nextLong();
        service.removerLivro(id);
    }

    private static void atualizarLivro(){
        System.out.print("Digite o id do livro que deseja alterar: ");
        Long id = teclado.nextLong();
        teclado.nextLine();

        System.out.print("Digite o título do livro: ");
        String titulo = teclado.nextLine();

        System.out.print("Digite o autor do livro: ");
        String autor = teclado.nextLine();

        System.out.print("Digite o gênero do livro: ");
        String genero = teclado.nextLine();

        System.out.print("Digite o ano de publicação do livro: ");
        Integer anoPublicacao = teclado.nextInt();

        System.out.print("Digite o estoque inicial do livro: ");
        Integer estoque = teclado.nextInt();

        service.atualizarLivro(new Livro(id, titulo, autor, genero, anoPublicacao, estoque));
    }
}
