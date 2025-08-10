/* Projeto 2 de Estrutura de Dados I
 *
 * 10417996 - Felipe Viviani Schulze
 * 10364673 - Francisco Losada Totaro
 */

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        LinkedList lista = new LinkedList();
        Interpretador interpretador = new Interpretador(lista);
        String cmd = "";
        System.out.print("Insira o comando: ");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while (true) {
            cmd = interpretador.executar(input);
            if (cmd.equals(":wq") || cmd.equals(":q!")) {
                break;
            }
            System.out.print("Insira o comando: ");
            s = new Scanner(System.in);
            input = s.nextLine();
        }
        s.close();
    }
}
