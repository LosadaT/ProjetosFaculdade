//Francisco Losada Totaro - 32345526
#include <stdio.h>
#include <locale.h>
#include <stdbool.h>
#include <math.h>

/*Função para mostrar um menu de ações para o usuario*/
void menu(){
	printf("\n ");
	printf("\nSelecione a ação desejada:");
	printf("\n1 - Calcular valor do polinômio");
	printf("\n2 - Calcular soma entre dois polinômios");
	printf("\n3 - Calcular mutiplicação entre dois polinômios");
	printf("\n0 - Sair");

/*Função para mostrar um menu de ações para o usuario*/
}
void menu2(){
	printf("\n1 - Utilizar polinômio criado no inicio do programa");
	printf("\n2 - Criar novo polinômio");
}
// função escolha de item menu2
int escolhaMenu2(){
	int escolha;
	printf("\nEscolha uma opção: ");
	scanf("%d", &escolha);
	while(escolha>2 || escolha<1){ //verificação
		printf("Opção não valida. Escolha: ");
		scanf("%d", &escolha);
	}
	printf("Opção escolhida: %d\n", escolha);
	printf(" \n");
	return escolha;
}
/*Função para a representação do polinomio, recebido em forma de vetor e formatado.*/
void representacao(int lista[], int tamanho){
	int i = 0;
	printf("P(x) = ");
	for (i = tamanho; i>=0; --i){ /*Formatação dos números no polinômio, sendo o ultimo termo do vetor, o primeiro representado. Sua posição é o mesmo valor da potencia*/
		if (lista[i] == 0){ /*Quando 0, pular o número*/
			printf("");
		}
		else if (i == tamanho && lista[i]==1){ 	/*Para o primeiro termo representado, não precisa do sinal de + */
			printf("x^%d",i);
		}
		else if (i == tamanho){ 	/*Para o primeiro termo representado, não precisa do sinal de + */
			printf("%dx^%d",lista[i],i);
		}
		else if (i==0 && lista[i]>0){ /*caso o número for maior que 0 e for o último valor do polinômio, que não é multiplicado por x*/
			printf("+%d",lista[i]);
		}
		else if (i==0 && lista[i]<0){ /*mesmo caso do anterio, só que menor que zero*/
			printf("%d",lista[i]);
		}
		else if (lista[i] == 1 && i==1){ //
			printf("+x");
		}
		else if(lista[i] == 1){	/*Quando 1, não mostrar o número, apenas o x^*/
			printf("+x^%d",i);
		}
		else if (lista[i]>=0 && i==1){
			printf("+%dx",lista[i]);
		}
		else if (lista[i]<0 && i==1){
			printf("%dx",lista[i]);
		}
		else if(lista[i]>=0){ /*caso o número for maior que 0*/
			printf("+%dx^%d",lista[i],i);
		}
		else if(lista[i]<0){ /*caso o número for menor que 0*/
			printf("%dx^%d",lista[i],i);
		}
	}
}
//grau do polinomio
int grau(){
	int grau;
	printf("Grau do polinômio: ");
	scanf("%d",&grau);
	while (grau<1){ //verificação do grau
		printf("Valor não valido. Grau do polinômio: ");
		scanf("%d",&grau);
	}
	return grau;
}
//Função para gerar polinomio
void gerarPoli(int lista[], int grau){
	int i, tamanho;
	tamanho = grau+1; 
	for (i=0;i<20;++i){ //Todos os valores do vetor polinomio2 são 0, para não dar erro na soma
		lista[i] = 0;
	}
	for (i = grau; i>=0; --i){ /*inserir os valores do polinomio 1*/
		printf("Valor: ");
		scanf("%d", &lista[i]);
	}
	representacao(lista, grau);
}
/*Função para calcular do valor do polinômio*/
int valor(int lista1[], int grau){ 
	int i,j,x;
	int valor = 0;
	int lista2[grau+1];
	printf("Valor de x: ");
	scanf("%d",&x);
	for (i = grau; i>=0; --i){	/*loop para calcular o valor de cada termo do polinômio, e colocar em um novo vetor*/
		lista2[i] = lista1[i]*pow(x, i);
	}
	for (j = grau; j>=0; --j){ /*loop para somar os valores do novo vetor, com resultado o valor do polinômio*/
		valor += lista2[j];
	}
	return valor;
}
/*Função para calcular a soma entre dois polinômios*/
void soma(int vetor1 [], int grau1, int vetor2[], int grau2){
	int i,j;
	int tamanho = grau1+grau2;
	int soma[tamanho];  /*Vetor para guardar os resultados das somas*/	
	if (grau1>=grau2){ /*caso o primeiro polinômio for maior que o segundo*/
		for (i = grau1; i>=0; --i){ /*loop para somar os termos de mesmo expoente dos polinômios*/
			soma[i] = vetor1[i]+vetor2[i];
		}
		printf(" \n");
		printf("\nSoma dos polinômios: \n");
		representacao(soma, grau1);
	}
	else if (grau2>grau1){ /*caso o segundo polinômio for maior que o segundo*/
		for (i = grau2; i>=0; --i){ /*loop para somar os termos de mesmo expoente dos polinômios*/
			soma[i] = vetor2[i]+vetor1[i];
		}
		printf(" \n");
		printf("\nSoma dos polinômios: \n");
		representacao(soma, grau2);
	}
}
/*Função para calcular a multiplicação entre dois polinômios*/
void multi(int lista1 [], int grau1, int lista2[], int grau2){ 
	int i,j,x;
	int tamanho = grau1+grau2;
	int multi[tamanho]; /*Vetor para guardar os resultados das multiplicações*/
	for (i = 0; i<=tamanho;++i){ /*transformar todos os valores do vetor multi em 0*/
		multi[i] = 0;
	}
	for (i = 0; i<=grau1; ++i){ /*Loop para fazer a distribuição e calcular os valores do novo polinômio*/
		for (j = 0; j<=grau2; ++j){
			multi[i+j] = multi[i+j] + lista1[i] * lista2[j]; /*como a multiplicação de expoentes é uma soma entre eles, o expoente do termo do polinomio1 na posição i somado com o expoente do termo do polinomio2 na posição j. Assim achando o novo expoente da multiplicação dos termos*/
		}
	}
	printf(" \n");
	printf("\nMultiplicação dos polinomios: \n");
	representacao(multi,tamanho);
}

