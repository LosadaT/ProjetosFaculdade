/*
        TRABALHO I DE PROJETO E ANÁLISE DE ALGORITMOS II
                        Turma 04G
    
    Felipe Viviani Schulze   - 10417996
    Francisco Losada Totaro  - 10364673
    Rodrigo Nascimento Tomaz - 10418449
*/


#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_LENGHT 256

// Função para inverter o arquivo de resposta
// Fazemos isso pois o algoritmo devolve os passos na ordem contrária, e como o jogo "Resta Um"
// é simétrico, invertemos todas as linhas do arquivo verticalmente
void inverter_arquivo()
{
    // Abrimos o arquivo original e criamos um arquivo que conterá o original invertido
    FILE *arq_original = fopen("resta_um_saida.txt", "r");
    if (arq_original == NULL) {
        perror("Erro ao abrir o arquivo!\n");
        return;
    }

    FILE *novo_arq = fopen("novo_arquivo.txt", "w");
    if (novo_arq == NULL) {
        perror("Erro ao abrir o arquivo!\n");
        return;
    }

    // 
    fseek(arq_original, 0, SEEK_END);
    long posicao = ftell(arq_original);

    char linha[MAX_LENGHT];
    int i = 0;
    
    // Percorremos linha a linha, de trás para frente, do arquivo original, copiando-as no arquivo novo
    for (i = posicao - 1; i >= 0; i--) {
        fseek(arq_original, i, SEEK_SET);
        char c = fgetc(arq_original);
        if (c == '\n' || i == 0) {
            if (i != 0) {
                fgets(linha, MAX_LENGHT, arq_original);
                i--;
                fprintf(novo_arq, "%s", linha);
            } else {
                fgets(linha, MAX_LENGHT, arq_original);
                i--;
                fprintf(novo_arq, "%s", linha);
            }
        }
    }
    fclose(arq_original);
    fclose(novo_arq);

    // Deletamos o arquivo original e renomeamos o novo com o mesmo nome do original
    remove("resta_um_saida.txt");
    rename("novo_arquivo.txt", "resta_um_saida.txt");
}

// Função que imprime a matriz
void imprimir_matriz(int matriz[7][7]) {
    FILE *arq = fopen("resta_um_saida.txt", "a");
    if (arq == NULL) {
        perror("Erro ao abrir o arquivo!\n");
        return;
    }
    fprintf(arq, "# # # # # # # # #\n");
    fprintf(arq, "# # # %d %d %d # # #\n", matriz[0][2], matriz[0][3], matriz[0][4]);
    fprintf(arq, "# # # %d %d %d # # #\n", matriz[1][2], matriz[1][3], matriz[1][4]);
    fprintf(arq, "# %d %d %d %d %d %d %d #\n", matriz[2][0], matriz[2][1], matriz[2][2], matriz[2][3], matriz[2][4], matriz[2][5], matriz[2][6]);
    fprintf(arq, "# %d %d %d %d %d %d %d #\n", matriz[3][0], matriz[3][1], matriz[3][2], matriz[3][3], matriz[3][4], matriz[3][5], matriz[3][6]);
    fprintf(arq, "# %d %d %d %d %d %d %d #\n", matriz[4][0], matriz[4][1], matriz[4][2], matriz[4][3], matriz[4][4], matriz[4][5], matriz[4][6]);
    fprintf(arq, "# # # %d %d %d # # #\n", matriz[5][2], matriz[5][3], matriz[5][4]);
    fprintf(arq, "# # # %d %d %d # # #\n", matriz[6][2], matriz[6][3], matriz[6][4]);
    fprintf(arq, "# # # # # # # # #\n\n");

    fclose(arq);
}

// Função para verificar se a matriz está na posição final
bool verificar(int matriz[7][7])
{
    // Comparamos a matriz de input com a matriz abaixo para verificar se está na posição final
    int final[7][7] = {{2, 2, 0, 0, 0, 2, 2},
                       {2, 2, 0, 0, 0, 2, 2},
                       {0, 0, 0, 0, 0, 0, 0},
                       {0, 0, 0, 1, 0, 0, 0},
                       {0, 0, 0, 0, 0, 0, 0},
                       {2, 2, 0, 0, 0, 2, 2},
                       {2, 2, 0, 0, 0, 2, 2}};
	int i,j;
    for (i = 0; i < 7; i++) {
        for (j = 0; j < 7; j++) {
            if (matriz[i][j] != final[i][j]) {
                //printf("%d %d /", i, j);
                return false;
            }
        }
    }
    return true;
}

