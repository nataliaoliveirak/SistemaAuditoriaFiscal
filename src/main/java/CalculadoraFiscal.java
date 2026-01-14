public class CalculadoraFiscal {

    // Método para calcular o DIFAL (Diferencial de Alíquota)
    public double calcularDifal(double valorMercadoria, double aliqInternaSP, double aliqInterestadual) {
        // Lógica: (Alíquota Interna - Alíquota Interestadual) aplicada sobre o valor
        double diferenca = aliqInternaSP - aliqInterestadual;
        return valorMercadoria * (diferenca / 100);
    }

    // Método simples para calcular ICMS ST (Exemplo básico para portfólio)
    public double calcularST(double valorMercadoria, double mva, double aliqInterna) {
        // Base ST = Valor + (Valor * MVA)
        double baseST = valorMercadoria * (1 + (mva / 100));
        return baseST * (aliqInterna / 100);
    }
}