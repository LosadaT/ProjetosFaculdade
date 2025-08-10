from itertools import product
import socket, json #importa modulo socket

# Função para exibir o tabuleiro para o cliente
def printBoard(x, y):
    def Borders():
        if d != 2:
            print("\u2502", sep = "" , end = " ")
            return()
        if c != 2:
            print("\u2551", sep = "" , end = " ")
            return()
        print("")
        if b != 2:
            print("\u2500\u2500\u2500\u253c\u2500\u2500\u2500\u253c\u2500\u2500\u2500\u256b\u2500\u2500\u2500\u253c\u2500\u2500\u2500\u253c\u2500\u2500\u2500\u256b\u2500\u2500\u2500\u253c\u2500\u2500\u2500\u253c\u2500\u2500\u2500")
            return()
        if a != 2:
            print("\u2550\u2550\u2550\u256a\u2550\u2550\u2550\u256a\u2550\u2550\u2550\u256c\u2550\u2550\u2550\u256a\u2550\u2550\u2550\u256a\u2550\u2550\u2550\u256c\u2550\u2550\u2550\u256a\u2550\u2550\u2550\u256a\u2550\u2550\u2550")
    for a, b, c, d in product(range(3), repeat=4):
        if y[(a * 3) + c + 1] == " ":
            if (d,c) == (0,0):
                print(" ", sep = "", end = "")
            print(x[c + (a * 3)][d + (b * 3) + 1], sep = "", end = " ")
            Borders()
        elif y[(a * 3) + c + 1] == "X":
            if (d,c) == (0,0):
                print(" ", sep = "", end = "")
            if b == d and b % 2 == 0:
                print("\u2572", sep = "", end = " ")
            elif b % 2 == 0 and d % 2 == 0:
                print("\u2571", sep = "", end = " ")
            elif b == d:
                print("\u2573", sep = "", end = " ")
            else:
                print(" ", sep = "", end = " ")
            Borders()
        elif y[(a * 3) + c + 1] == "O":
            if (d,c) == (0,0):
                print(" ", sep = "", end = "")
            if b == d == 0:
                print("\u256d", sep = "", end = " ")
            elif b == d == 2:
                print("\u256f", sep = "", end = " ")
            elif b == 0 and d == 2:
                print("\u256e", sep = "", end = " ")
            elif b == 2 and d == 0:
                print("\u2570", sep = "", end = " ")
            elif b == 1 and d % 2 == 0:
                print("\u2502", sep = "", end = " ")
            elif d == 1 and b % 2 == 0:
                print("\u2500", sep = "", end = " ")
            else:
                print(" ", sep = "", end = " ")
            Borders()
        elif y[(a * 3) + c + 1] == "ox":
            if (d,c) == (0,0):
                print(" ", sep = "", end = "")
            if b == d == 0:
                print("\u256d", sep = "", end = " ")
            elif b == d == 1:
                print("\u2573", sep = "", end = " ")
            elif b == d == 2:
                print("\u256f", sep = "", end = " ")
            elif b == 0 and d == 2:
                print("\u256e", sep = "", end = " ")
            elif b == 2 and d == 0:
                print("\u2570", sep = "", end = " ")
            elif b == 1 and d % 2 == 0:
                print("\u2502", sep = "", end = " ")
            elif d == 1 and b % 2 == 0:
                print("\u2500", sep = "", end = " ")
            else:
                print(" ", sep = "", end = " ")
            Borders()

# !!! INSERIR O IP DA MÁQUINA QUE VAI RODAR OS PROGRAMAS !!!
TCP_IP = ''# endereço IP do servidor 
TCP_PORTA = 10364      # porta disponibilizada pelo servidor
TAMANHO_BUFFER = 1024
MENSAGEM = ""
# Criação de socket TCP do cliente
cliente = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Conecta ao servidor em IP e porta especifica 
cliente.connect((TCP_IP, TCP_PORTA))

while (MENSAGEM != "QUIT"):
    # recebe dados do servidor 
    data, addr = cliente.recvfrom(1024)
    data = json.loads(data.decode())
    printBoard(data.get("B"), data.get("bigB"))
    if data.get("Fim") == "QUIT":
        print("O jogo terminou!")
        break
    if data.get("turn") == True:
        print("Vez de: X")
    else:
        print("Vez de: O")
    if data.get("tabul") == 0:
        print(data.get("msg")[0])
    else:
        print(data.get("msg")[1], end=" ")
        print(data.get("tabul"))
        print(data.get("msg")[2])
    
    # envia mensagem para servidor 
    MENSAGEM = input("")
    cliente.send(MENSAGEM.encode('UTF-8'))

# fecha conexão com servidor
cliente.close()

