/* Projeto 2 de Estrutura de Dados I
 *
 * 10417996 - Felipe Viviani Schulze
 * 10364673 - Francisco Losada Totaro
 */

import java.io.*;
import java.util.Scanner;

public class Interpretador {
    private LinkedList lista;
    private LinkedList aux;
    private String[] comandos;
    private int comeco;
    private int fim;
    private String nomeArquivo;
    Interpretador(LinkedList lista) {
        aux = new LinkedList();
        this.comandos = new String[]{":e",      // :e (Arq.ext), Abrir arquivo "Arq.ext"
                ":w",      // :w [Arq.ext], Salvar as modificações em arquivo [Arq.ext]
                ":wq",     // :wq, Salvar as modificações em arquivo e finalizar o editor
                ":q!",     // :q!, Finalizar o editor sem salvar
                ":ZZ",     // :ZZ, Gravar conteúdo em arquivo
                ":v",      // :v (LinIni) (LinFin), Marcar texto das linhas "LinIni" até "LinFin"
                ":y",      // :y, Copia o texto marcado
                ":c",      // :c, Recorta o texto marcado
                ":p",      // :p (Linha), Cola o texto copiado/recordado a partir da linha "Linha"
                ":s",      // :s [LinIni] [LinFin], Exibir na tela o conteúdo
                //  das linhas "LinIni" até "LinFin"
                ":x",      // :x (Linha), Apaga a linha "Linha"
                ":xG",     // :xG (Linha), Apaga todas as linhas abaixo da linha "Linha", inclusive
                ":XG",     // :XG (Linha), Apaga todas as linhas acima da linha "Linha", inclusive
                ":/",      // :/ (Elem) [ElemTroca] [Linha], Exibe/troca todos as instâncias de
                //  "Elem" {por "ElemTroca"} {na linha "Linha"}
                ":o",      // :o, apaga a lista e insere elementos, termina na linha com somente ":o"
                ":a",      // :a (Linha), insere elementos após a linha "Linha", termina na linha
                //  com somente ":a"
                ":i",      // :i (Linha), insere elementos antes da linha "Linha", termina na linha
                //  com somente ":i"
                ":help"};  // :help, imprime o funcionamento de todos os comandos
        this.lista = lista;
    }

    @SuppressWarnings("resource") // Se eu tentar fechar os scanners nesse arquivo o código não funciona, coloquei isso pra n aparecer o aviso
    public String executar(String input) {
        String[] param = input.split(" ");
        int cmd = -1;

        // Verifica qual comando foi digitado
        for (int i = 0; i < comandos.length; i++) {
            if (param[0].equals(comandos[i])) {
                cmd = i;
                break;
            }
        }
        switch (cmd) {
            // Comandos de arquivo
            case 0: // :e
            lista = new LinkedList(); // Reinicia a lista anterior
                if (param.length != 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                nomeArquivo = param[1];
                try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
                    String linha;
                    while((linha = br.readLine()) != null){
                        Node node = new Node(linha);
                        int count = lista.count();
                        String lin = node.getLine();
                        lista.insert(lin, count);
                    }
                }
                catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                    break;
                }
                System.out.println("Arquivo lido com sucesso.");
                break;

            case 1: // :w
                if (param.length > 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                if (lista.isEmpty() == true){
                    System.out.println("Arquivo não aberto.");
                    break;
                }
                String nomeArq = nomeArquivo;
                if (param.length == 2) {
                    nomeArq = param[1];
                }
                File file = new File(nomeArq);
                file.delete();
                try (FileWriter arq = new FileWriter(nomeArq)){
                    PrintWriter gravarArq = new PrintWriter(arq);
                    for (int i=0; i<lista.count(); i++) {
                        gravarArq.printf(lista.getLine(i));
                        gravarArq.printf("\n");
                    }
                }
                catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                    break;
                }
                System.out.println("Arquivo salvo com sucesso.");
                break;

            case 2: // :wq 
                if (param.length !=1) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                if (lista.isEmpty() == true){
                    System.out.println("Arquivo não aberto.");
                    break;
                }
                nomeArq = nomeArquivo;
                if (param.length == 2) {
                    nomeArq = param[1];
                }
                File file2 = new File(nomeArq);
                file2.delete();
                try (FileWriter arq2 = new FileWriter(nomeArq)){
                    PrintWriter gravarArq = new PrintWriter(arq2);
                    for (int i=0; i<lista.count(); i++) {
                        gravarArq.printf(lista.getLine(i));
                        gravarArq.printf("\n");
                    }
                }
                catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                }
                System.out.println("O programa foi encerrado salvando o arquivo.");
                System.exit(0);
                break;


