import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int menu() {
        System.out.println("1 - Inserir Dados");
        System.out.println("2 - Buscar Dados");
        System.out.println("3 - Remover Dados");
        System.out.println("4 - Análise e Estatística");
        System.out.println("5 - Sair");
        System.out.print("Selecione uma opção: ");
        Scanner s = new Scanner(System.in);
        int opcao = s.nextInt();
        while (opcao < 1 || opcao > 5) {
            System.out.print("Opção Inválida!\nSelecione uma opção: ");
            opcao = s.nextInt();
        }
        return opcao;
    }
    //funcao para mostrar informacoes do print
    public static void buscarPrint(No no) {
    	if(no == null) {
    		System.out.println("Valor não encontrado na arvore");
    		return;
    	}
        System.out.println(" ");
    	System.out.println("codigo: " + no.getChave());
    	System.out.println("Zona: " + no.getDado().getZona());
    	System.out.println("Municipio: " + no.getDado().getMunicipio());
    	System.out.println("ed_infantil: " + no.getDado().getEdInfantil());
    	System.out.println("ed_especial: " + no.getDado().getEdEspecial());
    	System.out.println("ed_recurso: " + no.getDado().getEdRecurso());
    	System.out.println("ano_ini: " + no.getDado().getAnoIni());
    	System.out.println("ano_fim: " + no.getDado().getAnoFim());
    	System.out.println("ed_medio: " + no.getDado().getEdMedio());
    	System.out.println("eja_fund_1: " + no.getDado().getEjaFund1());
    	System.out.println("eja_fund_2: " + no.getDado().getEjaFund2());
    	System.out.println("eja_medio: " + no.getDado().getEjaMedio());
        System.out.println(" ");
    }
    
    public static void main(String[] args) {
    	Scanner s = new Scanner(System.in);
        ArvoreAVL avl2019_1 = new ArvoreAVL();
        ArvoreAVL avl2019_2 = new ArvoreAVL();
        ArvoreAVL avl2020_1 = new ArvoreAVL();
        ArvoreAVL avl2020_2 = new ArvoreAVL();
        ArvoreAVL avl2021_1 = new ArvoreAVL();
        ArvoreAVL avl2021_2 = new ArvoreAVL();
        ArvoreAVL avl2022_1 = new ArvoreAVL();
        ArvoreAVL avl2022_2 = new ArvoreAVL();
        ArvoreAVL avl2023_1 = new ArvoreAVL();
        ArvoreAVL avl2023_2 = new ArvoreAVL();

        ArvoreBST bst2019_1 = new ArvoreBST();
        ArvoreBST bst2019_2 = new ArvoreBST();
        ArvoreBST bst2020_1 = new ArvoreBST();
        ArvoreBST bst2020_2 = new ArvoreBST();
        ArvoreBST bst2021_1 = new ArvoreBST();
        ArvoreBST bst2021_2 = new ArvoreBST();
        ArvoreBST bst2022_1 = new ArvoreBST();
        ArvoreBST bst2022_2 = new ArvoreBST();
        ArvoreBST bst2023_1 = new ArvoreBST();
        ArvoreBST bst2023_2 = new ArvoreBST();
        int compBST, compAVL;
        
        int opcao = 0;
        while (opcao != 5) {
            opcao = menu();
            switch (opcao) {
                case 1: // Inserir
                    String arq19_1 = "20191.csv";
                    String arq19_2 = "20192.csv";
                    String arq20_1 = "20201.csv";
                    String arq20_2 = "20202.csv";
                    String arq21_1 = "20211.csv";
                    String arq21_2 = "20212.csv";
                    String arq22_1 = "20221.csv";
                    String arq22_2 = "20222.csv";
                    String arq23_1 = "20231.csv";
                    String arq23_2 = "20232.csv";
                    String linha, titulos;
                    List<String> val;
                    compAVL = 0;
                    compBST = 0;
                    //Ler arquivo 2019_1
                    try (BufferedReader br = new BufferedReader(new FileReader(arq19_1))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2019_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2019_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2019_1.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2019_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    //Ler arquivo 2019_2
                    try (BufferedReader br = new BufferedReader(new FileReader(arq19_2))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2019_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2019_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2019_2.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2019_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // Ler arquivo 2020_1
                    try (BufferedReader br = new BufferedReader(new FileReader(arq20_1))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2020_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2020_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2020_1.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2020_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // Ler arquivo 2020_2
                    try (BufferedReader br = new BufferedReader(new FileReader(arq20_2))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2020_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2020_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2020_2.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2020_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // Ler arquivo 2021_1
                    try (BufferedReader br = new BufferedReader(new FileReader(arq21_1))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2021_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2021_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2021_1.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2021_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // Ler arquivo 2021_2
                    try (BufferedReader br = new BufferedReader(new FileReader(arq21_2))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2021_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2021_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2021_2.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2021_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // Ler arquivo 2022_1
                    try (BufferedReader br = new BufferedReader(new FileReader(arq22_1))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2022_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2022_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2022_1.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2022_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // Ler arquivo 2022_2
                    try (BufferedReader br = new BufferedReader(new FileReader(arq22_2))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2022_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2022_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2022_2.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2022_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    } 
                    // Ler arquivo 2023_1
                    try (BufferedReader br = new BufferedReader(new FileReader(arq23_1))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2023_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2023_1.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2023_1.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2023_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // Ler arquivo 2023_2
                    try (BufferedReader br = new BufferedReader(new FileReader(arq23_2))) {
                        titulos = br.readLine();
                        compAVL = 0;
                        compBST = 0;
                        while ((linha = br.readLine()) != null) {
                            val = Arrays.asList(linha.split(";"));
                            compBST += bst2023_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            avl2023_2.inserir(Integer.parseInt(val.get(0)), Integer.parseInt(val.get(1)), val.get(2), Integer.parseInt(val.get(3)), Integer.parseInt(val.get(4)), Integer.parseInt(val.get(5)), Integer.parseInt(val.get(6)), Integer.parseInt(val.get(7)), Integer.parseInt(val.get(8)), Integer.parseInt(val.get(9)), Integer.parseInt(val.get(10)), Integer.parseInt(val.get(11)));
                            compAVL += avl2023_2.compInserir;
                        }
                        System.out.println("Quantidade de comparações para Planílha 2023_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                    
                case 2: // Buscar
                	System.out.println("Ano que deseja buscar (ex: 20221 -> primeiro semestre de 2021): ");
                    int anoBuscar = s.nextInt();
                    System.out.println("Codigo da escola: ");
                    int codBuscar = s.nextInt();
                    
                    switch (anoBuscar) {
                    	case 20191:
                    		buscarPrint(avl2019_1.buscar(codBuscar));
                            compAVL = avl2019_1.compBusca;
                    		buscarPrint(bst2019_1.buscar(codBuscar));
                            compBST = bst2019_1.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2019_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20192:
                    		buscarPrint(avl2019_2.buscar(codBuscar));
                            compAVL = avl2019_2.compBusca;
                    		buscarPrint(bst2019_2.buscar(codBuscar));
                            compBST = bst2019_2.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2019_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20201:
                    		buscarPrint(avl2020_1.buscar(codBuscar));
                            compAVL = avl2020_1.compBusca;
                    		buscarPrint(bst2020_1.buscar(codBuscar));
                            compBST = bst2020_1.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2020_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20202:
                    		buscarPrint(avl2020_2.buscar(codBuscar));
                            compAVL = avl2020_2.compBusca;
                    		buscarPrint(bst2020_2.buscar(codBuscar));
                            compBST = bst2020_2.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2020_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20211:
                    		buscarPrint(avl2021_1.buscar(codBuscar));
                            compAVL = avl2021_1.compBusca;
                    		buscarPrint(bst2021_1.buscar(codBuscar));
                            compBST = bst2021_1.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2021_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20212:
                    		buscarPrint(avl2021_2.buscar(codBuscar));
                            compAVL = avl2021_2.compBusca;
                    		buscarPrint(bst2021_2.buscar(codBuscar));
                            compBST = bst2021_2.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2021_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20221:
                    		buscarPrint(avl2022_1.buscar(codBuscar));
                            compAVL = avl2022_1.compBusca;
                    		buscarPrint(bst2022_1.buscar(codBuscar));
                            compBST = bst2022_1.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2022_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20222:
                    		buscarPrint(avl2022_2.buscar(codBuscar));
                            compAVL = avl2022_2.compBusca;
                    		buscarPrint(bst2022_2.buscar(codBuscar));
                            compBST = bst2022_2.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2022_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    				
                    	case 20231:
                    		buscarPrint(avl2023_1.buscar(codBuscar));
                            compAVL = avl2023_1.compBusca;
                    		buscarPrint(bst2023_1.buscar(codBuscar));
                            compBST = bst2023_1.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2023_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20232:
                    		buscarPrint(avl2023_2.buscar(codBuscar));
                            compAVL = avl2023_2.compBusca;
                    		buscarPrint(bst2023_2.buscar(codBuscar));
                            compBST = bst2023_2.getCompBusca();
                            System.out.println("Quantidade de comparações para Planílha 2023_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	default:
                    		break;
                    }
                    break;
                    
                case 3: // Remover
                    System.out.println("Ano que deseja remover (ex: 20221 -> primeiro semestre de 2021): ");
                    int anoRemover = s.nextInt();
                    System.out.println("Codigo da escola: ");
                    int codRemover = s.nextInt();
                    
                    switch (anoRemover) {
                    	case 20191:
                    		avl2019_1.remover(codRemover);
                    		bst2019_1.remover(codRemover);
                            compAVL = avl2019_1.compRemocao;
                            compBST = bst2019_1.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2019_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20192:
                    		avl2019_2.remover(codRemover);
                    		bst2019_2.remover(codRemover);
                            compAVL = avl2019_2.compRemocao;
                            compBST = bst2019_2.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2019_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20201:
                    		avl2020_1.remover(codRemover);
                    		bst2020_1.remover(codRemover);
                            compAVL = avl2020_1.compRemocao;
                            compBST = bst2020_1.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2020_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20202:
                    		avl2020_2.remover(codRemover);
                    		bst2020_2.remover(codRemover);
                            compAVL = avl2020_2.compRemocao;
                            compBST = bst2020_2.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2020_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20211:
                    		avl2021_1.remover(codRemover);
                    		bst2021_1.remover(codRemover);
                            compAVL = avl2021_1.compRemocao;
                            compBST = bst2021_1.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2021_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20212:
                    		avl2021_2.remover(codRemover);
                    		bst2021_2.remover(codRemover);
                            compAVL = avl2021_2.compRemocao;
                            compBST = bst2021_2.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2021_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20221:
                    		avl2022_1.remover(codRemover);
                    		bst2022_1.remover(codRemover);
                            compAVL = avl2022_1.compRemocao;
                            compBST = bst2022_1.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2022_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20222:
                    		avl2022_2.remover(codRemover);
                    		bst2022_2.remover(codRemover);
                            compAVL = avl2022_2.compRemocao;
                            compBST = bst2022_2.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2022_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    				
                    	case 20231:
                    		avl2023_1.remover(codRemover);
                    		bst2023_1.remover(codRemover);
                            compAVL = avl2023_1.compRemocao;
                            compBST = bst2023_1.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2023_1:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	case 20232:
                    		avl2023_2.remover(codRemover);
                    		bst2023_2.remover(codRemover);
                            compAVL = avl2023_2.compRemocao;
                            compBST = bst2023_2.getCompRemocao();
                            System.out.println("Quantidade de comparações para Planílha 2023_2:\nBST: " + compBST + "\nAVL: " + compAVL);
                    		break;
                    		
                    	default:
                    		break;
                    }
                    break;
               
                case 4: // Análise
          
                	System.out.println("Ano    |  Inscritos no Fundamental 2 |  Inscritos Ensino Médio | Evasão | %Evasão");
                	avl2019_1.calcularArvore();
                 	double evasao2019_1 = (avl2019_1.somaFund-avl2019_1.somaMedio);
                 	evasao2019_1 = evasao2019_1/avl2019_1.somaFund*100;
                    System.out.println("2019/1 |           " + avl2019_1.somaFund + "           |         " + avl2019_1.somaMedio + "         | " + (avl2019_1.somaFund-avl2019_1.somaMedio) + " | "+ evasao2019_1+"%");
                    
                    avl2019_2.calcularArvore();
                 	double evasao2019_2 = (avl2019_2.somaFund-avl2019_2.somaMedio);
                 	evasao2019_2 = evasao2019_2/avl2019_2.somaFund*100;
                    System.out.println("2019/2 |           " + avl2019_2.somaFund + "           |         " + avl2019_2.somaMedio + "         | " + (avl2019_2.somaFund-avl2019_2.somaMedio) + " | "+ evasao2019_2+"%");
                    
                    avl2020_1.calcularArvore();
                 	double evasao2020_1 = (avl2020_1.somaFund-avl2020_1.somaMedio);
                 	evasao2020_1 = evasao2020_1/avl2020_1.somaFund*100;
                    System.out.println("2020/1 |           " + avl2020_1.somaFund + "           |         " + avl2020_1.somaMedio + "         | " + (avl2020_1.somaFund-avl2020_1.somaMedio) + " | "+ evasao2020_1+"%");
                    
                    avl2020_2.calcularArvore();
                 	double evasao2020_2 = (avl2020_2.somaFund-avl2020_2.somaMedio);
                 	evasao2020_2 = evasao2020_2/avl2020_2.somaFund*100;
                    System.out.println("2020/2 |           " + avl2020_2.somaFund + "           |         " + avl2020_2.somaMedio + "         | " + (avl2020_2.somaFund-avl2020_2.somaMedio) + " | "+ evasao2020_2+"%");
                    
                    avl2021_1.calcularArvore();
                 	double evasao2021_1 = (avl2021_1.somaFund-avl2021_1.somaMedio);
                 	evasao2021_1 = evasao2021_1/avl2021_1.somaFund*100;
                    System.out.println("2021/1 |           " + avl2021_1.somaFund + "           |         " + avl2021_1.somaMedio + "         | " + (avl2021_1.somaFund-avl2021_1.somaMedio) + " | "+ evasao2021_1+"%");
                    
                    avl2021_2.calcularArvore();
                 	double evasao2021_2 = (avl2021_2.somaFund-avl2021_2.somaMedio);
                 	evasao2021_2 = evasao2021_2/avl2021_2.somaFund*100;
                    System.out.println("2021/2 |           " + avl2021_2.somaFund + "           |         " + avl2021_2.somaMedio + "         | " + (avl2021_2.somaFund-avl2021_2.somaMedio) + " | "+ evasao2021_2+"%");
                    
                    avl2022_1.calcularArvore();
                 	double evasao2022_1 = (avl2022_1.somaFund-avl2022_1.somaMedio);
                 	evasao2022_1 = evasao2022_1/avl2022_1.somaFund*100;
                    System.out.println("2022/1 |           " + avl2022_1.somaFund + "           |         " + avl2022_1.somaMedio + "         | " + (avl2022_1.somaFund-avl2022_1.somaMedio) + " | "+ evasao2022_1+"%");
                    
                    avl2022_2.calcularArvore();
                 	double evasao2022_2 = (avl2022_2.somaFund-avl2022_2.somaMedio);
                 	evasao2022_2 = evasao2022_2/avl2022_2.somaFund*100;
                    System.out.println("2022/2 |           " + avl2022_2.somaFund + "           |         " + avl2022_2.somaMedio + "         | " + (avl2022_2.somaFund-avl2022_1.somaMedio) + "  | "+ evasao2022_2+"%");
                    
                    avl2023_1.calcularArvore();
                 	double evasao2023_1 = (avl2023_1.somaFund-avl2023_1.somaMedio);
                 	evasao2023_1 = evasao2023_1/avl2023_1.somaFund*100;
                    System.out.println("2023/1 |           " + avl2023_1.somaFund + "           |         " + avl2023_1.somaMedio + "         | " + (avl2023_1.somaFund-avl2023_1.somaMedio) + " | "+ evasao2023_1+"%");
                    
                    avl2023_2.calcularArvore();
                 	double evasao2023_2 = (avl2023_2.somaFund-avl2023_2.somaMedio);
                 	evasao2023_2 = evasao2023_2/avl2023_2.somaFund*100;
                    System.out.println("2023/2 |           " + avl2023_2.somaFund + "           |         " + avl2023_2.somaMedio + "         | " + (avl2023_2.somaFund-avl2023_2.somaMedio) + " | "+ evasao2023_2+"%");

                    break;
                    
                default: // Sair
                    break;
            }
        }
    }
}
