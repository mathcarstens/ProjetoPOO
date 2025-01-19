package teste;

import java.util.Scanner;

public class LivroController {

    private LivroService livroService;
    private Scanner scanner;

    public LivroController(LivroService livroService, Scanner scanner) {
        this.livroService = livroService;
        this.scanner = scanner;
    }

    public void cadastrarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        // Chama o método do LivroService para cadastrar o livro
        livroService.cadastrarLivro(titulo, autor);
    }

    public void listarLivros() {
        // Chama o método do LivroService para listar livros
        livroService.listarLivros();
    }
}
