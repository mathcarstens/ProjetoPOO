package teste;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    
    public void cadastrarUsuario(String nome, String tipo) {
        int id = usuarios.size() + 1;
        usuarios.add(new Usuario(id, nome, tipo));
    }

    public void cadastrarLivro(String titulo, String autor) {
        int id = livros.size() + 1;
        livros.add(new Livro(id, titulo, autor, true));
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarios;
    }

    public ArrayList<Livro> listarLivros() {
        return livros;
    }

    public void alugarLivro(int idUsuario, int idLivro) {
        Usuario usuario = usuarios.stream().filter(u -> u.getId() == idUsuario).findFirst().orElse(null);
        Livro livro = livros.stream().filter(l -> l.getId() == idLivro).findFirst().orElse(null);

        if (usuario != null && livro != null && livro.isDisponivel()) {
            livro.setDisponivel(false);
            emprestimos.add(new Emprestimo(usuario, livro));
            System.out.println("Livro alugado com sucesso.");
        } else {
            System.out.println("Erro ao alugar o livro.");
        }
    }

    public void devolverLivro(int idUsuario, int idLivro) {
        Usuario usuario = usuarios.stream().filter(u -> u.getId() == idUsuario).findFirst().orElse(null);
        Livro livro = livros.stream().filter(l -> l.getId() == idLivro).findFirst().orElse(null);

        if (usuario != null && livro != null) {
            livro.setDisponivel(true);
            emprestimos.removeIf(e -> e.getUsuario().getId() == idUsuario && e.getLivro().getId() == idLivro);
            System.out.println("Livro devolvido com sucesso.");
        } else {
            System.out.println("Erro ao devolver o livro.");
        }
    }
}
