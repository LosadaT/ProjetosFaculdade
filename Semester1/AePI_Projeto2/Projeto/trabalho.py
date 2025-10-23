import math
#Francisco Losada Totaro - 32345526

def menu():
    print('1. Cadastrar Candidatos')
    print('2. Cadastrar Eleitores')
    print('3. Votar')
    print('4. Apurar Resultados')
    print('5. Relatório e Estatística')
    print(('6. Encerrar'))
    cod = int(input('Opção Escolhida: '))
    return cod
#Dados candidatos
listaNomePres = []
listaNomeGov = []
listaNomePref = []
listaNumPres = []
listaNumGov = []
listaNumPref = []
listaPartidoPres = []
listaPartidoGov = []
listaPartidoPref = []
listaCargoPres = []
listaCargoGov = []
listaCargoPref = []
listaPres = [listaNomePres,listaNumPres,listaPartidoPres]
listaGov = [listaNomeGov,listaNumGov,listaPartidoGov]
listaPref = [listaNomePref,listaNumPref,listaPartidoPref]
#Dados eleitores
listaNomeEleitores = []
listaCpfEleitores = []
listaEleitores = [listaNomeEleitores, listaCpfEleitores]
#votos
votosPref = []
votosGov = []
votosPres = []
qtdVotos = []
votosPartidoPref = []
votosPartidoGov = []
votosPartidoPres = []
somaPartidos = []
#Backup dados
backupVotosPref = []
backcupVotosGov = []
backupVotosPres = []
backupListaNomePres = []
backupListaNomeGov = []
backupListaNomePref = []
backupListaNumPres = []
backupListaNumGov = []
backupListaNumPref = []
backupListaPartidoPres = []
backupListaPartidoGov = []
backupListaPartidoPref = []
#quantidade de votos para cada candidato
qtdVotosPref = []
#Verificação
BeN = [-1,-2]

