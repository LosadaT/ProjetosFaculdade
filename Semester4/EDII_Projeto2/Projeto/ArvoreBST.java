public class ArvoreBST {
    private No raiz;
    public static int compRemocao, compBusca;

    public void excluirArvore() {
        raiz = null;
        compRemocao = 0;
        compBusca = 0;
    }
    public int inserir(int chave, int zona, String municipio, int ed_infantil, int ed_especial, int ed_recurso, int ano_ini, int ano_fim, int ed_medio, int eja_fund_1, int eja_fund_2, int eja_medio) {
        No novoNo = new No(chave, zona, municipio, ed_infantil, ed_especial, ed_recurso, ano_ini, ano_fim, ed_medio, eja_fund_1, eja_fund_2, eja_medio);
        int comp = 0;
        if (raiz == null) {
            raiz = novoNo;
            return comp;
        } else {
            No atual = raiz;
            No pai;

            while (true) {
                pai = atual;
                comp++;
                if (chave <= atual.getChave()) {
                    atual = atual.getEsq();
                    if (atual == null) {
                        pai.setEsq(novoNo);
                        return comp;
                    }
                } else {
                    atual = atual.getDir();
                    if (atual == null) {
                        pai.setDir(novoNo);
                        return comp;
                    }
                }
            }
        }
    }

    public static No getMinimo(No raiz) {
        if (raiz.getEsq() == null)
            return raiz;
        else
            return getMinimo(raiz.getEsq());
    }

    public No remover(int chave) {
        compRemocao = 0;
        return remover(raiz, chave);
    }

    private static No remover(No raiz, int chave) {
        if (raiz == null)
            return null;
        compRemocao++;
        if (raiz.getChave() > chave)
            raiz.setEsq(remover(raiz.getEsq(), chave));
        else if (raiz.getChave() < chave)
            raiz.setDir(remover(raiz.getDir(), chave));
        else {
            if (raiz.getEsq() != null && raiz.getDir() != null) {
                No temp = raiz;
                No noMinDir = getMinimo(temp);
                raiz.setDado(noMinDir.getDado());
                raiz.setDir(remover(raiz.getDir(), noMinDir.getChave()));
            }
            else if (raiz.getEsq() != null)
                raiz = raiz.getEsq();
            else if (raiz.getDir() != null)
                raiz = raiz.getDir();
            else
                raiz = null;
        }
        return raiz;
    }

    public int getCompRemocao() {
        return compRemocao;
    }

    public No buscar(int chave) {
        No atual = raiz;
        compBusca = 0;
        while (atual != null) {
            compBusca++;
            if (atual.getChave() == chave)
                return atual;
            if (atual.getChave() < chave)
                atual = atual.getDir();
            else
                atual = atual.getEsq();
        }
        return null;
    }

    public int getCompBusca() {
        return compBusca;
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