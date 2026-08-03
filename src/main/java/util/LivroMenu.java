package util;

public class LivroMenu {
    public static void livroMenu(){
        System.out.println("""
                ===============
                 Menu de Livro
                ===============
                1. Cadastrar livro (Manutenção)
                2. Listar livros (Manutenção)
                3. Buscar livro por título (Manutenção)
                4. Remover livro (Manutenção)
                """);
        System.out.print("Selecione a opção desejada: ");
    }
}
