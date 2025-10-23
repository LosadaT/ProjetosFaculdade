/*
		Trabalho de Sistemas Operacionais

	Integrantes:
	- Felipe Viviani Schulze   - 10417996
	- Francisco Losada Totaro  - 10364673
	- Rodrigo Nascimento Tomaz - 10418449
*/

#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <time.h>
#include <stdint.h>
#include <unistd.h>
#include <semaphore.h>
#include <stdbool.h>

#define NUM_PEDIDOS 10 // Determina quantos pedidos a cozinha pode preparar ao mesmo tempo
int pedido_livre = 0; // Determina qual posição da lista de pedidos está livre para receber um pedido
int pedido_pendente = 0; // Determina qual posição da lista está o último pedido não preparado
int pedido_pronto = 0; // Determina qual posição da lista está o último pedido a ser entregue
int pedidos[NUM_PEDIDOS]; // Contém todos os pedidos pendentes a serem preparados
int pedidos_prontos[NUM_PEDIDOS]; // Contém todos os pedidos prontos para entregar aos clientes

// Inicialização de semáfaros
sem_t semafaro_livre;
sem_t semafaro_pendente_garcom;
sem_t semafaro_pendente_consumidor;
sem_t semafaro_pronto;

// Inicialização de threads
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

void* funcCozinheiro(void* arg)
{
	intptr_t id = (intptr_t)arg;

	while(true) {
		// O cozinheiro espera:
		// - Garçons não estarem usando a lista "pedidos"
		// - Consumidores não estarem usando a lista "pedido_prontos"
		sem_wait(&semafaro_pendente_garcom);
		sem_wait(&semafaro_pendente_consumidor);

		// ele pega o pedido na posição atual e o prepara (simulado pelo sleep)
		int pedido = pedidos[pedido_pendente];
		sleep(pedido);

		pthread_mutex_lock(&mutex);

		// O cozinheiro move o pedido para a lista de pedidos prontos
		pedidos_prontos[pedido_pendente] = pedidos[pedido_pendente];
		printf("Cozinheiro %ld: Prato %d | Posição %d\n", id, pedido, pedido_pendente);
		pedido_pendente = (pedido_pendente + 1) % NUM_PEDIDOS;

		pthread_mutex_unlock(&mutex);

        // O cozinheiro envia para os garçons e para os consumidores que acabou de utilizar a sessão crítica
		sem_post(&semafaro_livre);
		sem_post(&semafaro_pronto);
}

	return NULL;
}

void* funcGarcom(void* arg)
{
	//srand(time(NULL));

	intptr_t id = (intptr_t) arg;
	// Determina a quantidade total de pedidos que serão recebidos deste garçom nesta execução 
	int quantPedidos = rand() % 100 + 10;
	
	// printf("\nNum. pedidos: %d\n", quantPedidos);

	for (int i = 0; i < quantPedidos; i++) {
        // O garçom espera os cozinheiros não estarem usando a lista "pedidos"
		sem_wait(&semafaro_livre);
		
		//srand(time(NULL));
		
		pthread_mutex_lock(&mutex);
		
		pedidos[pedido_livre] = rand() % 5 + 1; // Adiciona um pedido à lista global de pedidos
		
		printf("Garçom %ld:     Prato %d | Posição %d\n", id, pedidos[pedido_livre], pedido_livre);
		
		pedido_livre = (pedido_livre + 1) % NUM_PEDIDOS;
		pthread_mutex_unlock(&mutex);

        // O garçom envia para os cozinheiros que acabou de utilizar a sessão crítica
		sem_post(&semafaro_pendente_garcom);

		sleep(1);
	}

	return NULL;
}

void* funcConsumidor(void* arg)
{
	intptr_t id = (intptr_t) arg;

	while(true) {
        // O consumidor espera os cozinheiros não estarem usando a lista "pedido_prontos"
		sem_wait(&semafaro_pronto);

		pthread_mutex_lock(&mutex);
		
        // O consumidor consome o pedido da lista "pedido_pronto"
		int pedido_consumido = pedidos_prontos[pedido_pronto]; 
		printf("Consumidor %ld: Prato %d | Posição %d\n", id, pedido_consumido, pedido_pronto);

		pedido_pronto = (pedido_pronto + 1) % NUM_PEDIDOS;
		
		pthread_mutex_unlock(&mutex);

        // O garçom envia para os cozinheiros que acabou de utilizar a sessão crítica
		sem_post(&semafaro_pendente_consumidor);

		sleep(5);
	}

	return NULL;
}

int main()
{
	srand(time(NULL));
    // Inicializações
	int num_garcons = 5;
	int num_cozinheiros = 3;
	int num_consumidores = 10;

	pthread_t garcons[num_garcons];
	pthread_t cozinheiros[num_cozinheiros];
	pthread_t consumidores[num_consumidores];

	sem_init(&semafaro_livre, 0, NUM_PEDIDOS);
	sem_init(&semafaro_pendente_garcom, 0, 0);
	sem_init(&semafaro_pendente_consumidor, 0, NUM_PEDIDOS);
	sem_init(&semafaro_pronto, 0, 0);

	for (int i = 0; i < num_garcons; i++) {
		pthread_create(&garcons[i], NULL, funcGarcom, (void*)i);
	}

	for (int i = 0; i < num_cozinheiros; i++) {
		pthread_create(&cozinheiros[i], NULL, funcCozinheiro, (void*)i);
	}

	for (int i = 0; i < num_consumidores; i++) {
		pthread_create(&consumidores[i], NULL, funcConsumidor, (void*)i);
	}
	
	for (int i = 0; i < num_garcons; i++) {
		pthread_join(garcons[i], NULL); // A main espera a thread terminar
	}

	for (int i = 0; i < num_cozinheiros; i++) {
		pthread_join(&cozinheiros[i], NULL);
	}

	for (int i = 0; i < num_consumidores; i++) {
		pthread_join(&consumidores[i], NULL);
	}

	return 0;
}
















