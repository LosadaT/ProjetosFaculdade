/*
 *  Referências usadas para esta Classe:
 *  - https://www.devmedia.com.br/pilhas-fundamentos-e-implementacao-da-estrutura-em-java/28241
 */

public class PilhaString {

    private String [] pilha;
    private int posicaoPilha;
    private int tamanho;

    public PilhaString() {
        this.posicaoPilha = -1;
        this.pilha = new String[100];
        this.tamanho = 100;
    }

    public PilhaString(int size) {
        this.posicaoPilha = -1;
        this.pilha = new String[size];
        this.tamanho = size;
    }

    public void push(String valor) {
        if (this.posicaoPilha < this.tamanho - 1) {
            this.pilha[++posicaoPilha] = valor;
        }
    }

    public String pop() {
        if (isEmpty()) {
            return null;
        }
        return this.pilha[this.posicaoPilha--];
    }

    public String top() {
        if (this.isEmpty()) {
            return null;
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
        this.pilha = new String[tamanho];
    }
}