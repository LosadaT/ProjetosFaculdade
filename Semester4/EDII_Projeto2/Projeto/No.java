public class No {
    private Dado dado;
    private No dir;
    private No esq;
    private int altura;

    public No(int chave, int zona, String municipio, int ed_infantil, int ed_especial, int ed_recurso, int ano_ini, int ano_fim, int ed_medio, int eja_fund_1, int eja_fund_2, int eja_medio) {
        dado = new Dado(chave, zona, municipio, ed_infantil, ed_especial, ed_recurso, ano_ini, ano_fim, ed_medio, eja_fund_1, eja_fund_2, eja_medio);
        dir = null;
        esq = null;
        altura = 0;
    }

    public No() {
        dado = new Dado();
        dir = null;
        esq = null;
        altura = 0;
    }

    public int getAltura() {return altura;}
    public void setAltura(int altura) {this.altura = altura;}

    public Dado getDado() {return dado;}
    public int getChave() {return dado.getChave();}
    public No getEsq() {return esq;}
    public No getDir() {return dir;}

    public void setDado(Dado dado) {this.dado.setDado(dado);}
    public void setZona(int zona) {dado.setZona(zona);}
    public void setAnoFim(int ano_fim) {dado.setAnoFim(ano_fim);}
    public void setAnoIni(int ano_ini) {dado.setAnoIni(ano_ini);}
    public void setChave(int chave) {dado.setChave(chave);}
    public void setEjaMedio(int eja_medio) {dado.setEjaMedio(eja_medio);}
    public void setEjaFund2(int eja_fund_2) {dado.setEjaFund2(eja_fund_2);}
    public void setEjaFund1(int eja_fund_1) {dado.setEjaFund1(eja_fund_1);}
    public void setEdMedio(int ed_medio) {dado.setEdMedio(ed_medio);}
    public void setEdRecurso(int ed_recurso) {dado.setEdRecurso(ed_recurso);}
    public void setEdEspecial(int ed_especial) {dado.setEdEspecial(ed_especial);}
    public void setEdInfantil(int ed_infantil) {dado.setEdInfantil(ed_infantil);}
    public void setMunicipio(String municipio) {dado.setMunicipio(municipio);}
    public void setEsq(No no) {esq = no;}
    public void setDir(No no) {dir = no;}
}
