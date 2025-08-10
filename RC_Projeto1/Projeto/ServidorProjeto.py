from itertools import product
import socket, json #importa modulo socket

# Função para determinar se o jogo terminou
def CheckWin(x,y):
    tie = []
    for m,n in product(range(9), range(3)):
        if x[m][3 * n + 1] == x[m][3 * n + 2] == x[m][3 * n + 3] != " ":
            x[m][0] = True
            y[m + 1] = x[m][3 * n + 1]
        if x[m][n + 1] == x[m][n + 4] == x[m][n + 7] != " ":
            x[m][0] = True
            y[m + 1] = x[m][n + 1]
        if x[m][1] == x[m][5] == x[m][9] != " ":
            x[m][0] = True
            y[m + 1] = x[m][1]
        if x[m][3] == x[m][5] == x[m][7] != " ":
            x[m][0] = True
            y[m + 1] = x[m][3]
        if y[m + 1] == " " and " " not in [x[m][1], x[m][2], x[m][3], x[m][4], x[m][5], x[m][6], x[m][7], x[m][8], x[m][9]]:
            x[m][0] = True
            y[m + 1] = "ox"

        xWin = list(product(["X", "ox"], repeat = 3))
        xWin.pop()
        oWin = list(product(["O", "ox"], repeat = 3))
        oWin.pop()
        if (y[3 * n + 1], y[3 * n + 2], y[3 * n + 3]) in xWin:
            y[0] = "X"
        if (y[n + 1], y[n + 4], y[n + 7]) in xWin:
            y[0] = "X"
        if (y[1], y[5], y[9]) in xWin:
            y[0] = "X"
        if (y[3], y[5], y[7]) in xWin:
            y[0] = "X"
        if (y[3 * n + 1], y[3 * n + 2], y[3 * n + 3]) in oWin:
            y[0] = "O"
        if (y[n + 1], y[n + 4], y[n + 7]) in oWin:
            y[0] = "O"
        if (y[1], y[5], y[9]) in oWin:
            y[0] = "O"
        if (y[3], y[5], y[7]) in oWin:
            y[0] = "O"
        if " " not in y and y[0] == "On":
            y[0] = "OX"
    return(x,y)

BigBoard = []
Boards = []

# Preparar os tabuleiros de jogo
#   O bool determina se o tabuleiro especifo ainda está em jogo
#   Os números determinam qual símbolo está em qual posição do tabuleiro:
#   1 / 2 / 3
#   4 / 5 / 6
#   7 / 8 / 9

for n in range(9):
    Boards.append([])
    Boards[n].append(False)
    for m in range(9):
        Boards[n].append(" ")
BigBoard.append("On")
for n in range(9):
    BigBoard.append(" ")

turn = True
PlayBoard = 0
# !!! INSERIR O IP DA MÁQUINA QUE VAI RODAR OS PROGRAMAS !!!
TCP_IP = '' # endereço IP do servidor 
TCP_PORTA = 10364       # porta disponibilizada pelo servidor
TAMANHO_BUFFER = 1024     # definição do tamanho do buffer
 
# Criação de socket TCP
# SOCK_STREAM, indica que será TCP.
servidor = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# IP e porta que o servidor deve aguardar a conexão
servidor.bind((TCP_IP, TCP_PORTA))

#Define o limite de conexões. 
servidor.listen(1)

print("Servidor dispoivel na porta 5005 e escutando.....") 
# Aceita conexão 
conn, addr = servidor.accept()
print ('Endereço conectado:', addr)
data = b'TRUE'
while 1:
    # Envia dados para o cliente
    response = json.dumps({"Fim": "Nao",
                           "B": Boards, "bigB": BigBoard, 
                           "msg": ["Digite em qual tabuleiro e em qual lugar jogar:(1 - 9),(1 - 9):", 
                                   "Tabuleiro em Jogo:",
                                   "Digite em qual lugar jogar:(1 - 9):",
                                   "Tabuleiro Inválido.",
                                   "Posição Inválida."],
                           "tabul": PlayBoard, "turn": turn})
    conn.send(response.encode())  # envia dados recebidos em letra maiuscula
    if data == b'QUIT':
        break
    
    #dados retidados da mensagem recebida
    data = conn.recv(TAMANHO_BUFFER)
    if data:
        data = data.decode().split(",")
        print ("Mensagem recebida:", data)
        # Lógica do jogo
        """
        if turn == True:
            print("Vez de: X")
        else:
            print("Vez de: O")
        """
        if len(data) > 1 and PlayBoard == 0:
            PlayBoard = int(data[0])
            pos = int(data[1])
        else:
            pos = int(data[0])
        
        if turn == True and Boards[PlayBoard - 1][pos] != "O":
            Boards[PlayBoard - 1][pos] = "X"
        elif Boards[PlayBoard - 1][pos] != "X":
            Boards[PlayBoard - 1][pos] = "O"
        turn = not turn
        Boards,BigBoard = CheckWin(Boards, BigBoard)
        if BigBoard[0] != "On":
            response = json.dumps({"Fim": "QUIT",
                           "B": Boards, "bigB": BigBoard, 
                           "msg": ["Digite em qual tabuleiro e em qual lugar jogar:(1 - 9),(1 - 9):", 
                                   "Tabuleiro em Jogo:",
                                   "Digite em qual lugar jogar:(1 - 9):",
                                   "Tabuleiro Inválido.",
                                   "Posição Inválida."],
                           "tabul": PlayBoard, "turn": turn})
            conn.send(response.encode())
            break
        # printBoard(Boards, BigBoard)
        if Boards[pos - 1][0] == False:
            PlayBoard = pos
            # print("Tabuleiro em Jogo:", PlayBoard)
        else:
            # print("Tabuleiro em Jogo: #")
            PlayBoard = 0