int main(){
	setlocale(LC_ALL,"Portuguese");
	int grau1, i, tamanho, x, valorPoli, grau2, grau3, escolha, escolha2;
	int polinomio[20];
	int polinomio2[20];
	int polinomio3[20];
	printf("Gerar um polinômio: \n");
	grau1 = grau();
	gerarPoli(polinomio, grau1);
	
	while(true){ /*Escolher a oção no menu, e só parar quando a opção de termino for escolhida, possibilitando fazer multiplas operações com o mesmo polinômio*/
		menu();
		printf("\nEscolha uma opção: ");
		scanf("%d", &escolha);
		while(escolha>3 || escolha<0){ //verificação
			printf("Opção não valida. Escolha: ");
			scanf("%d", &escolha);
		}
		printf("Opção escolhida: %d", escolha);
		printf(" \n");
		
		if(escolha == 0){ // final do programa
			printf("Você encerrou o programa.");
			break;
		}
		
		else if (escolha == 1){ //Valor do polinomio
			menu2();
			int escolha2 = escolhaMenu2();
			if (escolha2 == 2){ //caso o usuario queira criar um novo polinomio
				printf("Gerar um polinômio: \n");
				grau3 = grau();
				gerarPoli(polinomio3, grau3);
				printf(" \n");
				valorPoli = valor(polinomio3, grau3);
			}
			else{ //caso o usuario queira utilizaro o polinomio cirado anteriormente
				valorPoli = valor(polinomio, grau1);
			}
			printf("Valor do polinomio: %d", valorPoli);
		}
		
		else if (escolha == 2){ //soma polinomio
			menu2();
			escolha2 = escolhaMenu2();
			if (escolha2 == 2){ //Criar novo polinimio
				printf("Gerar um polinômio: \n");
				grau3 = grau();
				gerarPoli(polinomio3, grau3);
				printf(" \n");
			}
			printf("Defina um segundo polinomio: \n");
			grau2 = grau();
			gerarPoli(polinomio2, grau2);
			if (escolha2 == 1){ //utilizar valor criado no inicio
				soma(polinomio, grau1, polinomio2, grau2);
			}
			else{ //utilizar valores novos
				soma(polinomio3, grau3, polinomio2, grau2);
			}
		}
		
		else if (escolha == 3){ //multiplicacao de polinomio 
			menu2();
			escolha2 = escolhaMenu2();
			if (escolha2 == 2){ //Criar novo polinimio
				printf("\nGerar um polinômio: \n");
				grau3 = grau();
				gerarPoli(polinomio3,grau3);
				printf(" \n");
			}
			printf("Defina um segundo polinomio: \n");
			grau2 = grau();
			gerarPoli(polinomio2, grau2);	
			if (escolha2 == 1){ //utilizar valor criado no inicio
				multi(polinomio, grau1, polinomio2, grau2);
			}
			else{ //utilizar valores novos
				multi(polinomio3, grau3, polinomio2, grau2);
			}
		}
	}
	return 0;
}
