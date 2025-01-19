package teste;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciando os serviços
        LivroService livroService = new LivroService();
        UsuarioService usuarioService = new UsuarioService();
        EmprestimoService emprestimoService = new EmprestimoService();

        // Instanciando os controllers
        LivroController livroController = new LivroController(livroService, scanner);
        UsuarioController usuarioController = new UsuarioController();
        EmprestimoController emprestimoController = new EmprestimoController();

        // Instanciando o controlador principal
        BibliotecaController bibliotecaController = new BibliotecaController(livroController, usuarioController, emprestimoController);

        // Exibindo o menu
        bibliotecaController.exibirMenu(scanner);
    }
}
