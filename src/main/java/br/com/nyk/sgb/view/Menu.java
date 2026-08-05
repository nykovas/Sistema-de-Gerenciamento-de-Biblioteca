package br.com.nyk.sgb.view;

public class Menu {
    public static void menu(){
        System.out.println("""
                ===== BIBLIOTECA =====
                1. Área de Clientes (Ok)
                2. Área de Livros (Manutenção 0/6)
                3. Área de Emprestimos (Manutenção 0/4)
                0. Sair
                """);
        System.out.print("Selecione a opção desejada: ");
    }
}
