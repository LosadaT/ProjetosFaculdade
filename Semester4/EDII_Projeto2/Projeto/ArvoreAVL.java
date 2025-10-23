public class ArvoreAVL {
    private No raiz;
    
    public int somaMedio, somaFund, compInserir, compRemocao, compBusca;

    public void excluirArvore() {
        raiz = null;
    }

    private int max(int altura_no1, int altura_no2) {
        return (altura_no1 > altura_no2) ? altura_no1 : altura_no2;
    }

    public int getAlturaNo(No no) {
        return (no == null) ? -1 : no.getAltura();
    }

    private No rotacionarDireita(No no) {
        No aux;
        aux = no.getEsq();
        no.setEsq(aux.getDir());
        aux.setDir(no);
        no.setAltura(this.max(this.getAlturaNo(no.getDir()), this.getAlturaNo(no.getEsq()))+1);
        aux.setAltura(this.max(this.getAlturaNo(aux.getEsq()), no.getAltura())+1);
        return aux;
    }
    private No rotacionarEsquerda(No no) {
        No aux;
        aux = no.getDir();
        no.setDir(aux.getEsq());
        aux.setEsq(no);
        no.setAltura(this.max(this.getAlturaNo(no.getDir()), this.getAlturaNo(no.getEsq()))+1);
        aux.setAltura(this.max(this.getAlturaNo(aux.getDir()), no.getAltura())+1);
        return aux;
    }
    private No rotacionarEsquerdaDireita(No no) {
        no.setEsq(this.rotacionarEsquerda(no.getEsq()));
        return this.rotacionarDireita(no);
    }
    private No rotacionarDireitaEsquerda(No no) {
        no.setDir(this.rotacionarDireita(no.getDir()));
        return this.rotacionarEsquerda(no);
    }

    public void inserir(int chave, int zona, String municipio, int ed_infantil, int ed_especial, int ed_recurso, int ano_ini, int ano_fim, int ed_medio, int eja_fund_1, int eja_fund_2, int eja_medio) {
        compInserir = 0;
        raiz = inserir(raiz, chave, zona, municipio, ed_infantil, ed_especial, ed_recurso, ano_ini, ano_fim, ed_medio, eja_fund_1, eja_fund_2, eja_medio);
    }
    
    private No inserir(No raiz, int chave, int zona, String municipio, int ed_infantil, int ed_especial, int ed_recurso, int ano_ini, int ano_fim, int ed_medio, int eja_fund_1, int eja_fund_2, int eja_medio) {
        if (raiz == null)
            return (new No(chave, zona, municipio, ed_infantil, ed_especial, ed_recurso, ano_ini, ano_fim, ed_medio, eja_fund_1, eja_fund_2, eja_medio));
        compInserir++;
        if (chave <= raiz.getChave()) {
            raiz.setEsq(inserir(raiz.getEsq(), chave, zona, municipio, ed_infantil, ed_especial, ed_recurso, ano_ini, ano_fim, ed_medio, eja_fund_1, eja_fund_2, eja_medio));
            if (getAlturaNo(raiz.getEsq()) - getAlturaNo(raiz.getDir()) == 2)
                if (chave <= raiz.getEsq().getChave())
                    raiz = rotacionarDireita(raiz);
                else
                    raiz = rotacionarEsquerdaDireita(raiz);
        }
        else
            if (chave > raiz.getChave()) {
                raiz.setDir(inserir(raiz.getDir(), chave, zona, municipio, ed_infantil, ed_especial, ed_recurso, ano_ini, ano_fim, ed_medio, eja_fund_1, eja_fund_2, eja_medio));
                if (getAlturaNo(raiz.getDir()) - getAlturaNo(raiz.getEsq()) == 2)
                    if (chave > raiz.getDir().getChave())
                        raiz = rotacionarEsquerda(raiz);
                    else
                        raiz = rotacionarDireitaEsquerda(raiz);
            }
        raiz.setAltura(max(getAlturaNo(raiz.getEsq()), getAlturaNo(raiz.getDir()))+1);
        return raiz;
    }

    public void remover(int chave) {
        compRemocao = 0;
        raiz = remover(raiz, chave);
    }

    private No remover(No no, int chave) {
        if (no == null)
            return no;
        compRemocao++;
        if (chave < no.getChave())
            no.setEsq(remover(no.getEsq(), chave));
        else
            if (chave > no.getChave())
                no.setDir(remover(no.getDir(), chave));
            else
                if (no.getEsq() != null && no.getDir() != null) {
                    no.setDado(sucessor(no.getChave()).getDado());
                    no.setDir(remover(no.getDir(), no.getChave()));
                }
                else
                    no = (no.getEsq() != null) ? no.getEsq() : no.getDir();
        return no;
    }

    private No sucessor(int chave) {
        No atual;

        atual = buscar(chave);
        if (atual == null)
            return null;
        if (atual.getDir() == null)
            return null;
        atual = atual.getDir();
        while (atual.getEsq() != null)
            atual = atual.getEsq();
        return atual;
    }

    public No buscar(int chave) {
        No atual = raiz;
        compBusca = 0;
        while (atual != null) {
            compBusca++;
            if (atual.getChave() == chave)
                return atual;
            if (atual.getChave() <= chave)
                atual = atual.getDir();
            else
                atual = atual.getEsq();
        }
        return null;
    }

    public void exibirArvore(int dado) {
        exibirArvore(this.raiz, 0, dado);
    }

    private void exibirArvore(No no, int nivel, int dado) {
        if (no != null) {
            exibirArvore(no.getDir(), nivel+1, dado);
            for (int r = 1; r <= nivel; r++)
                System.out.print(" - ");
            printDado(no, dado);
            exibirArvore(no.getEsq(), nivel+1, dado);
        }
    }
    public void calcularArvore() {
    	somaMedio = 0;
    	somaFund = 0;
        somaArvore(this.raiz);
    }
    private void somaArvore(No no) {
    	if (no != null) {
            somaArvore(no.getDir());
            somaMedio = somaMedio+no.getDado().getEdMedio();
            somaFund = somaFund + no.getDado().getAnoFim();
            somaArvore(no.getEsq());
        }
    }
    private void printDado(No no, int dado) {

    }

    public void visitar(String modo, int dado) {
        if (modo.equalsIgnoreCase("preordem"))
            visitarPreOrdem(raiz, dado);
        if (modo.equalsIgnoreCase("emordem"))
            visitarEmOrdem(raiz, dado);
        if (modo.equalsIgnoreCase("posordem"))
            visitarPosOrdem(raiz, dado);
    }

    private void visitarPreOrdem(No no, int dado) {
        if (no != null) {
            printDado(no, dado);
            visitarEmOrdem(no.getEsq(), dado);
            visitarEmOrdem(no.getDir(), dado);
        }
    }

    private void visitarEmOrdem(No no, int dado) {
        if (no != null) {
            visitarEmOrdem(no.getEsq(), dado);
            printDado(no, dado);
            visitarEmOrdem(no.getDir(), dado);
        }
    }

    private void visitarPosOrdem(No no, int dado) {
        if (no != null) {
            visitarEmOrdem(no.getEsq(), dado);
            visitarEmOrdem(no.getDir(), dado);
            printDado(no, dado);
        }
    }

    public int quantidadeNos() {
        return quantidadeNos(raiz);
    }

    private int quantidadeNos(No no) {
        if (no == null)
            return 0;
        return 1 + quantidadeNos(no.getEsq()) + quantidadeNos(no.getDir());
    }
}