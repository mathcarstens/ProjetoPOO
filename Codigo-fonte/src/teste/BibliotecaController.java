package teste;

import java.util.Scanner;

public class BibliotecaController {
    
    private LivroController livroController;
    private UsuarioController usuarioController;
    private EmprestimoController emprestimoController;

    public BibliotecaController(LivroController livroController, UsuarioController usuarioController, EmprestimoController emprestimoController) {
        this.livroController = livroController;
        this.usuarioController = usuarioController;
        this.emprestimoController = emprestimoController;
    }

    public void exibirMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Sistema de Gerenciamento de Biblioteca ---");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Cadastrar livro");
            System.out.println("3. Listar usuários");
            System.out.println("4. Listar livros");
            System.out.println("5. Alugar livro");
            System.out.println("6. Devolver livro");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    usuarioController.cadastrarUsuario(scanner);
                    break;

                case 2:
                    livroController.cadastrarLivro();
                    break;

                case 3:
                    usuarioController.listarUsuarios();
                    break;

                case 4:
                    livroController.listarLivros();
                    break;

                case 5:
                    emprestimoController.alugarLivro(scanner);
                    break;

                case 6:
                    emprestimoController.devolverLivro(scanner);
                    break;

                case 7:
                    System.out.println("Saindo do sistema...");
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
