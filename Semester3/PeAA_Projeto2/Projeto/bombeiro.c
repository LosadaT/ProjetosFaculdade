//Felipe Viviani Schulze   - 10417996
//Francisco Losada Totaro  - 10364673
//Rodrigo Nascimento Tomaz - 10418449
//Link Video: https://youtu.be/TEfhnWsw850
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define INF 123456

int main()
{
    FILE* arq;
    int tamBuffer = 255;
    char buffer[tamBuffer];
    //Abrir Arquivo
    arq = fopen("bombeiros.txt", "r");
    if (arq == NULL) {
        printf("Erro ao ler o arquivo\n");
        return 1;
    }

    int incendio = fgets(buffer, tamBuffer, arq)[0] - '0'; // Ler a esquina em que ocorre o incendio
    int quantEsquinas = fgets(buffer, tamBuffer, arq)[0] - '0'; // Ler a quantidade de esquinas
    //Preencher vetor com valores "infinitos"
    int *esquinas = malloc(quantEsquinas * quantEsquinas * sizeof(int));
    int i,j;
    for (i = 0; i < quantEsquinas; i++) {
        for (j = 0; j < quantEsquinas; j++) {
            esquinas[i * quantEsquinas + j] = INF;
        }
    }

    while (fgets(buffer, tamBuffer, arq)[0] - '0' != 0) { // enquanto não encontrar linha so com "0" continuar lendo o arquivo
        int ori = buffer[0] - '0'; // ler a esquina origem
        int des = buffer[2] - '0'; // ler a esquina destino
        esquinas[(ori-1) * quantEsquinas + (des-1)] = buffer[4] - '0'; // ler o tempo
    }

    int *tempo_esquinas = malloc(quantEsquinas * sizeof(int));
    int *caminho_esquinas = malloc(quantEsquinas * sizeof(int));
    int e;
    for (e = 0; e < quantEsquinas; e++) {
        tempo_esquinas[e] = INF;
        caminho_esquinas[e] = -1;
    }
    tempo_esquinas[0] = 0; // Tempo gasto para ir da esquina 1 ate a esquina 1
    int des,ori;
    for (des = 0; des < quantEsquinas; des++) {
        for (ori = 0; ori < quantEsquinas; ori++) {
            if (tempo_esquinas[des] > tempo_esquinas[ori] + esquinas[ori * quantEsquinas + des]) {
                tempo_esquinas[des] = tempo_esquinas[ori] + esquinas[ori * quantEsquinas + des];
                caminho_esquinas[des] = ori+1;
            }
        }
    }
    for (des = 0; des < quantEsquinas; des++) {
        for (ori = 0; ori < quantEsquinas; ori++) {
            if (tempo_esquinas[des] > tempo_esquinas[ori] + esquinas[ori * quantEsquinas + des]) {
                tempo_esquinas[des] = tempo_esquinas[ori] + esquinas[ori * quantEsquinas + des];
                caminho_esquinas[des] = ori+1;
            }
        }
    }
    // Inverte os indices dos caminhos
    int *caminho = malloc(quantEsquinas * sizeof(int));
    for (i = 0; i < quantEsquinas; i++) {
        int ind = caminho_esquinas[i];
        if (ind != -1) {
            caminho[ind-1] = i+1;
        }
    }

    // Mostrar o resultado
    printf("Rota ate a esquina #%d: 1", incendio);
    int aux = 1;
    while (caminho[aux-1] != 3) {
        aux = caminho[aux-1];
        printf(" -> %d", aux);
    }
    printf(" -> %d\n", incendio);
    printf("Tempo calculado para rota: %d min", tempo_esquinas[incendio-1]);

    free(caminho);
    free(caminho_esquinas);
    free(tempo_esquinas);
    free(esquinas);
    return 0;
}
