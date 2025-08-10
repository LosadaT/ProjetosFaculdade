

public class No {
    String simbolo;
    No esquerda;
    No direita;

    No() {
        this.simbolo = null;
        esquerda = null;
        direita = null;
    }

    public void setSimbolo(String x) {
        simbolo = x;
    }

    public void set(No no, String modo) {
        if (modo == "esquerda")
            esquerda = no;
        else if (modo == "direita")
            direita = no;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public No get(String modo) {
        if (modo == "esquerda")
            return esquerda;
        else if (modo == "direita")
            return direita;
        return null;
    }
}
