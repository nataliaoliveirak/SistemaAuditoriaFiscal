import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // O banco será um arquivo chamado 'banco_fiscal' dentro da pasta do projeto
        String url = "jdbc:h2:./banco_fiscal";
        String usuario = "sa";
        String senha = "";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("✅ Conexão com o Banco de Dados H2 realizada com sucesso!");

            Statement stmt = conn.createStatement();

            // Criando a tabela (Lógica SQL)
            String sqlCreate = "CREATE TABLE IF NOT EXISTS auditoria_notas (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "razao_social VARCHAR(100), " +
                    "cnpj VARCHAR(20), " +
                    "valor_imposto DOUBLE)";
            stmt.execute(sqlCreate);
            System.out.println("📊 Tabela 'auditoria_notas' verificada/criada.");

            // Inserindo um dado de exemplo para testar
            String sqlInsert = "INSERT INTO auditoria_notas (razao_social, cnpj, valor_imposto) " +
                    "VALUES ('Natália Tech Contabilidade', '00.000.000/0001-00', 1550.50)";
            stmt.execute(sqlInsert);
            System.out.println("📥 Dados inseridos para teste de auditoria.");

            // Consultando os dados (O famoso SELECT)
            ResultSet rs = stmt.executeQuery("SELECT * FROM auditoria_notas");
            System.out.println("\n--- RELATÓRIO DE NOTAS AUDITADAS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        " | Empresa: " + rs.getString("razao_social") +
                        " | Imposto: R$ " + rs.getDouble("valor_imposto"));
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro de Banco de Dados: " + e.getMessage());
        }
    }
}