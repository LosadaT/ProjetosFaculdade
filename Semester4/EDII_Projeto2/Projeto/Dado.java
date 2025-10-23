public class Dado {
    private int chave;
    private int zona;
    private String municipio;
    private int ed_infantil;
    private int ed_especial;
    private int ed_recurso;
    private int ed_medio;
    private int eja_fund_1;
    private int eja_fund_2;
    private int eja_medio;
    private int ano_ini;
    private int ano_fim;

    public Dado() {
        chave = 0;
        ano_ini = 0;
        ano_fim = 0;
        zona = 0;
        ed_infantil = 0;
        ed_especial = 0;
        ed_recurso = 0;
        ed_medio = 0;
        eja_fund_1 = 0;
        eja_fund_2 = 0;
        eja_medio = 0;
    }

    public Dado(int chave, int zona, String municipio, int ed_infantil, int ed_especial, int ed_recurso, int ano_ini, int ano_fim, int ed_medio, int eja_fund_1, int eja_fund_2, int eja_medio) {
        this.chave = chave;
        this.ano_ini = ano_ini;
        this.ano_fim = ano_fim;
        this.zona = zona;
        this.municipio = municipio;
        this.ed_infantil = ed_infantil;
        this.ed_especial = ed_especial;
        this.ed_recurso = ed_recurso;
        this.ed_medio = ed_medio;
        this.eja_fund_1 = eja_fund_1;
        this.eja_fund_2 = eja_fund_2;
        this.eja_medio = eja_medio;
    }

    public int getChave() {return chave;}
    public int getAnoIni() {return ano_ini;}
    public int getAnoFim() {return ano_fim;}
    public int getZona() { return zona; }
    public String getMunicipio() { return municipio; }
    public int getEdInfantil() { return ed_infantil; }
    public int getEdEspecial() { return ed_especial; }
    public int getEdRecurso() { return ed_recurso; }
    public int getEdMedio() { return ed_medio; }
    public int getEjaFund1() { return eja_fund_1; }
    public int getEjaFund2() { return eja_fund_2; }
    public int getEjaMedio() { return eja_medio; }

    public void setZona(int zona) { this.zona = zona; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }
    public void setEdInfantil(int ed_infantil) { this.ed_infantil = ed_infantil; }
    public void setEdEspecial(int ed_especial) { this.ed_especial = ed_especial; }
    public void setEdRecurso(int ed_recurso) { this.ed_recurso = ed_recurso; }
    public void setEdMedio(int ed_medio) { this.ed_medio = ed_medio; }
    public void setEjaFund1(int eja_fund_1) { this.eja_fund_1 = eja_fund_1; }
    public void setEjaFund2(int eja_fund_2) { this.eja_fund_2 = eja_fund_2; }
    public void setEjaMedio(int eja_medio) { this.eja_medio = eja_medio; }
    public void setChave(int chave) {this.chave = chave;} 
    public void setAnoIni(int ano) {ano_ini = ano;}
    public void setAnoFim(int ano) {ano_fim = ano;}
    public void setDado(Dado dado) {
        this.chave = dado.getChave();
        this.ano_ini = dado.getAnoIni();
        this.ano_fim = dado.getAnoFim();
        this.zona = dado.getZona();
        this.municipio = dado.getMunicipio();
        this.ed_infantil = dado.getEdInfantil();
        this.ed_especial = dado.getEdEspecial();
        this.ed_recurso = dado.getEdRecurso();
        this.ed_medio = dado.getEdMedio();
        this.eja_fund_1 = dado.getEjaFund1();
        this.eja_fund_2 = dado.getEjaFund2();
        this.eja_medio = dado.getEjaMedio();
    }
}
