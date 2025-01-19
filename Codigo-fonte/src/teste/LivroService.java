package teste;

import java.sql.*;

public class LivroService {

    public void cadastrarLivro(String titulo, String autor) {
        String sql = "INSERT INTO livros (titulo, autor, disponivel) VALUES (?, ?, 1)"; // Disponível por padrão

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.executeUpdate();
            System.out.println("Livro cadastrado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public void listarLivros() {
        String sql = "SELECT id, titulo, autor, disponivel FROM livros";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                boolean disponivel = rs.getBoolean("disponivel");
                System.out.println("ID: " + id + ", Título: " + titulo + ", Autor: " + autor + ", Disponível: " + disponivel);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }
}
