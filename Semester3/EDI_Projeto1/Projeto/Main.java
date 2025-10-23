/*
        *      Trabalho de Estrutura de Dados I
        *
        *  Integrantes:
        *   - Felipe Viviani Schulze  - 10417996
        *   - Francisco Losada Totaro - 10364673
        */

import java.util.Scanner;

public class Main {
    private static final char[] OPERADORES = {'+', '-', '*', '/', '^'};
    private static final int NUM_VARIAVEIS = 26;

    private static boolean expressaoValida(String expressao) {
        int countParenteses = 0;
        // Verifica se a expressão contém apenas operadores, variáveis válidas e parênteses balanceados
        for (char c : expressao.toCharArray()) {
            if (!Character.isLetter(c) && new String(OPERADORES).indexOf(c) == -1 && c != ' ' && c != '(' && c != ')') {
                return false;
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
        return countParenteses == 0; // Verifica se os parênteses estão balanceados
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expressaoInfixa = "";
        char[] variaveis = new char[NUM_VARIAVEIS];
        double[] valores = new double[NUM_VARIAVEIS];
        int totalValores = 0;

        boolean valoresInseridos = false;
        int opcao;
        do {
            System.out.println("\nMenu de Opções:");
            System.out.println("1. Entrada da expressão aritmética na notação infixa");
            System.out.println("2. Entrada dos valores numéricos associados às variáveis");
            System.out.println("3. Conversão da expressão para notação polonesa reversa");
            System.out.println("4. Avaliação da expressão");
            System.out.println("5. Encerramento do programa");
            System.out.print("Escolha uma opção: ");
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
            } else {
                scanner.nextLine(); // Consumir a entrada inválida
                opcao = 0;
            }
            System.out.println("");
            switch (opcao) {
                //Entrada da expreção infixa
                case 1:
                    System.out.print("Digite a expressão aritmética na notação infixa: ");
                    expressaoInfixa = scanner.nextLine().toLowerCase(); // Convertendo para minúsculas
                    if (!expressaoValida(expressaoInfixa)) {
                        System.out.println("Expressão inválida.");
                        expressaoInfixa = "";
                    }
                    totalValores = 0;
                    valoresInseridos = false;
                    break;
                //Entrada dos valores
                case 2:
                    for (int i = 0; i < variaveis.length; i++) {
                        char variavel = (char) ('a' + i); // Modificado para aceitar tanto minúsculas quanto maiúsculas
                        if (expressaoInfixa.indexOf(variavel) != -1) {
                            System.out.print("Digite o valor de " + variavel + ": ");
                            while (!scanner.hasNextDouble()) {
                                System.out.print("Por favor, insira um número válido: ");
                                scanner.next(); // Consumir entrada inválida
                            }
                            valores[i] = scanner.nextDouble();
                            totalValores++;
                        }
                    }
                    valoresInseridos = true;
                    break;
                //Conversao
                case 3:
                    if (expressaoInfixa.isEmpty()) {
                        System.out.println("Por favor, insira uma expressão antes de converter para notação polonesa reversa.");
                    } else {
                        ConversorExp conversor = new ConversorExp(expressaoInfixa);
                        String expressaoPosfixa = conversor.converter();
                        System.out.println("Expressão em notação polonesa reversa: " + expressaoPosfixa);
                    }
                    break;

                //Avaliçao
                case 4:
                    if (expressaoInfixa.isEmpty()) {
                        System.out.println("Por favor, insira uma expressão antes de avaliá-la.");
                    } else if (!valoresInseridos){
                        System.out.println("Por favor, insira os valores das variáveis antes de avaliar a expressão.");
                    } else {
                        ConversorExp conversor = new ConversorExp(expressaoInfixa);
                        String expressaoPosfixa = conversor.converter();
                        System.out.println("Expressão: " + expressaoPosfixa);

                        for (int i = 0; i < totalValores; i++) {
                            char variavel = (char) ('a' + i);
                            if (expressaoInfixa.indexOf(variavel) != -1) {
                                System.out.print("Valor de " + variavel + ": ");
                                System.out.println(valores[i]);
                            }
                        }

                        AvaliaExp avaliador = new AvaliaExp(expressaoPosfixa, valores);
                        double resultado = avaliador.avaliar();
                        System.out.println("\nResultado da expressão: " + resultado);
                    }
                    break;
                case 5:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}