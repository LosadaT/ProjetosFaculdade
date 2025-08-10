/*
 *      Trabalho de Estrutura de Dados I
 *
 *  Integrantes:
 *   - Felipe Viviani Schulze  - 10417996
 *   - Francisco Losada Totaro - 10364673
 */

public class AvaliaExp {
    PilhaDouble pilha;
    char[] expressao;
    double valores[];
    char[] variaveis;
    int quantVar = 0;
    int tamanhoExp;

    private boolean contem(char[] lista, char c) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == c) {
                return true;
            }
        }
        return false;
    }

    public AvaliaExp(String exp, double[] val) {
        this.tamanhoExp = exp.length();
        this.expressao = exp.toCharArray();
        this.valores = val;
        this.variaveis = new char[tamanhoExp];
        for (int i = 0; i < tamanhoExp; i++) {
            if (Character.isLetter(expressao[i]) == true) {
                if (!contem(variaveis, expressao[i])) {
                    variaveis[quantVar] = expressao[i]; // Coloca todas as variáveis dentro da
                    quantVar++;
                }
            }
        }
        this.pilha = new PilhaDouble(quantVar);
    }

    // https://stackoverflow.com/questions/8071363/calculating-powers-of-integers
    private double pow(double a, double b) {
        if (b == 0) {return 1;}
        double result = 1;
        for (int i = 1; i <= b; i++) {
            result *= a;
        }
        return result;
    }

    public double avaliar() {
        double a, b;
        for (int i = 0; i < tamanhoExp; i++) {
            boolean breakFlag = false;
            for (int j = 0; j < quantVar; j++) {
                if (expressao[i] == variaveis[j]) {
                    pilha.push(valores[j]);
                    breakFlag = true;
                    break;
                }
            }
            if (breakFlag) {
                continue;
            }
            if (expressao[i] == '+') {
                a = pilha.pop();
                b = pilha.pop();
                pilha.push(a + b);
                continue;
            }
            if (expressao[i] == '-') {
                a = pilha.pop();
                b = pilha.pop();
                pilha.push(b - a);
                continue;
            }
            if (expressao[i] == '*') {
                a = pilha.pop();
                b = pilha.pop();
                pilha.push(a * b);
                continue;
            }
            if (expressao[i] == '/') {
                a = pilha.pop();
                b = pilha.pop();
                if (a == 0) {
                    throw new RuntimeException("Divisão por 0");
                }
                pilha.push(b / a);
                continue;
            }
            if (expressao[i] == '^') {
                a = pilha.pop();
                b = pilha.pop();
                pilha.push(pow(a, b));
                continue;
            }
        }

        return pilha.pop();
    }
}