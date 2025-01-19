package teste;

import java.util.Scanner;
import java.util.ArrayList;

public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController() {
        usuarioService = new UsuarioService();
    }

    public void cadastrarUsuario(Scanner scanner) {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o tipo (aluno ou professor): ");
        String tipo = scanner.nextLine().toLowerCase();
        usuarioService.cadastrarUsuario(nome, tipo);
    }

    public void listarUsuarios() {
        ArrayList<Usuario> usuarios = usuarioService.listarUsuarios();
        System.out.println("\nUsuários cadastrados:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", Tipo: " + usuario.getTipo());
        }
    }
}
