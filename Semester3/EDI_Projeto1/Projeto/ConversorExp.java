/*
 *      Trabalho de Estrutura de Dados I
 *
 *  Integrantes:
 *   - Felipe Viviani Schulze  - 10417996
 *   - Francisco Losada Totaro - 10364673
 */

public class ConversorExp {
    private static final char[] OPERADORES = {'+', '-', '*', '/', '^'};
    private static final int NUM_VARIAVEIS = 26;
    private String expressaoInfixa;
    private StringBuilder expressaoPosfixa;
    private PilhaChar pilha;

    public ConversorExp(String expressao) {
        expressaoPosfixa = new StringBuilder();
        pilha = new PilhaChar(NUM_VARIAVEIS);
        expressaoInfixa = expressao;
    }

    public String converter() {
        for (char c : expressaoInfixa.toCharArray()) {
            if (Character.isLetter(c)) {
                expressaoPosfixa.append(c).append(" ");
                continue;
            }
            if (c == '(') {
                pilha.push(c);
                continue;
            }
            if (c == ')') {
                while (!pilha.isEmpty() && pilha.top() != '(') {
                    expressaoPosfixa.append(pilha.pop()).append(" ");
                }
                pilha.pop(); // Remove o '(' da pilha
                continue;
            }
            if (isOperador(c)) {
                while (!pilha.isEmpty() && prioridade(pilha.top()) >= prioridade(c)) {
                    expressaoPosfixa.append(pilha.pop()).append(" ");
                }
                pilha.push(c);
            }
        }

        while (!pilha.isEmpty()) {
            expressaoPosfixa.append(pilha.pop()).append(" ");
        }

        return expressaoPosfixa.toString().trim();
    }

    private static boolean isOperador(char c) {
        for (char operador : OPERADORES) {
            if (c == operador) {
                return true;
            }
        }
        return false;
    }

    private static int prioridade(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
}