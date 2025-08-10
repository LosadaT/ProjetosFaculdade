//Felipe Viviani Schulze  – 10417996
//Francisco Losada Totaro - 10364673
#include <ctype.h>
#include <locale.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define MAX_LINE_LENGTH 1024

// Define um struct com todas as informações de um compromisso
struct Compromisso {
  int ano;
  int mes;
  int dia;
  int hora;
  int minuto;
  int duracao;
  int prioridade;
  char nome[50];
  char descricao[200];
  char local[50];
};

// Função de comparação por qsort para data
int compararCompromissosData(const void *a, const void *b) {
  struct Compromisso *compromissoA = (struct Compromisso *)a;
  struct Compromisso *compromissoB = (struct Compromisso *)b;

  // Comparação por ano
  if (compromissoA->ano != compromissoB->ano)
    return compromissoA->ano - compromissoB->ano;

  // Comparação por mês
  if (compromissoA->mes != compromissoB->mes)
    return compromissoA->mes - compromissoB->mes;

  // Comparação por dia
  if (compromissoA->dia != compromissoB->dia)
    return compromissoA->dia - compromissoB->dia;

  return 0;
}
// Função de comparação por qsort para data e horario
int compararCompromissosDataHorario(const void *a, const void *b) {
  struct Compromisso *compromissoA = (struct Compromisso *)a;
  struct Compromisso *compromissoB = (struct Compromisso *)b;

  // Comparação por ano
  if (compromissoA->ano != compromissoB->ano)
    return compromissoA->ano - compromissoB->ano;

  // Comparação por mês
  if (compromissoA->mes != compromissoB->mes)
    return compromissoA->mes - compromissoB->mes;

  // Comparação por dia
  if (compromissoA->dia != compromissoB->dia)
    return compromissoA->dia - compromissoB->dia;

  // Comparação por hora
  if (compromissoA->hora != compromissoB->hora)
    return compromissoA->hora - compromissoB->hora;

  // Comparação por minuto
  if (compromissoA->minuto != compromissoB->minuto)
    return compromissoA->minuto - compromissoB->minuto;

  return 0;
}
// Função de comparação por qsort para data e prioridade
int compararCompromissosDataPrioridade(const void *a, const void *b) {
  struct Compromisso *compromissoA = (struct Compromisso *)a;
  struct Compromisso *compromissoB = (struct Compromisso *)b;

  // Comparação por ano
  if (compromissoA->ano != compromissoB->ano)
    return compromissoA->ano - compromissoB->ano;

  // Comparação por mês
  if (compromissoA->mes != compromissoB->mes)
    return compromissoA->mes - compromissoB->mes;

  // Comparação por dia
  if (compromissoA->dia != compromissoB->dia)
    return compromissoA->dia - compromissoB->dia;

  // Comparação por prioridade
  if (compromissoA->prioridade != compromissoB->hora)
    return compromissoA->prioridade - compromissoB->prioridade;

  return 0;
}

// Função de comparação para qsort por local, prioridade e duração
int compararCompromissosLocalPrioridadeDuracao(const void *a, const void *b) {
  struct Compromisso *compromissoA = (struct Compromisso *)a;
  struct Compromisso *compromissoB = (struct Compromisso *)b;
  int i;
  // transformar string local em minuscula
  for (i = 0; compromissoA->local[i]; i++) {
    compromissoA->local[i] = tolower(compromissoA->local[i]);
  }

  for (i = 0; compromissoB->local[i]; i++) {
    compromissoB->local[i] = tolower(compromissoB->local[i]);
  }
  
  // Comparação por local
  int comparacaoLocal = strcmp(compromissoB->local, compromissoA->local);
  if (comparacaoLocal != 0)
    return comparacaoLocal;

  // Comparação por prioridade
  if (compromissoA->prioridade != compromissoB->prioridade)
    return compromissoB->prioridade - compromissoA->prioridade;

  // Comparação por duração
  return compromissoB->duracao - compromissoA->duracao;

  return 0;
}

// Funcao para adicionar compromisso
void adicionarCompromisso(const char *nomeEntrada, struct Compromisso novoCompromisso) {
  FILE *arquivo = fopen("entrada.csv", "a");
  if (arquivo == NULL) {
    printf("Erro ao abrir o arquivo de entrada.\n");
    return;
  }
  fprintf(arquivo, "%d;%d;%d;%d;%d;%d;%d;%s;%s;%s\n", novoCompromisso.ano,
          novoCompromisso.mes, novoCompromisso.dia, novoCompromisso.hora,
          novoCompromisso.minuto, novoCompromisso.duracao,
          novoCompromisso.prioridade, novoCompromisso.nome,
          novoCompromisso.descricao, novoCompromisso.local);
  fclose(arquivo);
}

