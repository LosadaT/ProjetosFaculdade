
import java.util.Scanner;

public class Main {
    private static final char[] OPERADORES = {'+', '-', '*', '/', '^'};
    public static void main(String[] args) {
        ArvoreExpressao arvore = new ArvoreExpressao();
        Scanner s = new Scanner(System.in);
        String exp = null;
        boolean existeArvore = false;
        boolean continua = true;

        while (continua) {
            System.out.println("\nMenu:\n");
            System.out.println(" 1 - Entrada da expressão aritmética (notação infixa)\n");
            System.out.println(" 2 - Criação de árvore binária\n");
            System.out.println(" 3 - Exibição da árvore binária\n");
            System.out.println(" 4 - Cálculo da expressão\n");
            System.out.println(" 5 - Encerrar o programa\n");
            System.out.println("Digite a opção: ");

            int i = s.nextInt();
            while (i < 1 || i > 5) {
                System.out.println("Opção inválida, insira novamente: ");
                i = s.nextInt();
                
            }
            s = new Scanner(System.in);
            switch (i) {
                case 1:
                    System.out.println("Insira a expressão: ");
                    exp = s.nextLine();
                    while (!expressaoValida(exp)) {
                        System.out.println("Expressão inválida\nDigite novamente: ");
                        exp = s.nextLine();
                    }
                    break;
                case 2:
                    if (exp == null) {
                        System.out.println("Expressão não foi inserida!\n");
                    } else {
                        arvore.inserir(exp);
                        System.out.println("Expressão inserida na árvore!\n");
                        existeArvore = true;
                    }
                    break;
                case 3:
                    if (existeArvore) {
                        arvore.exibirArvore();
                        System.out.println("\nEm Ordem: ");
                        arvore.exibirArvoreEmOrdem();
                        System.out.println("\nPré Ordem: ");
                        arvore.exibirArvorePreOrdem();
                        System.out.println("\nPos Ordem: ");
                        arvore.exibirArvorePosOrdem();
                    }
                    break;
                case 4:
                    System.out.println("O resultado da expressão é: " + arvore.calcular() + "\n");
                    break;
                case 5:
                    continua = false;
                    break;
                default:
                    break;
            }
        }
        s.close();
    }

    private static boolean expressaoValida(String expressao) {
        int countParenteses = 0;
        boolean operador = false;
        boolean inicio = true;
        // Verifica se a expressão contém apenas operadores, variáveis válidas e parênteses balanceados
        for (char c : expressao.toCharArray()) {
            if (!Character.isDigit(c) && new String(OPERADORES).indexOf(c) == -1 && c != ' ' && c != '(' && c != ')' && c != '.') {
                return false;
            }
            if (new String(OPERADORES).indexOf(c) != -1) {
                if (!operador) {
                    operador = true;
                } else {
                    operador = false;
                    return false;
                }
            }
            if (operador && inicio) {
                return false;
            }
            inicio = false;
            if (Character.isDigit(c)) {
                operador = false;
            }
            if (c == '(') {
                countParenteses++;
            } else if (c == ')') {
                countParenteses--;
                if (countParenteses < 0) {
                    return false; // Mais parênteses de fechamento do que de abertura
                }
            }
        }
        if (operador) { // último lido foi operador
            return false;
        }
        return countParenteses == 0; // Verifica se os parênteses estão balanceados
    }
}
