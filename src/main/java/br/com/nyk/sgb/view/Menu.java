package br.com.nyk.sgb.view;

public class Menu {
    public static void menu(){
        System.out.println("""
                ===== BIBLIOTECA =====
                1. Área de Clientes (Ok)
                2. Área de Livros (Ok)
                3. Área de Emprestimos (Ok)
                0. Sair
                """);
        System.out.print("Selecione a opção desejada: ");
    }
}
