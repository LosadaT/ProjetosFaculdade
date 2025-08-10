//Felipe Viviani Schulze - 32336330
//Francisco Losada Totaro - 32345526
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <locale.h>
#include <ctype.h>

const int COMMAX = 1024;
const int ALTMAX = 768;

int pular_linha(FILE *arq){
    int c;
    
    while (c = fgetc(arq), c != '\n' && c != EOF);
    
    return c;
}

void codificar(int com, int alt, int im[][com]){   
    // PS: toda vez que dizermos "imagem", nos referimos à "im".
    
    // Verifica se a imagem tem dimensão 1x1
    if (alt == 1 && com == 1){
       // Então, verifica se esse pixel é branco ou preto
       if (im[0][0] == 1){
          printf("P");
       }
       if (im[0][0] == 0){
          printf("B");
       }
       // retorna a função pois a imagem foi codificada
       return;
    }
    
    // Pega o primeiro elemento da imagem e declara que é monocolor
    int test = im[0][0];
    int mono = 1;
    int i, j;
    // Iterar sobre todos os elementos da imagem enquanto ela for monocolor
    for (i = 0; i < alt && mono == 1; i++){
        for (j = 0; j < com && mono == 1; j++){
            // Verificar se esse elemento é diferente do primeiro elemento
            if (im[i][j] != test){
               // Definir a imagem como não monocolor, ou seja,
               // tem pixels brancos e pretos
               mono = 0;
            }
        }
    }
    
    // Verifica se a imagem é monocolor
    if (mono == 1){
       // Verifica se a imagem é preta ou branca olhando o primeiro pixel
       if (im[0][0] == 1){
          printf("P");
       }
       if (im[0][0] == 0){
          printf("B");
       }
       // retorna a função pois a imagem foi codificada
       return;
    }
    
    // Imprime "X" pois a imagem não é monocolor, portanto
    // precisa ser dividida
    printf("X");
    
    // Determina as dimensões das subdivisões da imagem
    int altcima = ceil((double)alt / 2);
    int comcima = ceil((double)com / 2);
    int altbaixo = floor((double)alt / 2);
    int combaixo = floor((double)com / 2);
    
    // Cria 4 arrays para guardarem as subdivisões da imagem
    int subdiv1[altcima][comcima];
    int subdiv2[altcima][combaixo];
    int subdiv3[altbaixo][comcima];
    int subdiv4[altbaixo][combaixo];
    // Iterar sobre todos os elementos da imagem
    for (i = 0; i < alt; i++){
        for (j = 0; j < com; j++){
            // Verifica a qual quadrante o píxel (j,i) pertence e
            // o coloca no array respectivo
            if (i < altcima && j < comcima){
               subdiv1[i][j] = im[i][j];
            }
            if (i < altcima && j >= comcima){
               subdiv2[i][j % comcima] = im[i][j];
            }
            if (i >= altcima && j < comcima){
               subdiv3[i % altcima][j] = im[i][j];
            }
            if (i >= altcima && j >= comcima){
               subdiv4[i % altcima][j % comcima] = im[i][j];
            }
        }
    }
    
    // Chama a função recursivamente em cada um dos quatro quadrantes,
    // verificando se eles tem tamanho de no mínimo 1x1
    codificar(comcima, altcima, subdiv1);
    if (combaixo >= 1){
       codificar(combaixo, altcima, subdiv2);
    }
    if (altbaixo >= 1){
       codificar(comcima, altbaixo, subdiv3);
    }
    if (combaixo >= 1 && altbaixo >= 1){
       codificar(combaixo, altbaixo, subdiv4);
    }
    
    // retorna a função pois a imagem foi codificada
    return;
}

