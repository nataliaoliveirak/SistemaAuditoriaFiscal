import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Usaremos o nome 'banco_fiscal_v3' para garantir que ele crie tudo do zero e limpo
        String url = "jdbc:h2:./banco_fiscal_v3";
        String usuario = "sa";
        String senha = "";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            Statement stmt = conn.createStatement();

            // 1. Criando a tabela com a estrutura completa
            stmt.execute("CREATE TABLE IF NOT EXISTS auditoria_fiscal (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "empresa VARCHAR(100), " +
                    "valor_mercadoria DOUBLE, " +
                    "valor_difal DOUBLE, " +
                    "valor_st DOUBLE)");

            // 2. Criando o objeto da Calculadora
            CalculadoraFiscal calc = new CalculadoraFiscal();

            // 3. Fazendo os cálculos de ICMS Interestadual (Lógica Contábil)
            double valorNota = 1000.0;
            double meuDifal = calc.calcularDifal(valorNota, 18.0, 12.0);
            double meuST = calc.calcularST(valorNota, 40.0, 18.0);

            // 4. Inserindo no banco (Apenas UM Insert com todos os dados)
            String sqlInsert = "INSERT INTO auditoria_fiscal (empresa, valor_mercadoria, valor_difal, valor_st) " +
                    "VALUES ('Fornecedor MG', " + valorNota + ", " + meuDifal + ", " + meuST + ")";

            stmt.execute(sqlInsert);

            System.out.println("✅ Auditoria realizada com sucesso!");
            System.out.println("📊 Valor Mercadoria: R$ " + valorNota);
            System.out.println("📊 DIFAL calculado: R$ " + meuDifal);
            System.out.println("📊 ST calculado: R$ " + meuST);

        } catch (SQLException e) {
            System.out.println("❌ Erro no Banco de Dados: " + e.getMessage());
        }
    }
}