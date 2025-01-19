package teste;

import java.util.Scanner;

public class BibliotecaView {
    public BibliotecaView(Scanner scanner) {
    }

    public void exibirMenu() {
        System.out.println("\n--- Sistema de Gerenciamento de Biblioteca ---");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Cadastrar livro");
        System.out.println("3. Listar usuários");
        System.out.println("4. Listar livros");
        System.out.println("5. Alugar livro");
        System.out.println("6. Devolver livro");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