int resta_um(int matriz[7][7], int x, int y, int passos)
{
    // Verificamos se percorremos a matriz inteira sem realizar nenhum movimento
    // Caso esteja na posição final, começamos a imprimir as posições da matriz no arquivo
    if (x >= 7) {
        x = 0;
        y++;
    }
    if (y >= 7) {
        if (verificar(matriz)) {
            imprimir_matriz(matriz);
            printf("\n");
            return 1;
        }
        return 0;
    }
    if (passos >= 31) {
        if (verificar(matriz)) {
            imprimir_matriz(matriz);
            printf("\n");
            return 1;
        }
        return 0;
    }
    // Verificamos se a posição atual tem um pino
    if (matriz[x][y] == 1) {
        // Verificar todas as direções se temos um movimento válido para o pino atual
        if (x >= 2) {
            if (matriz[x-1][y] == 1 && matriz[x-2][y] == 0) {
                matriz[x][y] = 0;
                matriz[x-1][y] = 0;
                matriz[x-2][y] = 1;
                passos++;
                int res = resta_um(matriz, 0, 0, passos);
                passos--;
                matriz[x][y] = 1;
                matriz[x-1][y] = 1;
                matriz[x-2][y] = 0;
                if (res == 1) {
                    imprimir_matriz(matriz);
                    return 1;
                }
            }
        }

        if (x <= 4) {
            if (matriz[x+1][y] == 1 && matriz[x+2][y] == 0) {
                matriz[x][y] = 0;
                matriz[x+1][y] = 0;
                matriz[x+2][y] = 1;
                passos++;
                int res = resta_um(matriz, 0, 0, passos);
                passos--;
                matriz[x][y] = 1;
                matriz[x+1][y] = 1;
                matriz[x+2][y] = 0;
                if (res == 1) {
                    imprimir_matriz(matriz);
                    return 1;
                }
            }
        }

        if (y >= 2) {
            if (matriz[x][y-1] == 1 && matriz[x][y-2] == 0) {
                matriz[x][y] = 0;
                matriz[x][y-1] = 0;
                matriz[x][y-2] = 1;
                passos++;
                int res = resta_um(matriz, 0, 0, passos);
                passos--;
                matriz[x][y] = 1;
                matriz[x][y-1] = 1;
                matriz[x][y-2] = 0;
                if (res == 1) {
                    imprimir_matriz(matriz);
                    return 1;
                }
            }
        }

        if (y <= 4) {
            if (matriz[x][y+1] == 1 && matriz[x][y+2] == 0) {
                matriz[x][y] = 0;
                matriz[x][y+1] = 0;
                matriz[x][y+2] = 1;
                passos++;
                int res = resta_um(matriz, 0, 0, passos);
                passos--;
                matriz[x][y] = 1;
                matriz[x][y+1] = 1;
                matriz[x][y+2] = 0;
                if (res == 1) {
                    imprimir_matriz(matriz);
                    return 1;
                }
            }
        }
    }
    // Caso estejamos voltando os passos da solução, retornamos 1, senão retornamos 0
    if (resta_um(matriz, x+1, y, passos) == 1) {
        return 1;
    }
    return 0;
}

int main()
{
    int tabuleiro[7][7] = {{2, 2, 1, 1, 1, 2, 2},
                           {2, 2, 1, 1, 1, 2, 2},
                           {1, 1, 1, 1, 1, 1, 1},
                           {1, 1, 1, 0, 1, 1, 1},
                           {1, 1, 1, 1, 1, 1, 1},
                           {2, 2, 1, 1, 1, 2, 2},
                           {2, 2, 1, 1, 1, 2, 2}};

    printf("A solucao pode demorar alguns segundos...\n");
    remove("resta_um_saida.txt");
    resta_um(tabuleiro, 0, 0, 0);
    inverter_arquivo();
    printf("\nPrograma finalizado!");
}
