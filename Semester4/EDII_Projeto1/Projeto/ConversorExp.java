/*
 *   Baseado no código de: https://www.geeksforgeeks.org/convert-infix-prefix-notation/
 */

 public class ConversorExp {
    private static final char[] OPERADORES = {'+', '-', '*', '/', '^'};
    private static final int NUM_VARIAVEIS = 26;
    private String expressaoInfixa;
    private StringBuilder expressaoPosfixa;
    private PilhaString pilha;
    
    public ConversorExp(String expressao) {
        expressaoPosfixa = new StringBuilder();
        pilha = new PilhaString(NUM_VARIAVEIS);
        expressaoInfixa = expressao;
    }

    private String inverter() {
        String res = new StringBuilder(expressaoInfixa).reverse().toString();
        res = res.replace('(', 'X');
        res = res.replace(')', '(');
        res = res.replace('X', ')');
        return res;
    }

    public PilhaString converter() {
        String expressaoInfixaReversa = inverter();

        String abre = "(";
        String fecha = ")";
        String caractere = "";
        char listaCaracteres[] = expressaoInfixaReversa.toCharArray();
        for (int i = 0; i < listaCaracteres.length; i++) {
            char c = listaCaracteres[i];
            // Posfixa = :5.1::4:
            // caractere = :4:
            // i = 7
            // pilha = * ( (
            //1.5+(2-(3+4))*5.1 - > 1.5*((4+3)-2)+5.1

            caractere = "" + c; // 
            if (Character.isDigit(c)) {
                caractere = caractere + ":"; // 
                i++;
                while (i < listaCaracteres.length && (Character.isDigit(listaCaracteres[i]) || listaCaracteres[i] == '.')) {
                    caractere = listaCaracteres[i] + caractere;
                    i++;
                }
                i--;
                caractere = ":" + caractere;
                expressaoPosfixa.append(caractere);
                continue;
            }
            if (caractere.equals(abre)) {
                pilha.push(caractere);
                continue;
            }

            if (caractere.equals(fecha)) {
                while (!pilha.isEmpty() && !pilha.top().equals(abre)) {
                    String aux = ":" + pilha.pop() + ":";
                    expressaoPosfixa.append(aux);
                }
                pilha.pop(); // Remove o '(' da pilha
                continue;
            }
            if (isOperador(c)) {
                while (!pilha.isEmpty() && prioridade(pilha.top()) >= prioridade(caractere)) {
                    String aux = ":" + pilha.pop() + ":";
                    expressaoPosfixa.append(aux);
                }
                pilha.push(caractere);
            }
        }
        
        while (!pilha.isEmpty()) {
            String aux = ":" + pilha.pop() + ":";
            expressaoPosfixa.append(aux);
        }
        PilhaString pilha = new PilhaString();
        listaCaracteres = expressaoPosfixa.toString().toCharArray();
        for(int i = 0; i < listaCaracteres.length; i++) {
            char c = listaCaracteres[i];
            String num = "" + c;
            if (c == ':') {
                i++;
                num = "";
                while (i < listaCaracteres.length && listaCaracteres[i] != ':') {
                    num = num + listaCaracteres[i];
                    i++;
                }
                i--;
                if (num.trim().length() > 0) {
                    pilha.push(num);
                    continue;
                }
            }
            char list[] = num.toCharArray();
            for (int j = 0; j < list.length; j++) {
                String d = "" + list[j];
                if (d.trim().length() > 0) {
                    pilha.push(d);
                }
            }
        }
        return pilha;
    }

    private static boolean isOperador(char c) {
        for (char operador : OPERADORES) {
            if (c == operador) {
                return true;
            }
        }
        return false;
    }

    private static int prioridade(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }
}
