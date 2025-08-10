/* Projeto 2 de Estrutura de Dados I
 *
 * 10417996 - Felipe Viviani Schulze
 * 10364673 - Francisco Losada Totaro
 */

public class Node {
    private String line;
    private Node next;
    private Node prev;

    Node(String data) {
        // Um nó sempre começa apontando para sí mesmo em ambas as direções
        next = this;
        prev = this;
        line = data;
    }

    public String getLine() {return line;}
    public Node getNext() {return next;}
    public Node getPrev() {return prev;}

    public void setLine(String data) {line = data;}
    public void setNext(Node next) {this.next = next;}
    public void setPrev(Node prev) {this.prev = prev;}
}



