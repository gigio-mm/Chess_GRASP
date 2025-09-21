package br.com.model.pecas;

public abstract class Peca {
    private String cor;
    private int valor;
    private boolean jaMoveu;

    public Peca(String cor, int valor) {
        this.cor = cor;
        this.valor = valor;
        this.jaMoveu = false;
    }

    public static void notificarQueJaMoveu() {

    }


    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isJaMoveu() {
        return jaMoveu;
    }

    public void setJaMoveu(boolean jaMoveu) {
        this.jaMoveu = jaMoveu;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