            case 3: // :q! 
                System.out.println("O programa foi encerrado sem salvar as alterações.");
                System.exit(0);
                break;


            case 4: // :ZZ
                if (param.length != 1) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                if (lista.isEmpty() == true){
                    System.out.println("Arquivo não aberto.");
                    break;
                }
                nomeArq = nomeArquivo;
                if (param.length == 2) {
                    nomeArq = param[1];
                }
                File file3 = new File(nomeArq);
                file3.delete();
                try (FileWriter arq3 = new FileWriter(nomeArq)){
                    PrintWriter gravarArq = new PrintWriter(arq3);
                    for (int i=0; i<lista.count(); i++) {
                        gravarArq.printf(lista.getLine(i));
                        gravarArq.printf("\n");
                    }
                }
                catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                    break;
                }
                System.out.println("Arquivo gravado com sucesso.");
                break;

            // Comandos de Copia/Cola
            case 5: // :v
                if (param.length != 3) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                comeco = Integer.parseInt(param[1]);
                fim = Integer.parseInt(param[2]);
                if(comeco>fim) {
                    System.out.println("Começo menor que o fim");
                    break;
                }
                aux = new LinkedList();

                int n = 0; //contador
                for (int i = comeco-1; i<fim; i++) {
                    aux.insert(lista.getLine(i), n);
                    n++;
                }
                System.out.println("Trecho marcado.");
                break;

            case 6: // :y
                if (param.length != 1) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                if (aux.isEmpty()) {
                    System.out.println("Não tem texto marcado");
                }
                else {
                    System.out.println("Texto copiado");
                }
                break;

            case 7: // :c
                if (param.length != 1) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                if (aux.isEmpty()) {
                    System.out.println("Não tem texto marcado");
                }
                else {
                    for (int i = comeco-1; i<fim; i++) {
                        lista.remove(comeco-1);

                    }
                    System.out.println("Texto recortado");
                }
                break;

            case 8: // :p
                if (param.length != 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                if (aux.isEmpty()) {
                    System.out.println("Não tem texto marcado");
                }

                int posColar = Integer.parseInt(param[1]);
                if (posColar>lista.count()+1) {
                    System.out.println("Posição invalida");
                    break;
                }
                int n1 = 0;
                for(int i = posColar-1; i<aux.count()+posColar-1; i++) {
                    lista.insert(aux.getLine(n1), i);
                    n1++;
                }
                System.out.println("Texto copiado.");
                break;

            // Comandos de exibição/remoção
            case 9: // :s
                int s_LinIni = 0;
                int s_LinFim = lista.count();
                if (param.length > 3) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                if (param.length == 2) {
                    s_LinIni = Integer.valueOf(param[1]);
                    if (s_LinIni > lista.count() || s_LinIni < 1) {
                        System.out.println("Posição inválida");
                    } else {
                        System.out.println((s_LinIni) + ". " + lista.getLine(s_LinIni - 1));
                    }
                    break;
                }
                if (param.length == 3) {
                    s_LinIni = Integer.valueOf(param[1]) - 1;
                    int par2 = Integer.valueOf(param[2]);
                    int size = lista.count();
                    if (par2 > size) {
                        s_LinFim = size;
                    } else {
                        s_LinFim = par2;
                    }
                }
                Scanner s_s = new Scanner(System.in);
                int s_counter = 0;
                for (int i = s_LinIni; i < s_LinFim; i++) {
                    s_counter++;
                    System.out.println((i+1) + ". " + lista.getLine(i));
                    if (s_counter == 10) {
                        s_counter = 0;
                        s_s.nextLine();
                        s_s = new Scanner(System.in);
                    }
                }
                break;
            case 10: // :x
                if (param.length != 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                lista.remove(Integer.valueOf(param[1]) - 1);
                break;

            case 11: // :xG
                if (param.length != 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                int i = Integer.valueOf(param[1]) - 1;
                while (i < lista.count()) {
                    lista.remove(i);
                }
                break;
            case 12: // :XG
                if (param.length != 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                for (int j = 0; j < Integer.valueOf(param[1]); j++) {
                    lista.remove(0);
                }
                break;
            case 13: // :/
                if (param.length > 4 || param.length < 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                if (param.length == 2) {
                    boolean encontrado = false;
                    for (i = 0; i < lista.count(); i++) {
                        if (lista.getLine(i).contains(param[1])) {
                            if (!encontrado) {
                                encontrado = true;
                            }
                            System.out.println((i+1) + ". " + lista.getLine(i));
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Elemento não encontrado");
                    }
                    break;
                }
                if (param.length == 3) {
                    String line = "";
                    boolean existe = false;
                    for (i = 0; i < lista.count(); i++) {
                        line = lista.getLine(i);
                        if (line.contains(param[1])) {
                            existe = true;
                            lista.setLine(line.replace(param[1], param[2]), i);
                        }
                    }
                    if (!existe) {
                        System.out.println("Não há nenhuma aparições de \"" + param[1] + "\" no arquivo.");
                    } else {
                        System.out.println("Todas as aparições de \"" + param[1] + "\" foram substituídas.");
                    }
                    break;
                }
                if (param.length == 4) {
                    int pos = Integer.valueOf(param[3]) - 1;
                    String line = lista.getLine(pos);
                    String newLine = line.replace(param[1], param[2]);
                    boolean existe = false;
                    lista.setLine(newLine, pos);
                    if (line.contains(param[1])) {existe = true;};
                    if (!existe) {
                        System.out.println("Não há nenhuma aparições de \"" + param[1] + "\" na linha " + param[3]);
                    } else {
                        System.out.println("Todas as aparições de \"" + param[1] + "\" na linha " + param[3] + " foram substituídas.");
                    }
                    break;
                }
                break;
            case 14: // :o
                if (param.length != 1) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                lista = new LinkedList();
                String o_input = "";
                Scanner o_s = new Scanner(System.in);

                while (true) {
                    o_input = o_s.nextLine();
                    o_s = new Scanner(System.in);
                    if (o_input.compareTo(":o") == 0) {
                        break;
                    }
                    lista.insert(o_input, -1);
                }
                System.out.println("Edição finalizada.");
                break;
            case 15: // :a
                if (param.length != 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                String a_input = "";
                Scanner a_s = new Scanner(System.in);
                int a_pos = Integer.valueOf(param[1]);
                LinkedList a_listaAux = new LinkedList();

                while (true) {
                    a_input = a_s.nextLine();
                    a_s = new Scanner(System.in);
                    if (a_input.compareTo(":a") == 0) {
                        break;
                    }
                    a_listaAux.insert(a_input, 0);
                }
                for (i = 0; i < a_listaAux.count(); i++) {
                    lista.insert(a_listaAux.getLine(i), a_pos);
                }
                System.out.println("Edição finalizada.");
                break;
            case 16: // :i
                if (param.length != 2) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                String i_input = "";
                Scanner i_s = new Scanner(System.in);
                int i_pos = Integer.valueOf(param[1]) - 1;
                LinkedList i_listaAux = new LinkedList();

                while (true) {
                    i_input = i_s.nextLine();
                    i_s = new Scanner(System.in);
                    if (i_input.compareTo(":i") == 0) {
                        break;
                    }
                    i_listaAux.insert(i_input, 0);
                }
                for (i = 0; i < i_listaAux.count(); i++) {
                    lista.insert(i_listaAux.getLine(i), i_pos);
                }
                System.out.println("Edição finalizada.");
                break;
            // Comando de ajuda
            case 17: // :help
                if (param.length != 1) {
                    System.out.println("Quantidade incorreta de parâmetros");
                    break;
                }
                System.out.println("  Parâmetros entre () são obrigatórios e entre [] são opcionais.");
                System.out.println("Para mudar de página, digite > ou <");
                System.out.println("Digite \"s\" para fechar");
                int pag = 1;
                Scanner help_s = new Scanner(System.in);
                String help_pageSwitch = "";
                while (help_pageSwitch.compareTo("s") != 0) {
                    switch(pag) {
                        case 1:
                            System.out.println("     Comando      \u2502            Ação resultante              \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :e (nomeArq.ext) \u2502 Abrir o arquivo de nome \"nomeArq.ext\" \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :w [nomeArq.ext] \u2502 Salvar o arquivo aberto nele mesmo ou no\n"+
                                    "                  \u2502 arquivo de nome \"nomeArq.ext\"         \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :wq              \u2502 Salvar o arquivo e fechar o editor      \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :q!              \u2502 Fechar o editor sem salvar              \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :ZZ              \u2502 Gravar conteúdo no arquivo              \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    "                Pág 1\n");
                            break;
                        case 2:
                            System.out.println("       Comando        \u2502            Ação resultante              \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :v (LinIni) (LinFim) \u2502 Marcar o texto das linhas LinIni até LinFim\n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :y                   \u2502 Copia o texto marcado\n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :p (Lin)             \u2502 Cola o texto copiado/recortado a partir de Lin\n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :s [LinIni] [LinFim] \u2502 Exibe o conteúdo do arquivo de 10 em 10 linhas, ou\n"+
                                    "                      \u2502 da linha LinIni, ou da LinIni até LinFim\n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :x (Lin)             \u2502 Apagar da linha Lin até o fim do arquivo              \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    "                Pág 2\n");
                            break;
                        case 3:
                            System.out.println("         Comando         \u2502            Ação resultante              \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :xG (Lin)               \u2502 Apagar de Lin até o fim do arquivo      \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :XG (Lin)               \u2502 Apagar de Lin até o começo do arquivo   \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :/ (elem) [Troca] [Lin] \u2502 Localizar as linhas que contém \"elem\" \n"+
                                    "                         \u2502 e exibí-las, ou trocar \"elem\" por \n"+
                                    "                         \u2502 \"troca\" (na linha Lin)\n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :o                      \u2502 \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :a (PosLin)             \u2502 Gravar conteúdo no arquivo              \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    "                Pág 3\n");
                            break;
                        case 4:
                            System.out.println("     Comando      \u2502            Ação resultante              \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :i (PosLin)      \u2502 Abrir o arquivo de nome \"nomeArq.ext\" \n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u253C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    " :help            \u2502 Abre esse menu de ajuda\n"+
                                    "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2534\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n"+
                                    "                Pág 4\n");
                            break;
                        default:
                            break;
                    }
                    help_pageSwitch = help_s.nextLine();
                    help_s = new Scanner(System.in);
                    if (help_pageSwitch.compareTo(">") == 0) {
                        if (pag != 4) {pag++;}
                    }
                    if (help_pageSwitch.compareTo("<") == 0) {
                        if (pag != 1) {pag--;}
                    }
                }
                break;
            default:
                System.out.println("Comando desconhecido.");
                break;
        }
        return param[0];
    }
}