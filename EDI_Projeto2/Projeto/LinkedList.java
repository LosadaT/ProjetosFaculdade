/* Projeto 2 de Estrutura de Dados I
 *
 * 10417996 - Felipe Viviani Schulze
 * 10364673 - Francisco Losada Totaro
 */

public class LinkedList {
    private Node head;
    private int count;

    LinkedList() {
        head = null;
        count = 0;
    }

    public int count() {return count;}

    // Insere atrás do head atual e define o nó inserido como head
    private void insertFront(Node node) {
        // Ajusta os ponteiros dos nós
        insertBack(node);
        head = node;
    }

    // Insere atrás do head atual
    private void insertBack(Node node) {
        // Ajusta os ponteiros dos nós
        Node tail = head.getPrev();
        node.setNext(head);
        node.setPrev(tail);
        tail.setNext(node);
        head.setPrev(node);
    }

    // Insere um nó na posição pos
    public void insert(String str, int pos) {
        count++;
        Node node = new Node(str);
        if (head == null) {
            head = node;
            return;
        }
        // Quando pos é 0, insere no início da lista
        if (pos == 0) {
            insertFront(node);
            return;
        }
        // Quando pos é -1, insere no fim da lista
        if (pos == -1) {
            insertBack(node);
            return;
        }

        Node pointer = head;
        for (int i = 0; i < pos - 1; i++) {
            pointer = pointer.getNext();
        }

        // Ajusta os ponteiros dos nós
        Node aux = pointer.getNext();
        node.setNext(aux);
        node.setPrev(pointer);
        aux.setPrev(node);
        pointer.setNext(node);
    }

    // Remove o nó da posição pos da lista
    public void remove(int pos) {
        count--;
        Node aux = head;
        for (int i = 0; i < pos; i++) {
            aux = aux.getNext();
        }
        aux.getPrev().setNext(aux.getNext());
        aux.getNext().setPrev(aux.getPrev());
        if (pos == 0) {
            head = aux.getNext();
        }
    }

    // Retorna a String salva no nó da posição pos
    public String getLine(int pos) {
        Node aux = head;
        for (int i = 0; i < pos; i++) {
            aux = aux.getNext();
        }
        return aux.getLine();
    }

    // Define a String do nó na posição "pos" como "str"
    public void setLine(String str, int pos) {
        Node aux = head;
        for (int i = 0; i < pos; i++) {
            aux = aux.getNext();
        }
        aux.setLine(str);
    }
    public boolean isEmpty(){
        if (head == null){
            return true;
        }
        else{
            return false;
        }
    }
}