int main(int argc, char* argv[]){
    // Permite o uso de acentos
    setlocale(LC_ALL, "Portuguese");
    printf("\n");
    
    // Verifica se há a quantidade de argumentos suficientes
    if (argc == 1){
       printf("Argumentos Insuficientes!\nUse '-?' ou '--help' para ajuda.\n");
       return 1;
    }
    
    // Pega o primeiro argumento e realiza a sua função
    char *arg1 = argv[1];
    
    // Imprimir mensagem de ajuda e terminar programa
    if (0 == strcmp(arg1, "-?") || 0 == strcmp(arg1, "--help")){
       printf("Uso: codificadorImagem [-? | -m | -f ARQ]\n");
       printf("Codifica imagens binárias dadas em arquivos PBM ou por dados informados manualmente.\n");
       printf("Argumentos:\n"
              "-?, --help  : apresenta essa orientação na tela.\n"
              "-m, --manual: ativa o modo de entrada manual, em que o usuário fornece todos os dados "
              "da imagem informando-os através do teclado.\n"
              "-f, --file  : considera a imagem representada no arquivo PBM (Portable Bitmap).\n");
       return 0;
    }
    
    // Seguir pelos inputs manuais do usuário e então terminar programa
    if (0 == strcmp(arg1, "-m") || 0 == strcmp(arg1, "--manual")){
       int alt, com;
       
       // Pega a altura e comprimento da imagem, verificando se não excedem o máximo
       printf("Digite a altura da imagem: ");
       scanf("%d", &alt);
       
       if (alt > ALTMAX || alt < 1){
          printf("Valor inserido para altura não é aceito por esse programa.\n"
                 "Altura Máxima = 768\n");
          return 2;
       }
       
       printf("Digite o comprimento da imagem: ");
       scanf("%d", &com);
       
       if (com > COMMAX || com < 1){
          printf("Valor inserido para comprimento não é aceito por esse programa.\n"
                 "Comprimento Máximo = 1024\n");
          return 2;
       }
       
       // Cria uma matriz com altura e comprimento definidos
       int ar[alt][com];
       int pixel = 2;
       int i,j;
       // Pega input de cada píxel do usuário e o salva na sua posição da matriz
       for (i = 0; i < alt; i++){
           for (j = 0; j < com; j++){
               do{
                  printf("Digite o píxel (0 ou 1) na posição (%d, %d): ", j, i);
                  scanf("%d", &pixel);
               } while(pixel != 1 && pixel != 0);
               ar[i][j] = pixel;
           }
       }
       
       // Chama a função codificar na matriz ar
       codificar(com, alt, ar);
       
       return 0;
    }
    
    if (0 == strcmp(arg1, "-f") || 0 == strcmp(arg1, "--file")){
       // Verifica se o número de argumentos está correto
       if (argc != 3){
          printf("Argumentos insuficientes para %s\n"
                 "Use '-?' ou '--help' para ajuda.\n", arg1);
          return 3;
       }
	   
	   // Pega o nome do arquivo e abre ele no modo leitura
	   const char *FILENAME = argv[2];
	   FILE *arquivo = fopen(FILENAME, "r");
	   
	   // Verifica se o arquivo foi aberto corretamente
	   if (arquivo == NULL){
          perror("Erro ao abrir o arquivo");
          return 4;
       }
	   
	   int i = 0;
	   char c;
	   char tipo_arq[256] = {0};
	   
	   // Verifica se a primeira do arquivo contém "P1", caso contrário termina 
       // o programa
	   c = fgetc(arquivo);
       while(c != EOF && c != '\n'){
             tipo_arq[i] = c;
             i++;
             c = fgetc(arquivo);
       }
       
       if (strcmp(tipo_arq, "P1") != 0){
          printf("Tipo de arquivo inválido!\n");
          return 5;
       }
       
       // Pula todas as linhas seguintes que começam com "#"
       c = fgetc(arquivo);
       if (c == '#'){
          do {
             pular_linha(arquivo);
             c = fgetc(arquivo);
          } while (c == '#');
       }
       ungetc(c, arquivo);
       
       
       char altcom[2];
       int com, alt;
   	    
   	   // Le o comprimento (x) e a altura (y) do arquivo
	   fscanf(arquivo, "%s", altcom);
	   com = atoi(altcom);
	   fscanf(arquivo, "%s", altcom);
	   alt = atoi(altcom);
	   
	   // Verifica se o comprimento e a altura estão dentro do limite máximo
	   if (com > COMMAX || com < 1){
          printf("Comprimento de imagem inválido!\n");
          return 6;
       }
       if (alt > ALTMAX || alt < 1){
          printf("Altura de imagem inválida!\n");
          return 6;
       }
	
	   char pixel;
	   int imagem[alt][com];
	   int x = 0, y = 0; 
	   
	   // Para todo número após as dimensões da imagem, lê somente os dígitos e
	   // os registram na matriz imagem
       while(alt > y && pixel != EOF){
             pixel = getc(arquivo);
		     if (isdigit(pixel) == 0){
                continue;
             }
		     imagem[y][x] = pixel - '0';
		     x++;
		     if (com == x){
			    y++;
			    x = 0;
		     }
       }
       
       // Verifica se a quantidade correta de píxels foram lidas, ignorando
       // quaisquer píxels extras que possam estar presentes no arquivo
       if (x != 0 || y != alt){
          printf("Número insuficiente de píxels no arquivo!");
          return 7;
       }
       
	   fclose(arquivo);
	   
	   // Passa a matriz imagem para ser codificada
	   printf("A imagem codificada é: ");
	   codificar(com, alt, imagem);
	   
	   // Retorna 0 pois terminou o programa
	   return 0;
    }
    
    // Caso o número de argumentos ou a quantidade de argumentos esteja incorreta,
    // imprime a seguinte mensagem e termina o programa
    printf("Argumentos inseridos desconhecidos\n"
           "Use '-?' ou '--help' para ajuda.\n");
    
    return 8;
}
