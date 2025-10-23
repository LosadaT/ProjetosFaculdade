/*
 *      Trabalho de Estrutura de Dados I
 *
 *  Integrantes:
 *   - Felipe Viviani Schulze  - 10417996
 *   - Francisco Losada Totaro - 10364673
 *
 *  Referências usadas para esta Classe:
 *  - https://www.devmedia.com.br/pilhas-fundamentos-e-implementacao-da-estrutura-em-java/28241
 */

public class PilhaChar {

    private char [] pilha;
    private int posicaoPilha;
    private int tamanho;

    public PilhaChar() {
        this.posicaoPilha = -1;
        this.pilha = new char[100];
        this.tamanho = 100;
    }

    public PilhaChar(int size) {
        this.posicaoPilha = -1;
        this.pilha = new char[size];
        this.tamanho = size;
    }

    public void push(char valor) {
        if (this.posicaoPilha < this.tamanho - 1) {
            this.pilha[++posicaoPilha] = valor;
        }
    }

    public char pop() {
        if (isEmpty()) {
            return '\0';
        }
        return this.pilha[this.posicaoPilha--];
    }

    public char top() {
        if (this.isEmpty()) {
            return '\0';
        }
        return this.pilha[this.posicaoPilha];
    }

    public int size() {
        return this.tamanho;
    }

    public int count() {
        if (this.isEmpty()) {
            return 0;
        }
        return this.posicaoPilha + 1;
    }

    public boolean isEmpty() {
        if (this.posicaoPilha == -1) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (this.posicaoPilha == this.tamanho - 1) {
            return true;
        }
        return false;
    }

    public void clear() {
        this.posicaoPilha = -1;
        this.pilha = new char[tamanho];
    }
}
