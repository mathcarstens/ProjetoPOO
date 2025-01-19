package teste;

import java.sql.*;

public class EmprestimoService {

    public void alugarLivro(int usuarioId, int livroId) {
        String sqlUsuario = "SELECT tipo FROM usuarios WHERE id = ?";
        String sqlLivro = "SELECT disponivel FROM livros WHERE id = ?";
        String sqlEmprestimos = "SELECT COUNT(*) AS total FROM emprestimos WHERE usuario_id = ? AND devolvido = FALSE";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario);
             PreparedStatement stmtLivro = conn.prepareStatement(sqlLivro);
             PreparedStatement stmtEmprestimos = conn.prepareStatement(sqlEmprestimos)) {

            stmtUsuario.setInt(1, usuarioId);
            ResultSet rsUsuario = stmtUsuario.executeQuery();
            if (!rsUsuario.next()) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            String tipo = rsUsuario.getString("tipo");

            stmtLivro.setInt(1, livroId);
            ResultSet rsLivro = stmtLivro.executeQuery();
            if (!rsLivro.next() || !rsLivro.getBoolean("disponivel")) {
                System.out.println("Livro não disponível ou não encontrado.");
                return;
            }

            stmtEmprestimos.setInt(1, usuarioId);
            ResultSet rsEmprestimos = stmtEmprestimos.executeQuery();
            rsEmprestimos.next();
            int emprestimosAtuais = rsEmprestimos.getInt("total");
            int limite = tipo.equals("aluno") ? 3 : 5;

            if (emprestimosAtuais >= limite) {
                System.out.println("Usuário já atingiu o limite de empréstimos.");
                return;
            }

            // Realizar o empréstimo
            String sqlAlugar = "INSERT INTO emprestimos (usuario_id, livro_id, devolvido) VALUES (?, ?, FALSE)";
            String sqlAtualizarLivro = "UPDATE livros SET disponivel = FALSE WHERE id = ?";
            try (PreparedStatement stmtAlugar = conn.prepareStatement(sqlAlugar);
                 PreparedStatement stmtAtualizarLivro = conn.prepareStatement(sqlAtualizarLivro)) {
                stmtAlugar.setInt(1, usuarioId);
                stmtAlugar.setInt(2, livroId);
                stmtAlugar.executeUpdate();

                stmtAtualizarLivro.setInt(1, livroId);
                stmtAtualizarLivro.executeUpdate();
                System.out.println("Livro alugado com sucesso.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao alugar livro: " + e.getMessage());
        }
    }

    public void devolverLivro(int usuarioId, int livroId) {
        String sqlDevolver = "UPDATE emprestimos SET devolvido = TRUE WHERE usuario_id = ? AND livro_id = ? AND devolvido = FALSE";
        String sqlAtualizarLivro = "UPDATE livros SET disponivel = TRUE WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmtDevolver = conn.prepareStatement(sqlDevolver);
             PreparedStatement stmtAtualizarLivro = conn.prepareStatement(sqlAtualizarLivro)) {

            stmtDevolver.setInt(1, usuarioId);
            stmtDevolver.setInt(2, livroId);
            int rowsAffected = stmtDevolver.executeUpdate();

            if (rowsAffected > 0) {
                stmtAtualizarLivro.setInt(1, livroId);
                stmtAtualizarLivro.executeUpdate();
                System.out.println("Livro devolvido com sucesso.");
            } else {
                System.out.println("Nenhum empréstimo encontrado para este livro e usuário.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao devolver livro: " + e.getMessage());
        }
    }
}
