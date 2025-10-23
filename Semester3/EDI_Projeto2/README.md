# EDI_Projeto2
Projeto desenvolvido para o matéria de Estrutura de Dados I, em Java. Editor de Texto simples que utiliza comandos no terminal.

## Comandos:
- :e (Arq.ext) -> Abrir arquivo "Arq.ext"
- :w [Arq.ext] -> Salvar as modificações em arquivo [Arq.ext]
- :wq -> Salvar as modificações em arquivo e finalizar o editor
- :q! -> Finalizar o editor sem salvar
- :ZZ -> Gravar conteúdo em arquivo
- :v (LinIni) (LinFin) -> Marcar texto das linhas "LinIni" até "LinFin"
- :y -> Copia o texto marcado
- :c -> Recorta o texto marcado
- :p (Linha) -> Cola o texto copiado/recordado a partir da linha "Linha"
- :s [LinIni] [LinFin] -> Exibir na tela o conteúdo das linhas "LinIni" até "LinFin"
- :x (Linha) -> Apaga a linha "Linha"
- :xG (Linha) -> Apaga todas as linhas abaixo da linha "Linha", inclusive
- :XG (Linha) -> Apaga todas as linhas acima da linha "Linha", inclusive
- :/ (Elem) [ElemTroca] [Linha] -> Exibe/troca todos as instâncias de "Elem" {por "ElemTroca"} {na linha "Linha"}
- :o -> apaga a lista e insere elementos, termina na linha com somente ":o"
- :a (Linha) -> insere elementos após a linha "Linha", termina na linha com somente ":a"
- :i (Linha) -> insere elementos antes da linha "Linha", termina na linha com somente ":i"
- :help -> imprime o funcionamento de todos os comandos
