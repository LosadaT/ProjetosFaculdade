

public class ArvoreExpressao {
    private No raiz;
    private ConversorExp conversor;
    private PilhaString calculo;
    
    public ArvoreExpressao() {
        raiz = new No();
        calculo = new PilhaString(100);
    }

    public void inserir(String expressao) {
        PilhaString pilha = new PilhaString();
        conversor = new ConversorExp(expressao);
        pilha = conversor.converter();
        PilhaString teste1 = new PilhaString();
        while (!pilha.isEmpty()) {
            teste1.push(pilha.top());
            pilha.pop();
        }
        while (!teste1.isEmpty()) {
            pilha.push(teste1.top());
            teste1.pop();
        }
        inserirPrivada(pilha, raiz);
    }

    // Função retirada de: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
    private boolean isDigit(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void inserirPrivada(PilhaString i, No raiz) {
        String simb = i.pop();
        if (simb == null)
            return;
        raiz.setSimbolo(simb);
        if (isDigit(simb)) {
            return;
        }
        raiz.set(new No(), "esquerda");
        inserirPrivada(i, raiz.get("esquerda"));
        
        raiz.set(new No(), "direita");
        inserirPrivada(i, raiz.get("direita"));
    }

    public String calcular() {
        calcularPrivado(raiz);
        return calculo.pop();
    }

    private void calcularPrivado(No no) {
        if (no != null) {
            calcularPrivado(no.get("esquerda"));
            calcularPrivado(no.get("direita"));
            calculo.push(no.getSimbolo());
            verificarSinal();
        }
    }

    private void verificarSinal() {
        if (calculo.top().equals("+")) {
            calculo.pop();
            float a = Float.parseFloat(calculo.top());
            calculo.pop();
            float b = Float.parseFloat(calculo.top());
            calculo.pop();
            String res = String.valueOf((a+b));
            calculo.push(res);
        }
        if (calculo.top().equals("-")) {
            calculo.pop();
            float a = Float.parseFloat(calculo.top());
            calculo.pop();
            float b = Float.parseFloat(calculo.top());
            calculo.pop();
            String res = String.valueOf((b-a));
            calculo.push(res);
        }
        if (calculo.top().equals("*")) {
            calculo.pop();
            float a = Float.parseFloat(calculo.top());
            calculo.pop();
            float b = Float.parseFloat(calculo.top());
            calculo.pop();
            String res = String.valueOf((a*b));
            calculo.push(res);
        }
        if (calculo.top().equals("/")) {
            calculo.pop();
            float a = Float.parseFloat(calculo.top());
            calculo.pop();
            float b = Float.parseFloat(calculo.top());
            calculo.pop();
            String res = String.valueOf((b/a));
            calculo.push(res);
        }
    }

    public void exibirArvore(){
        exibirArvore(this.raiz, 0);
    }

    private void exibirArvore(No no, int nivel) {
        if(no != null) {
            exibirArvore(no.get("direita"), nivel + 1);
            for(int r = 1; r <= nivel; r++)
              System.out.print("= ");
            System.out.println(no.getSimbolo());
            exibirArvore(no.get("esquerda"), nivel + 1);
        }
    }
   

    public void exibirArvoreEmOrdem() {
        exibirArvoreEmOrdemFunc(this.raiz);
    }
    
    private void exibirArvoreEmOrdemFunc(No no) {
        if(no != null) {
            exibirArvoreEmOrdemFunc(no.esquerda);
            System.out.print(no.getSimbolo() + " ");
            exibirArvoreEmOrdemFunc(no.direita);
        }
    }
    public void exibirArvorePreOrdem() {
        exibirArvorePreOrdemFunc(this.raiz);
    }
    
    private void exibirArvorePreOrdemFunc(No no) {
        if(no != null) {
            System.out.print(no.getSimbolo() + " ");
            exibirArvorePreOrdemFunc(no.esquerda);
            exibirArvorePreOrdemFunc(no.direita);
        }
    }
    public void exibirArvorePosOrdem() {
        exibirArvorePosOrdemFunc(this.raiz);
    }
    
    private void exibirArvorePosOrdemFunc(No no) {
        if(no != null) {
            exibirArvorePosOrdemFunc(no.esquerda);
            exibirArvorePosOrdemFunc(no.direita);
            System.out.print(no.getSimbolo() + " ");
        }
    }
    /*int nivel*/    
        
    public void emOrdem() {
        visitar(raiz, "emOrdem");
    }
    public void preOrdem() {
        visitar(raiz, "preOrdem");
    }
    public void posOrdem() {
        visitar(raiz, "posOrdem");
    }
    private void visitar(No no, String modo) {
        if(no != null) {
            if (modo == "preOrdem")
                System.out.print(no.getSimbolo() + " ");
            visitar(no.get("esquerda"), modo);
            if (modo == "emOrdem")
                System.out.print(no.getSimbolo() + " ");
            visitar(no.get("direita"), modo);
            if (modo == "posOrdem")
                System.out.print(no.getSimbolo() + " ");
        }
    }
}