while True:
    cod = menu()

    #1-Cadastrar Candidatos
    if cod == 1:
        while True:
            nomeCand = input('Nome do candidato: ')
            numCand = int(input('Número do candidato: '))
            while numCand>99 or numCand<1:
                numCand = int(input('Número invalido. Número do candidato: '))
            partidoCand = input('Partido do candidato: ').upper()
            cargoCand = int(input('Cargo do candidato. Para presidente digite 1, para governador digite 2, para prefeito digite 3: '))
            while cargoCand>3 or cargoCand<1:
                cargoCand = int(input('Cargo invalido. Para presidente digite 1, para governador digite 2, para prefeito digite 3: '))
            
            if cargoCand == 1:
                listaNomePres.append(nomeCand)
                listaNumPres.append(numCand)
                listaPartidoPres.append(partidoCand)

            elif cargoCand == 2:
                listaNomeGov.append(nomeCand)
                listaNumGov.append(numCand)
                listaPartidoGov.append(partidoCand)

            elif cargoCand == 3:
                listaNomePref.append(nomeCand)
                listaNumPref.append(numCand)
                listaPartidoPref.append(partidoCand)

            rep = int(input('Deseja inserir outro candidato? (1 - Sim, 2 - Não):'))
            while rep<1 or rep>2:
                rep = int(input('Dado não valido. Deseja inserir outro candidato? (1 - Sim, 2 - Não):'))

            if rep == 2:
                break
    #2-Cadastrar Eleitores
    elif cod == 2:
        while True:
            nomeEleitor = input('Nome do eleitor: ')
            cpfEleitor = int(input('CPF do eleitor: '))

            listaNomeEleitores.append(nomeEleitor)
            listaCpfEleitores.append(cpfEleitor)

            rep = int(input('Deseja inserir outro eleitor? (1 - Sim, 2 - Não):'))
            while rep<1 or rep>2:
                rep = int(input('Dado não valido. Deseja inserir outro eleitor? (1 - Sim, 2 - Não):'))

            if rep == 2:
                break
    #3-Votar            
    elif cod == 3:
        for i in range (len(listaNomeEleitores)):
            print(f'Eleitor {listaNomeEleitores[i]}, pode votar')
            votoPref = int(input(f'Voto para Prefeito. Escolha entre {listaNumPref}, ou -1 para branco e -2 para nulo: '))#variavel
            while votoPref not in listaNumPref and votoPref not in BeN:
                votoPref = int(input(f'Voto invalido. Novo voto para Prefeito. Escolha entre {listaNumPref}, ou -1 para branco e -2 para nulo: '))
            votosPref.append(votoPref)
            if votoPref in listaNumPref:
                indice_pref=listaNumPref.index(votoPref)
                somaPartidos.append(listaPartidoPref[indice_pref])
            
            votoGov = int(input(f'Voto para Governador. Escolha entre {listaNumGov}, ou -1 para branco e -2 para nulo: '))
            votosGov.append(votoGov)
            while votoGov not in listaNumGov and votoGov not in BeN:
                votoGov = int(input(f'Voto invalido. Novo voto para Governador. Escolha entre {listaNumGov}, ou -1 para branco e -2 para nulo: '))
            if votoGov in listaNumGov:
                indice_gov=listaNumGov.index(votoGov)
                somaPartidos.append(listaPartidoGov[indice_gov])

            votoPres = int(input(f'Voto para Presidente. Escolha entre {listaNumPres}, ou -1 para branco e -2 para nulo: '))
            votosPres.append(votoPres)
            while votoPres not in listaNumPres and votoPres not in BeN:
                votoPres = int(input(f'Voto invalido. Novo voto para Presidente. Escolha entre {listaNumPres}, ou -1 para branco e -2 para nulo: '))
            if votoPres in listaNumPres:
                indice_candidato=listaNumPres.index(votoPres)
                somaPartidos.append(listaPartidoPres[indice_candidato])
    #4-Apurar Resultados
    elif cod == 4:
        #total de votos
        totalVotosPref = len(votosPref)
        totalVotosGov = len(votosGov)
        totalVotosPres = len(votosPres)
        
        #Total votos validos/nulos/brancos e % para prefeito
        countValidosPref = 0
        countBrancosPref = 0
        countNulosPref = 0
        for i in range (len(votosPref)):
            if votosPref [i] == -1:
                countBrancosPref += 1
            elif votosPref [i] == -2:
                countNulosPref += 1
            else:
                countValidosPref += 1
        perValidosPref = (countValidosPref/len(votosPref))*100
        perBrancosPref = (countBrancosPref/len(votosPref))*100
        perNulosPref = (countNulosPref/len(votosPref))*100

        #Total votos validos/nulos/brancos e % para governador
        countValidosGov = 0
        countBrancosGov = 0
        countNulosGov = 0
        for i in range (len(votosGov)):
            if votosGov [i] == -1:
                countBrancosGov += 1
            elif votosGov [i] == -2:
                countNulosGov += 1
            else:
                countValidosGov += 1
        perValidosGov = (countValidosGov/len(votosGov))*100
        perBrancosGov = (countBrancosGov/len(votosGov))*100
        perNulosGov = (countNulosGov/len(votosGov))*100

        #Total votos validos/nulos/brancos e % para presidente
        countValidosPres = 0
        countBrancosPres = 0
        countNulosPres = 0
        for i in range (len(votosPres)):
            if votosPres [i] == -1:
                countBrancosPres += 1
            elif votosPres [i] == -2:
                countNulosPres += 1
            else:
                countValidosPres += 1
        perValidosPres = (countValidosPres/len(votosPres))*100
        perBrancosPres = (countBrancosPres/len(votosPres))*100
        perNulosPres = (countNulosPres/len(votosPres))*100

        #Resultado prefeito --------------------------------------------------------------------------------
        backupVotosPref = votosPref
        backupListaNomePref = listaNomePref
        backupListaNumPref = listaNumPref
        backupListaPartidoPref = listaPartidoPref
        print(" ________________________________________________________________________________________________")
        print("|                             RANKING DO RESULTADO PARA PREFEITO                                 |")
        print("|------------------------------------------------------------------------------------------------|")
        print("|             %s\t\t |   %s\t|     %s\t|    %s\t |" % ('Nome', 'Partido', 'Total de Votos', '% Votos Validos' ))
        print("|------------------------------------------------------------------------------------------------|")
        for i in range(len(listaNumPref)):
            countVotos = votosPref.count(listaNumPref[i])
            qtdVotos.append(countVotos)

        lenQtdVotos = len(qtdVotos)
        for i in range (lenQtdVotos):
            maxVotosPref = max(qtdVotos) #a maior quantidade de votos é 4
            posVotosPref = qtdVotos.index(maxVotosPref)  #pposição que teve mais votos
            resVotosPref = listaNumPref[posVotosPref]#quem está na posição que teve mais votos
            resPartidoPref = listaPartidoPref[posVotosPref]#quem está na posição que teve mais votos
            resNomePref = listaNomePref[posVotosPref]#quem está na posição que teve mais votos
            resPerVotosPref = (maxVotosPref/len(votosPref))*100 #% de votos validos
            print("|%d%s%s\t\t         |     %s\t|         %d\t        |       %f\t |" % (i+1,'.', resNomePref, resPartidoPref, maxVotosPref, resPerVotosPref))

            del listaNumPref[posVotosPref]
            del listaPartidoPref[posVotosPref]
            del listaNomePref[posVotosPref]
            del qtdVotos[posVotosPref]
        
        votosPref = backupVotosPref
        listaNomePref = backupListaNomePref 
        listaNumPref = backupListaNumPref
        listaPartidoPref = backupListaPartidoPref
        print("|------------------------------------------------------------------------------------------------|")
        print(f'|Total de votos = {totalVotosPref}                                                                              |')
        print(f'|Total de votos validos e % = {countValidosPref} votos validos, {"%.2F"%perValidosPref}%                                            |')
        print(f'|Total de brancos e % = {countBrancosPref} votos brancos, {"%.2F"%perBrancosPref}%                                                  |')                   
        print(f'|Total de nulos e % = {countNulosPref} votos nulos, {"%.2F"%perNulosPref}%                                                      |')                 
        print("|________________________________________________________________________________________________|")

        #Resultado Governador ----------------------------------------------------------------------------------------------
        backupVotosGov = votosGov
        backupListaNomeGov = listaNomeGov
        backupListaNumGov = listaNumGov
        backupListaPartidoGov = listaPartidoGov
        print(" ________________________________________________________________________________________________")
        print("|                             RANKING DO RESULTADO PARA GOVERNADOR                               |")
        print("|------------------------------------------------------------------------------------------------|")
        print("|             %s\t\t |   %s\t|     %s\t|    %s\t |" % ('Nome', 'Partido', 'Total de Votos', '% Votos Validos' ))
        print("|------------------------------------------------------------------------------------------------|")
        
        for i in range(len(listaNumGov)):
            countVotos = votosGov.count(listaNumGov[i])
            qtdVotos.append(countVotos)

        lenQtdVotos = len(qtdVotos)
        for i in range (lenQtdVotos):
            maxVotosGov = max(qtdVotos) #a maior quantidade de votos é 4
            posVotosGov = qtdVotos.index(maxVotosGov)  #pposição que teve mais votos
            resVotosGov = listaNumGov[posVotosGov]#quem está na posição que teve mais votos
            resPartidoGov = listaPartidoGov[posVotosGov]#quem está na posição que teve mais votos
            resNomeGov = listaNomeGov[posVotosGov]#quem está na posição que teve mais votos
            resPerVotosGov = (maxVotosGov/len(votosGov))*100 #% de votos validos
            print("|%d%s%s\t\t         |     %s\t|         %d\t        |       %f\t |" % (i+1,'.', resNomeGov, resPartidoGov, maxVotosGov, resPerVotosGov))

            del listaNumGov[posVotosGov]
            del listaPartidoGov[posVotosGov]
            del listaNomeGov[posVotosGov]
            del qtdVotos[posVotosGov]
        
        votosGov = backupVotosGov
        listaNomeGov = backupListaNomeGov
        listaNumGov = backupListaNumGov
        listaPartidoGov = backupListaPartidoGov
        print("|------------------------------------------------------------------------------------------------|")
        print(f'|Total de votos = {totalVotosGov}                                                                              |')
        print(f'|Total de votos validos e % = {countValidosGov} votos validos, {"%.2F"%perValidosGov}%                                            |')
        print(f'|Total de brancos e % = {countBrancosGov} votos brancos, {"%.2F"%perBrancosGov}%                                                  |')                   
        print(f'|Total de nulos e % = {countNulosGov} votos nulos, {"%.2F"%perNulosGov}%                                                      |')                 
        print("|________________________________________________________________________________________________|")


        #Resultado Presidente ----------------------------------------------------------------------------------------------
        backupVotosPres = votosPres
        backupListaNomePres = listaNomePres
        backupListaNumPres = listaNumPres
        backupListaPartidoPres = listaPartidoPres
        print(" ________________________________________________________________________________________________")
        print("|                             RANKING DO RESULTADO PARA PRESIDENTE                               |")
        print("|------------------------------------------------------------------------------------------------|")
        print("|             %s\t\t |   %s\t|     %s\t|    %s\t |" % ('Nome', 'Partido', 'Total de Votos', '% Votos Validos' ))
        print("|------------------------------------------------------------------------------------------------|")
        for i in range(len(listaNumPres)):
            countVotos = votosPres.count(listaNumPres[i])
            qtdVotos.append(countVotos)

        lenQtdVotos = len(qtdVotos)
        for i in range (lenQtdVotos):
            maxVotosPres = max(qtdVotos) #a maior quantidade de votos é 4
            posVotosPres = qtdVotos.index(maxVotosPres)  #pposição que teve mais votos
            resVotosPres = listaNumPres[posVotosPres]#quem está na posição que teve mais votos
            resPartidoPres = listaPartidoPres[posVotosPres]#quem está na posição que teve mais votos
            resNomePres = listaNomePres[posVotosPres]#quem está na posição que teve mais votos
            resPerVotosPres = (maxVotosPres/len(votosPres))*100 #% de votos validos
            print("|%d%s%s\t\t         |     %s\t|         %d\t        |       %f\t |" % (i+1,'.', resNomePres, resPartidoPres, maxVotosPres, resPerVotosPres)) 

            del listaNumPres[posVotosPres]
            del listaPartidoPres[posVotosPres]
            del listaNomePres[posVotosPres]
            del qtdVotos[posVotosPres]
        
        votosPres = backupVotosPres
        listaNomePres = backupListaNomePres
        listaNumPres = backupListaNumPres
        listaPartidoPres = backupListaPartidoPres  
        print("|------------------------------------------------------------------------------------------------|")
        print(f'|Total de votos = {totalVotosPres}                                                                              |')
        print(f'|Total de votos validos e % = {countValidosPres} votos validos, {"%.2F"%perValidosPres}%                                            |')
        print(f'|Total de brancos e % = {countBrancosPres} votos brancos, {"%.2F"%perBrancosPres}%                                                  |')                   
        print(f'|Total de nulos e % = {countNulosPres} votos nulos, {"%.2F"%perNulosPres}%                                                      |')                 
        print("|________________________________________________________________________________________________|")
        
    
    #Relatório e estatísticas
    elif cod == 5:
        #Lista Eleitores
        listaNomeEleitores.sort()
        print(f"Lista de Eleitores: {listaNomeEleitores}")
        #Auditoria
        if len(listaNomeEleitores) == len(votosPref) and len(listaNomeEleitores) == len(votosGov) and len(listaNomeEleitores) == len(votosPres):
            print("O total de eleitores bate com o total de votos")
        else:
            print("O total de eleitores nao bate com o total de votos")
        
        #Partido com mais politicos
        partidos2 = set(somaPartidos)
        partidos2 = list(partidos2) #lista com todos os partidos
        guardaPartidoMenor = None
        guardaPartidoMaior = None
        maior = 0
        menor = math.inf
        for i in range(len(partidos2)):
            guarda = somaPartidos.count(partidos2[i]) #contando quantos partidos tem
            if guarda > maior:
                maior = guarda
                guardaPartidoMaior=partidos2[i]
            elif guarda < menor:
                menor = guarda
                guardaPartidoMenor=partidos2[i]
        print(" ")
        print("O partido que mais elegeu políticos é o", guardaPartidoMaior)
        print("O partido que menos elegeu políticos é o", guardaPartidoMenor)
        print(" ")
    #Finalizar
    elif cod == 6:
        print('Programa encerrado.')
        break
