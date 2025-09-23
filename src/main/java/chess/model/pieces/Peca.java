package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;

import java.util.List;

public abstract class Peca {

    protected final Cor cor;
    protected final int valor;
    protected boolean jaMoveu;


    public Peca(Cor cor, int valor) {
        this.cor = cor;
        this.valor = valor;
        this.jaMoveu = false;
    }

    public Cor getCor() {
        return cor;
    }

    public int getValor() {
        return valor;
    }

    public boolean isJaMoveu() {
        return jaMoveu;
    }

    public void notificarQueJaMoveu() {
        this.jaMoveu = true;
    }


    public abstract List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro);

    public abstract String getRepresentacao();
}