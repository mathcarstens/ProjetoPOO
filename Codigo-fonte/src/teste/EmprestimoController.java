package teste;

import java.util.Scanner;

public class EmprestimoController {
    private EmprestimoService emprestimoService;

    public EmprestimoController() {
        emprestimoService = new EmprestimoService();
    }

    public void alugarLivro(Scanner scanner) {
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = scanner.nextInt();
        System.out.print("Digite o ID do livro: ");
        int idLivro = scanner.nextInt();
        emprestimoService.alugarLivro(idUsuario, idLivro);
    }

    public void devolverLivro(Scanner scanner) {
        System.out.print("Digite o ID do usuário: ");
        int idUsuario = scanner.nextInt();
        System.out.print("Digite o ID do livro: ");
        int idLivro = scanner.nextInt();
        emprestimoService.devolverLivro(idUsuario, idLivro);
    }
}