// estava com problema que a funcao adicionar compromisso precisava de uma liha
// em branco no arquivo, e a funcao eliminar linha excluia essa linha em branco,
// por isso no final dessa, adicionamos uma linha em branco
void eliminarLinha(const char *nomeArquivo) {
  FILE *arquivo = fopen(nomeArquivo, "r+");
  if (arquivo == NULL) {
    printf("Erro ao abrir o arquivo.\n");
    return;
  }

  fseek(arquivo, 0, SEEK_END);
  long tamanho = ftell(arquivo);
  if (tamanho <= 0) {
    fclose(arquivo);
    return;
  }

  long posicao = tamanho - 2;
  while (posicao > 0) {
    fseek(arquivo, posicao, SEEK_SET);
    if (fgetc(arquivo) == '\n') {
      ftruncate(fileno(arquivo), posicao);
      break;
    }
    posicao--;
  }
  fprintf(arquivo,
          "\n"); // estava com problema que a funcao adicionar compromisso
                 // precisava de uma liha em branco no arquivo, e a funcao
                 // eliminar linha excluia essa linha em branco, por isso no
                 // final dessa, adicionamos uma linha em branco
  fclose(arquivo);
}

int main() {
  setlocale(LC_ALL, "Portuguese");
  while (true) {
    FILE *arquivoEntrada, *arquivoSaida;
    char linha[MAX_LINE_LENGTH];
    struct Compromisso *compromissos = NULL;
    int numCompromissos = 0;
    int capacidadeCompromissos = 0;

    // Abrir arquivo de entrada
    arquivoEntrada = fopen("entrada.csv", "r");
    if (arquivoEntrada == NULL) {
      printf("Erro ao abrir o arquivo de entrada.\n");
      return 1;
    }

    // Ler e armazenar os compromissos
    while (fgets(linha, MAX_LINE_LENGTH, arquivoEntrada) != NULL) {
      if (numCompromissos >= capacidadeCompromissos) {
        capacidadeCompromissos += 50;
        compromissos = realloc(compromissos, capacidadeCompromissos *
                                                 sizeof(struct Compromisso));
        if (compromissos == NULL) {
          printf("Erro ao alocar memória.\n");
          return 1;
        }
      }
      sscanf(linha, "%d;%d;%d;%d;%d;%d;%d;%99[^;];%199[^;];%99[^\n]",
             &compromissos[numCompromissos].ano,
             &compromissos[numCompromissos].mes,
             &compromissos[numCompromissos].dia,
             &compromissos[numCompromissos].hora,
             &compromissos[numCompromissos].minuto,
             &compromissos[numCompromissos].duracao,
             &compromissos[numCompromissos].prioridade,
             compromissos[numCompromissos].nome,
             compromissos[numCompromissos].descricao,
             compromissos[numCompromissos].local);
      numCompromissos++;
    }
    fclose(arquivoEntrada);

    // menu
    int opcao;
    printf("Menu: \n");
    printf("1. Adicionar compromisso\n");
    printf("2. Remover compromisso\n");
    printf("3. Ordenar por data\n");
    printf("4. Ordenar por data e horario\n");
    printf("5. Ordenar por data e prioridade\n");
    printf("6. Ordenar por local, prioridade e duração (decrescente)\n");
    printf("0. Fechar programa\n");
    printf("opção: ");
    scanf("%d", &opcao);

    if (opcao == 1) {
      // Adicionar compromisso
      struct Compromisso novoCompromisso;
      printf("Digite os detalhes do novo compromisso:\n");
      printf("Ano: ");
      scanf("%d", &novoCompromisso.ano);
      printf("Mês: ");
      scanf("%d", &novoCompromisso.mes);
      while (novoCompromisso.mes > 12 || novoCompromisso.mes < 1) {
        printf("Mês invalido, coloque novamente: ");
        scanf("%d", &novoCompromisso.mes);
      }
      printf("Dia: ");
      scanf("%d", &novoCompromisso.dia);
      while (novoCompromisso.dia > 31 || novoCompromisso.dia < 1) {
        printf("Dia invalido, coloque novamente: ");
        scanf("%d", &novoCompromisso.dia);
      }
      printf("Hora: ");
      scanf("%d", &novoCompromisso.hora);
      while (novoCompromisso.hora > 23 || novoCompromisso.hora < 0) {
        printf("Hora invalida, coloque novamente: ");
        scanf("%d", &novoCompromisso.hora);
      }
      printf("Minuto: ");
      scanf("%d", &novoCompromisso.minuto);
      while (novoCompromisso.minuto > 59 || novoCompromisso.minuto < 0) {
        printf("Minuto invalido, coloque novamente: ");
        scanf("%d", &novoCompromisso.minuto);
      }
      printf("Duração: ");
      scanf("%d", &novoCompromisso.duracao);
      while (novoCompromisso.duracao < 0) {
        printf("Duração invalida, coloque novamente: ");
        scanf("%d", &novoCompromisso.duracao);
      }
      printf("Prioridade: ");
      scanf("%d", &novoCompromisso.prioridade);
      while (novoCompromisso.prioridade < 1 || novoCompromisso.prioridade > 5) {
        printf("Prioridade invalida, coloque novamente: ");
        scanf("%d", &novoCompromisso.prioridade);
      }
      printf("Nome: ");
      scanf("%s", novoCompromisso.nome);
      printf("Descrição: ");
      scanf("%s", novoCompromisso.descricao);
      printf("Local: ");
      scanf("%s", novoCompromisso.local);
      adicionarCompromisso("entrada.csv", novoCompromisso);
    }

    if (opcao == 2) {
      eliminarLinha("entrada.csv");
    }

    if (opcao == 3) {
      // Ordenar por data
      qsort(compromissos, numCompromissos, sizeof(struct Compromisso), compararCompromissosData);

      // Abrir arquivo de saída
      arquivoSaida = fopen("saida.csv", "w");
      if (arquivoSaida == NULL) {
        printf("Erro ao abrir o arquivo de saída.\n");
        return 1;
      }
      int i;
		
      // Escrever os compromissos ordenados no arquivo de saída
      for (i = 0; i < numCompromissos; i++) {
        fprintf(arquivoSaida, "%d;%d;%d;%d;%d;%d;%d;%s;%s;%s\n",
                compromissos[i].ano, compromissos[i].mes, compromissos[i].dia,
                compromissos[i].hora, compromissos[i].minuto,
                compromissos[i].duracao, compromissos[i].prioridade,
                compromissos[i].nome, compromissos[i].descricao,
                compromissos[i].local);
      }

      fclose(arquivoSaida);
      printf("Compromissos ordenados por data e salvos em 'saida.csv'\n\n");
    }
    if (opcao == 4) {
      qsort(compromissos, numCompromissos, sizeof(struct Compromisso), compararCompromissosDataHorario);

      // Abrir arquivo de saída
      arquivoSaida = fopen("saida.csv", "w");
      if (arquivoSaida == NULL) {
        printf("Erro ao abrir o arquivo de saída.\n");
        return 1;
      }
      int i;
	
      // Escrever os compromissos ordenados no arquivo de saída
      for (i = 0; i < numCompromissos; i++) {
        fprintf(arquivoSaida, "%d;%d;%d;%d;%d;%d;%d;%s;%s;%s\n",
                compromissos[i].ano, compromissos[i].mes, compromissos[i].dia,
                compromissos[i].hora, compromissos[i].minuto,
                compromissos[i].duracao, compromissos[i].prioridade,
                compromissos[i].nome, compromissos[i].descricao,
                compromissos[i].local);
      }

      fclose(arquivoSaida);
      printf("Compromissos ordenados por data e horario e salvos em 'saida.csv'\n\n");
    }

    if (opcao == 5) {
      qsort(compromissos, numCompromissos, sizeof(struct Compromisso), compararCompromissosDataPrioridade);

      // Abrir arquivo de saída
      arquivoSaida = fopen("saida.csv", "w");
      if (arquivoSaida == NULL) {
        printf("Erro ao abrir o arquivo de saída.\n");
        return 1;
      }
      int i;

      // Escrever os compromissos ordenados no arquivo de saída
      for (i = 0; i < numCompromissos; i++) {
        fprintf(arquivoSaida, "%d;%d;%d;%d;%d;%d;%d;%s;%s;%s\n",
                compromissos[i].ano, compromissos[i].mes, compromissos[i].dia,
                compromissos[i].hora, compromissos[i].minuto,
                compromissos[i].duracao, compromissos[i].prioridade,
                compromissos[i].nome, compromissos[i].descricao,
                compromissos[i].local);
      }

      fclose(arquivoSaida);
      printf("Compromissos ordenados por data e prioridade e salvos em 'saida.csv'\n\n");
    }
    if (opcao == 6) {
      qsort(compromissos, numCompromissos, sizeof(struct Compromisso), compararCompromissosLocalPrioridadeDuracao);

      // Abrir arquivo de saída
      arquivoSaida = fopen("saida.csv", "w");
      if (arquivoSaida == NULL) {
        printf("Erro ao abrir o arquivo de saída.\n");
        return 1;
      }
      int i;

      // Escrever os compromissos ordenados no arquivo de saída
      for (i = 0; i < numCompromissos; i++) {
        fprintf(arquivoSaida, "%d;%d;%d;%d;%d;%d;%d;%s;%s;%s\n",
                compromissos[i].ano, compromissos[i].mes, compromissos[i].dia,
                compromissos[i].hora, compromissos[i].minuto,
                compromissos[i].duracao, compromissos[i].prioridade,
                compromissos[i].nome, compromissos[i].descricao,
                compromissos[i].local);
      }

      fclose(arquivoSaida);
      printf("Compromissos ordenados por local, prioridade e duracao e salvos em 'saida.csv'\n\n");
    }

    if (opcao == 0) {
      break;
    }
  }
  return 0;
}
