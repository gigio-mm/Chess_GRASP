package chess.model.pieces;

import chess.model.enums.Cor;
import chess.model.Posicao;
import chess.model.Tabuleiro;

import java.util.ArrayList;
import java.util.List;

public class Cavalo extends Peca {
    public Cavalo(Cor cor) {
        super(cor, 3);
    }

    @Override
    public List<Posicao> getMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();
        int[][] deslocamentos = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] d : deslocamentos) {
            Posicao alvo = new Posicao(posicaoAtual.getLinha() + d[0], posicaoAtual.getColuna() + d[1]);
            if (tabuleiro.isPosicaoValida(alvo) && !tabuleiro.temPecaDaMesmaCor(alvo, this.getCor())) {
                movimentos.add(alvo);
            }
        }
        return movimentos;
    }

    @Override
    public String getRepresentacao() {
        return getCor() == Cor.BRANCO ? "C" : "c";
    }
}