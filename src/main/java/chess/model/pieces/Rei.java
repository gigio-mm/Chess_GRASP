package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Rei extends Peca {
    public Rei(Cor cor) {
        super(cor, 0);
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Pula a própria casa
                }
                Posicao alvo = new Posicao(posicaoAtual.getLinha() + i, posicaoAtual.getColuna() + j);
                if (tabuleiro.isPosicaoValida(alvo) && !tabuleiro.temPecaDaMesmaCor(alvo, this.getCor())) {
                    movimentos.add(alvo);
                }
            }
        }
        return movimentos;
    }

    @Override
    public String getRepresentacao() {
        return getCor() == Cor.BRANCO ? "K" : "k";
    }
}